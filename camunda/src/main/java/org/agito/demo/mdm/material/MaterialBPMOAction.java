package org.agito.demo.mdm.material;


import de.agito.cps.core.bpmo.api.enums.IAction;


/**
 * BPMO Actions for MaterialBPMO.
 *
 * @author Jörg Burmeister
 */
public enum MaterialBPMOAction implements IAction {

	FindMaterial,
	ReadMaterial,
	UpdateMaterial,
	CreateMaterial,
	EvaluateApproveActivities;

}

