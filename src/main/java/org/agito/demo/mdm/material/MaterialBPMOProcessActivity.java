package org.agito.demo.mdm.material;


import de.agito.cps.core.bpmo.api.enums.IProcessActivity;


/**
 * Process Activity Enum for MaterialBPMO.
 *
 * @author Jörg Burmeister
 */
public enum MaterialBPMOProcessActivity implements IProcessActivity {

	$DRAFT,
	Requester,
	HeaderMgmt,
	PlantMgmt,
	StorageMgmt,
	DistributionMgmt,
	Approver,
	CreateMaterial;

}

