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

		// checkout and complete header task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance();

		// checkout and complete plant task
		bpmoRule.switchUser("alice");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
		access.getPlants().createAndAddElement("Berlin").getPlants$ProductionSupervisor().setValue("foobar");
		bpmo.completeTaskInstance();

		// checkout and complete storage location task
		bpmoRule.switchUser("alice");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.StorageMgmt);
		access.getPlants().getBPMOAccessForNodeElements().get(0).getPlants$StorageLocations()
				.createAndAddElement("123").getPlants$StorageLocations$StockInQualityInspection().setValue("foo");
		bpmo.completeTaskInstance();

		// checkout and complete distribution task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.DistributionMgmt);
		access = access.getSalesOrganizations().createAndAddElement("DE", "Berlin");
		access.getSalesOrganizations$StatisticsGroup().setValue("0815");
		SalesTexts.Row row = access.getSalesOrganizations$SalesTexts().createAndAddRow();
		row.getLanguage().setValue("foo");
		row.getText().setValue("bar");
		bpmo.completeTaskInstance();

		// checkout and complete approver task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.Approver);
		bpmo.completeTaskInstance();

		// wait for async jobs
		bpmoRule.waitForActivitiJobExecutorToProcessAllJobs(30000l, 200l);

		// assert process end
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_END)
				.singleResult());

	}
}
