package org.agito.demo.mdm.material;

import de.agito.cps.core.bpmo.api.enums.ILifecycle;

/**
 * Lifecycle Enum for Header.
 *
 * @author Jörg Burmeister
 */
public enum MaterialBPMOLifecycle implements ILifecycle {

	CREATE(false, "MDM_Material_Create"),
	UPDATE(false, "MDM_Material_Update");

	private MaterialBPMOLifecycle(boolean supportsOriginalValue, String processDefinitionId) { this.supportsOriginalValue = supportsOriginalValue; this.processDefinitionId = processDefinitionId; }
	private boolean supportsOriginalValue;
	private String processDefinitionId;
	public boolean supportsOriginalValue() { return supportsOriginalValue; }
	public String getProcessDefinitionId() { return processDefinitionId; }

}

