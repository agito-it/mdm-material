package org.agito.demo.mdm.material.test;

import org.agito.demo.mdm.material.MaterialBPMO;
import org.agito.demo.mdm.material.MaterialBPMOAccess;
import org.agito.demo.mdm.material.MaterialBPMOAccess.SalesOrganizations.SalesTexts;
import org.agito.demo.mdm.material.MaterialBPMOLifecycle;
import org.agito.demo.mdm.material.MaterialBPMOProcessActivity;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import de.agito.cps.core.bpmo.IBPMO;
import de.agito.cps.core.process.spi.eventing.ProcessAgentEventType;
import de.agito.cps.core.process.spi.task.TaskInstance;
import de.agito.cps.core.test.BPMOTestRule;
import de.agito.cps.core.test.annotations.BPMOTestUserId;

public class MaterialTest {

	@Rule
	public BPMOTestRule bpmoRule = BPMOTestRule.init();

	@BPMOTestUserId("bob")
	@Test
	public void testBPMOLifecycleCreate() throws InterruptedException {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.CREATE, "001");
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());
		access.getName().setValue("Test");
		access.getMaterialType().setValue("1");
		access.getBaseUnitOfMeasure().setValue("DZN");

		// start process
		bpmo.startProcess();
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_START)
				.singleResult());

		// claim the first task
		TaskInstance firstTask = bpmoRule.getTaskService().createTaskQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).singleResult();
		Assert.assertNotNull(firstTask);
		bpmoRule.getTaskService().claim(firstTask.getId(), "bob");

		// checkout and complete header task
		bpmo = bpmoRule.getRuntimeService().readBPMO(bpmo.getBPMOHeader().getBPMOUuid(), true,
				MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance(firstTask.getId(), null);
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).processActivityIds(firstTask.getTaskDefinitionId())
				.eventType(ProcessAgentEventType.TASK_COMPLETE).singleResult());

		// checkout and complete plant task
		bpmoRule.switchUser("alice");
		TaskInstance plantTask = bpmoRule.getTaskService().createTaskQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).taskDefinitionId(MaterialBPMOProcessActivity.PlantMgmt)
				.singleResult();
		Assert.assertNotNull(plantTask);
		bpmoRule.getTaskService().claim(plantTask.getId(), "alice");
		bpmo = bpmoRule.getRuntimeService().readBPMO(bpmo.getBPMOHeader().getBPMOUuid(), true,
				MaterialBPMOProcessActivity.PlantMgmt);
		access = new MaterialBPMOAccess(bpmo.getBPMOData());
		access.getPlants().createAndAddElement("Berlin").getPlants$ProductionSupervisor().setValue("foobar");
		bpmo.completeTaskInstance(plantTask.getId(), null);

		// checkout and complete storage location task
		bpmoRule.switchUser("alice");
		TaskInstance storageLocationTask = bpmoRule.getTaskService().createTaskQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).taskDefinitionId(MaterialBPMOProcessActivity.StorageMgmt)
				.singleResult();
		Assert.assertNotNull(storageLocationTask);
		bpmoRule.getTaskService().claim(storageLocationTask.getId(), "alice");
		bpmo = bpmoRule.getRuntimeService().readBPMO(bpmo.getBPMOHeader().getBPMOUuid(), true,
				MaterialBPMOProcessActivity.StorageMgmt);
		access = new MaterialBPMOAccess(bpmo.getBPMOData());
		access.getPlants().getBPMOAccessForNodeElements().get(0).getPlants$StorageLocations()
				.createAndAddElement("123").getPlants$StorageLocations$StockInQualityInspection().setValue("foo");
		bpmo.completeTaskInstance(storageLocationTask.getId(), null);

		// checkout and complete distribution task
		TaskInstance distributionTask = bpmoRule.getTaskService().createTaskQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid())
				.taskDefinitionId(MaterialBPMOProcessActivity.DistributionMgmt).singleResult();
		Assert.assertNotNull(distributionTask);
		bpmoRule.getTaskService().claim(distributionTask.getId(), "alice");
		bpmo = bpmoRule.getRuntimeService().readBPMO(bpmo.getBPMOHeader().getBPMOUuid(), true,
				MaterialBPMOProcessActivity.DistributionMgmt);
		access = new MaterialBPMOAccess(bpmo.getBPMOData());
		access = access.getSalesOrganizations().createAndAddElement("DE", "Berlin");
		access.getSalesOrganizations$StatisticsGroup().setValue("0815");
		SalesTexts.Row row = access.getSalesOrganizations$SalesTexts().createAndAddRow();
		row.getLanguage().setValue("foo");
		row.getText().setValue("bar");
		bpmo.completeTaskInstance(distributionTask.getId(), null);

		// checkout and complete approver task
		TaskInstance approvalTask = bpmoRule.getTaskService().createTaskQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).taskDefinitionId(MaterialBPMOProcessActivity.Approver)
				.singleResult();
		Assert.assertNotNull(approvalTask);
		bpmoRule.getTaskService().claim(approvalTask.getId(), "alice");
		bpmo = bpmoRule.getRuntimeService().readBPMO(bpmo.getBPMOHeader().getBPMOUuid(), true,
				MaterialBPMOProcessActivity.Approver);
		bpmo.completeTaskInstance(approvalTask.getId(), null);

		// wait for async jobs
		bpmoRule.waitForActivitiJobExecutorToProcessAllJobs(30000l, 200l);

		// assert process end
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_END)
				.singleResult());

	}
}
