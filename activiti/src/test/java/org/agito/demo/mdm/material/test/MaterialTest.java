package org.agito.demo.mdm.material.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.agito.demo.mdm.material.MaterialBPMO;
import org.agito.demo.mdm.material.MaterialBPMOAccess;
import org.agito.demo.mdm.material.MaterialBPMOAccess.SalesOrganizations.SalesTexts;
import org.agito.demo.mdm.material.MaterialBPMOAction;
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
import de.agito.cps.test.activiti.BPMOTestRule;
import de.agito.cps.test.activiti.annotations.BPMOTestUserId;

public class MaterialTest {

	@Rule
	public BPMOTestRule bpmoRule = BPMOTestRule.init().applicationId("MDM_Material");

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@BPMOTestUserId("bob")
	@Test
	public void testProcessStartNegativeLifecycleCreate() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.CREATE, "001");

		// expect ValidationException upon startProcess()
		expectedException.expect(ValidationException.class);

		// start process
		bpmo.startProcess();

	}

	@BPMOTestUserId("bob")
	@Test
	public void testProcessStartNegativeLifecycleUpdate() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.UPDATE, "001");

		// expect ValidationException upon startProcess()
		expectedException.expect(ValidationException.class);

		// start process
		bpmo.startProcess();

	}

	@BPMOTestUserId("bob")
	@Test
	public void testProcessStartPositiveLifecycleCreate() {

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
	public void testProcessStartPositiveLifecycleUpdate() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.UPDATE, "001");

		// fill bpmo
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());

		MaterialHeaderDTO material = new MaterialHeaderList().findMaterialByElements(null, 1).get(0);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(ActionParameter.MATERIAL_HEADER_DTO.toString(), material);
		access.getBPMO().execute(MaterialBPMOAction.ReadMaterial, parameters);

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

		// checkout and complete HeaderMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete PlantMgmt task
		bpmoRule.switchUser("alice");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
		access.getPlants().createAndAddElement("Berlin").getPlants$ProductionSupervisor().setValue("foobar");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete StorageMgmt task
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

		// checkout and complete Approver task
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

		// checkout and complete HeaderMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete PlantMgmt task
		bpmoRule.switchUser("alice");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
		access.getPlants().createAndAddElement("Berlin").getPlants$ProductionSupervisor().setValue("foobar");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete StorageMgmt task
		bpmoRule.switchUser("bob");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.StorageMgmt);
		access.getPlants().getBPMOAccessForNodeElements().get(0).getPlants$StorageLocations()
				.createAndAddElement("123").getPlants$StorageLocations$StockInQualityInspection().setValue("foo");
		bpmo.completeTaskInstance("Complete", null);

		// checkout and decline DistributionMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.DistributionMgmt);
		access = access.getSalesOrganizations().createAndAddElement("DE", "Berlin");
		access.getSalesOrganizations$StatisticsGroup().setValue("0815");
		SalesTexts.Row row = access.getSalesOrganizations$SalesTexts().createAndAddRow();
		row.getLanguage().setValue("foo");
		row.getText().setValue("bar");
		bpmo.completeTaskInstance("Decline", "Declined");

		// checkout and complete Requester task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.Requester);
		bpmo.completeTaskInstance("Repeat", "is fixed");

		// checkout and complete HeaderMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete PlantMgmt task
		bpmoRule.switchUser("alice");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete StorageMgmt task
		bpmoRule.switchUser("bob");
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.StorageMgmt);
		bpmo.completeTaskInstance("Complete", null);

		// checkout and complete DistributionMgmt task
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
	@Test
	public void testProcessLifecycleUpdate() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.UPDATE, "001");

		// fill bpmo
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());

		MaterialHeaderDTO material = new MaterialHeaderList().findMaterialByElements(null, 1).get(0);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(ActionParameter.MATERIAL_HEADER_DTO.toString(), material);
		access.getBPMO().execute(MaterialBPMOAction.ReadMaterial, parameters);
		access.getAllowedPackagingVolume().setValue("01");

		// start process
		bpmo.startProcess();

		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		// checkout and complete HeaderMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
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
	public void testProcessLifecycleUpdateAddPlantAndStorageLocation() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.UPDATE, "001");

		// fill bpmo
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());

		MaterialHeaderDTO material = new MaterialHeaderList().findMaterialByElements(null, 1).get(0);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(ActionParameter.MATERIAL_HEADER_DTO.toString(), material);
		access.getBPMO().execute(MaterialBPMOAction.ReadMaterial, parameters);
		// add plant
		MaterialBPMOAccess plant = access.getPlants().createAndAddElement("Dresden");
		plant.getPlants$ProductionSupervisor().setValue("Supervisor d");
		plant.getPlants$MinimumLotSize().setValue(new BigDecimal(2));
		plant.getPlants$MaximumLotSize().setValue(new BigDecimal(7));

		// add storage location
		MaterialBPMOAccess storageLocation = plant.getPlants$StorageLocations().createAndAddElement("1099");
		storageLocation.getPlants$StorageLocations$StockInQualityInspection().setValue("done");

		// start process
		bpmo.startProcess();

		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		// checkout and complete PlantMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
		bpmo.completeTaskInstance("Approve", null);

		// checkout and complete StorageMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.StorageMgmt);
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
	public void testProcessLifecycleUpdateRestart() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.UPDATE, "001");

		// fill bpmo
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());

		MaterialHeaderDTO material = new MaterialHeaderList().findMaterialByElements(null, 1).get(0);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(ActionParameter.MATERIAL_HEADER_DTO.toString(), material);
		access.getBPMO().execute(MaterialBPMOAction.ReadMaterial, parameters);
		access.getAllowedPackagingVolume().setValue("01");

		// start process
		bpmo.startProcess();

		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		// checkout and complete HeaderMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance("Decline", "something is wrong, please change the plant Berlin");

		// checkout and complete Requester task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.Requester);
		// change plant item
		MaterialBPMOAccess plant = access.getPlants().getBPMOAccess("Berlin");
		plant.getPlants$ProductionSupervisor().setValue("Supervisor b");
		bpmo.completeTaskInstance("Repeat", "is fixed now");

		// checkout and complete HeaderMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance("Approve", null);

		// checkout and complete PlantMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
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
	public void testProcessLifecycleUpdateAllTasks() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.UPDATE, "001");

		// fill bpmo
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());

		MaterialHeaderDTO material = new MaterialHeaderList().findMaterialByElements(null, 1).get(0);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(ActionParameter.MATERIAL_HEADER_DTO.toString(), material);
		access.getBPMO().execute(MaterialBPMOAction.ReadMaterial, parameters);

		// change header item
		access.getAllowedPackagingVolume().setValue("01");

		// change plant item
		MaterialBPMOAccess plant = access.getPlants().getBPMOAccess("Berlin");
		plant.getPlants$ProductionSupervisor().setValue("Supervisor b");

		// change storage location item
		MaterialBPMOAccess storageLocation = plant.getPlants$StorageLocations().getBPMOAccess("0971");
		storageLocation.getPlants$StorageLocations$StockInQualityInspection().setValue("0815");

		// change salesorg item
		MaterialBPMOAccess salesOrg = access.getSalesOrganizations().getBPMOAccess("8637", "98");
		salesOrg.getSalesOrganizations$StatisticsGroup().setValue("Group 2");

		// start process
		bpmo.startProcess();

		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		// checkout and complete HeaderMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.HeaderMgmt);
		bpmo.completeTaskInstance("Approve", null);

		// checkout and complete DistributionMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.DistributionMgmt);
		bpmo.completeTaskInstance("Approve", null);

		// checkout and complete PlantMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.PlantMgmt);
		bpmo.completeTaskInstance("Approve", null);

		// checkout and complete StorageMgmt task
		bpmo.claimTaskInstance(MaterialBPMOProcessActivity.StorageMgmt);
		bpmo.completeTaskInstance("Approve", null);

		// wait for async jobs
		bpmoRule.waitForActivitiJobExecutorToProcessAllJobs(30000l, 200l);

		// assert process end
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_END)
				.singleResult());

	}
}
