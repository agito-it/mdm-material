package org.agito.demo.mdm.material.test;

import org.agito.demo.mdm.material.MaterialBPMO;
import org.agito.demo.mdm.material.MaterialBPMOAccess;
import org.agito.demo.mdm.material.MaterialBPMOLifecycle;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import de.agito.cps.core.bpmo.IBPMO;
import de.agito.cps.core.process.spi.eventing.ProcessAgentEventType;
import de.agito.cps.core.process.spi.task.TaskInstance;
import de.agito.cps.core.test.BPMOTestRule;

public class MaterialTest {

	@Rule
	public BPMOTestRule bpmoRule = BPMOTestRule.init();

	@Test
	public void testBPMOLifecycleCreate() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getRuntimeService().createBPMO(MaterialBPMO.$BPMO, MaterialBPMOLifecycle.CREATE, "001");
		MaterialBPMOAccess access = new MaterialBPMOAccess(bpmo.getBPMOData());
		access.getName().setValue("Test");
		access.getMaterialType().setValue("1");
		access.getBaseUnitOfMeasure().setValue("DZN");

		// start process
		bpmo.startProcess();
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_START));

		// claim first task
		TaskInstance task = bpmoRule.getTaskService().createTaskQuery().bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid())
				.singleResult();
		Assert.assertNotNull(task);
		bpmoRule.getTaskService().claim(task.getId(), BPMOTestRule.DEFAULT_USER_ID);
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).processActivityIds(task.getTaskDefinitionId())
				.eventType(ProcessAgentEventType.TASK_CLAIM));

		// complete first task
		task = bpmoRule.getTaskService().createTaskQuery().bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).singleResult();
		bpmo = bpmoRule.getRuntimeService().readBPMO(bpmo.getBPMOHeader().getBPMOUuid(), true,
				task.getTaskDefinitionId());
		bpmo.setTaskInstance(task);
		bpmo.completeTaskInstance(null);
		Assert.assertNotNull(bpmoRule.getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).processActivityIds(task.getTaskDefinitionId())
				.eventType(ProcessAgentEventType.TASK_COMPLETE));

		// TODO assert further tasks

	}

}
