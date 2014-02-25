package org.agito.demo.mdm.material.app;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.PreUndeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;

import de.agito.cps.core.annotations.BPMOApplication;
import de.agito.cps.process.camunda.app.BPMOApplicationHelper;

@BPMOApplication
@ProcessApplication("MDM_Material")
public class MaterialBPMOProcessApplication extends ServletProcessApplication {

	@PostDeploy
	public void deployBPMO() {
		BPMOApplicationHelper.INSTANCE.deploy(this);
	}

	@PreUndeploy
	public void undeployBPMO() {
		BPMOApplicationHelper.INSTANCE.undeploy(this);
	}

}
