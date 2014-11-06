package org.agito.demo.mdm.material;


import de.agito.cps.core.bpmo.api.enums.IAction;


/**
 * BPMO Actions for MaterialBPMO.
 *
 * @author joerg.burmeister
 */
public enum MaterialBPMOAction implements IAction {

	FindMaterial,
	ReadMaterial,
	UpdateMaterial,
	CreateMaterial,
	EvaluateApproveActivities;

}

