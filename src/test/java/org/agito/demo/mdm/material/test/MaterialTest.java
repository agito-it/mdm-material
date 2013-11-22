package org.agito.demo.mdm.material.test;

import java.util.HashMap;
import java.util.Map;

import org.agito.demo.mdm.material.MaterialBPMO;
import org.agito.demo.mdm.material.MaterialBPMOAccess;
import org.agito.demo.mdm.material.MaterialBPMOAction;
import org.agito.demo.mdm.material.MaterialBPMOAccess.SalesOrganizations.SalesTexts;
import org.agito.demo.mdm.material.MaterialBPMOController.ActionParameter;
import org.agito.demo.mdm.material.MaterialBPMOLifecycle;
import org.agito.demo.mdm.material.MaterialBPMOProcessActivity;
import org.agito.demo.mdm.material.dto.MaterialHeaderDTO;
import org.agito.demo.mdm.material.dto.MaterialHeaderList;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.agito.cps.core.bpmo.IBPMO;
import de.agito.cps.core.exception.ValidationException;
import de.agito.cps.core.process.spi.eventing.ProcessAgentEventType;
import de.agito.cps.core.test.BPMOTestRule;
import de.agito.cps.core.test.annotations.BPMOTestUserId;

public class MaterialTest {

	@Rule
	public BPMOTestRule bpmoRule = BPMOTestRule.init();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@BPMOTestUserId("bob")
	@Test
	public void testProcessStartNegative() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.CREATE, "001");

		// expect ValidationException upon startProcess()
		expectedException.expect(ValidationException.class);

		// start process
		bpmo.startProcess();

	}

	@BPMOTestUserId("bob")
	@Test
	public void testProcessStartPositive() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.CREATE, "001");

		// fill bpmo
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());
		access.getName().setValue("Test");
		access.getMaterialType().setValue("1");
		access.getBaseUnitOfMeasure().setValue("DZN");

		// start process
		bpmo.startProcess();

		// assert
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

	}

	@BPMOTestUserId("bob")
	@Test
	public void testProcessLifecycleCreate() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.CREATE, "001");

		// fill bpmo
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());
		access.getName().setValue("Test");
		access.getMaterialType().setValue("1");
		access.getBaseUnitOfMeasure().setValue("DZN");

		// start process
		bpmo.startProcess();

		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		// checkout and complete header task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete plant task
		bpmoRule.switchUser("alice");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
		access.getPlants().createAndAddElement("Berlin").getPlants$ProductionSupervisor().setValue("foobar");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete storage location task
		bpmoRule.switchUser("bob");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.StorageMgmt);
		access.getPlants().getBPMOAccessForNodeElements().get(0).getPlants$StorageLocations()
				.createAndAddElement("123").getPlants$StorageLocations$StockInQualityInspection().setValue("foo");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete distribution task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.DistributionMgmt);
		access = access.getSalesOrganizations().createAndAddElement("DE", "Berlin");
		access.getSalesOrganizations$StatisticsGroup().setValue("0815");
		SalesTexts.Row row = access.getSalesOrganizations$SalesTexts().createAndAddRow();
		row.getLanguage().setValue("foo");
		row.getText().setValue("bar");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete approver task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.Approver);
		bpmo.completeTaskInstance("Approve", null);

		// wait for async jobs
		bpmoRule.waitForActivitiJobExecutorToProcessAllJobs(30000l, 200l);

		// assert process end
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_END)
				.singleResult());

	}

	@BPMOTestUserId("bob")
	@Test
	public void testProcessLifecycleCreateDeclineTask() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.CREATE, "001");

		// fill bpmo
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());
		access.getName().setValue("Test");
		access.getMaterialType().setValue("1");
		access.getBaseUnitOfMeasure().setValue("DZN");

		// start process
		bpmo.startProcess();

		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		// checkout and complete header task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete plant task
		bpmoRule.switchUser("alice");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
		access.getPlants().createAndAddElement("Berlin").getPlants$ProductionSupervisor().setValue("foobar");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete storage location task
		bpmoRule.switchUser("bob");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.StorageMgmt);
		access.getPlants().getBPMOAccessForNodeElements().get(0).getPlants$StorageLocations()
				.createAndAddElement("123").getPlants$StorageLocations$StockInQualityInspection().setValue("foo");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and decline distribution task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.DistributionMgmt);
		access = access.getSalesOrganizations().createAndAddElement("DE", "Berlin");
		access.getSalesOrganizations$StatisticsGroup().setValue("0815");
		SalesTexts.Row row = access.getSalesOrganizations$SalesTexts().createAndAddRow();
		row.getLanguage().setValue("foo");
		row.getText().setValue("bar");
		bpmo.completeTaskInstance("Decline", "Declined");

		// checkout and complete requester task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.Requester);
		bpmo.completeTaskInstance("Repeat", "is fixed");

		// checkout and complete header task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete plant task
		bpmoRule.switchUser("alice");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete storage location task
		bpmoRule.switchUser("bob");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.StorageMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete distribution task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.DistributionMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete approver task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.Approver);
		bpmo.completeTaskInstance("Approve", null);

		// wait for async jobs
		bpmoRule.waitForActivitiJobExecutorToProcessAllJobs(30000l, 200l);

		// assert process end
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_END)
				.singleResult());

	}
	
	
	
	@BPMOTestUserId("bob")
	//@Test
	public void testProcessLifecycleUpdate() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.UPDATE, "001");

		// fill bpmo
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());
		
		MaterialHeaderDTO material=new MaterialHeaderList().findMaterialByElements(null, 1).get(0);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(ActionParameter.MATERIAL_HEADER_DTO.toString(), material);
		access.getBPMO().execute(MaterialBPMOAction.ReadMaterial, parameters);
	

		// start process
		bpmo.startProcess();

		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		// checkout and complete header task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete plant task
		bpmoRule.switchUser("alice");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
		access.getPlants().createAndAddElement("Berlin").getPlants$ProductionSupervisor().setValue("foobar");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete storage location task
		bpmoRule.switchUser("bob");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.StorageMgmt);
		access.getPlants().getBPMOAccessForNodeElements().get(0).getPlants$StorageLocations()
				.createAndAddElement("123").getPlants$StorageLocations$StockInQualityInspection().setValue("foo");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete distribution task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.DistributionMgmt);
		access = access.getSalesOrganizations().createAndAddElement("DE", "Berlin");
		access.getSalesOrganizations$StatisticsGroup().setValue("0815");
		SalesTexts.Row row = access.getSalesOrganizations$SalesTexts().createAndAddRow();
		row.getLanguage().setValue("foo");
		row.getText().setValue("bar");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete approver task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.Approver);
		bpmo.completeTaskInstance("Approve", null);

		// wait for async jobs
		bpmoRule.waitForActivitiJobExecutorToProcessAllJobs(30000l, 200l);

		// assert process end
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_END)
				.singleResult());

	}
}
