package org.agito.demo.mdm.material;

// @@begin imports
import de.agito.cps.core.annotations.BPMO;
import de.agito.cps.core.bpmo.api.controller.BPMOController;
import de.agito.cps.core.bpmo.api.controller.IBPMOControllerContext;
import de.agito.cps.core.logger.Logger;
import java.util.HashMap;
import java.util.Map;
import org.agito.demo.mdm.material.MaterialBPMO;
import org.agito.demo.mdm.material.MaterialBPMOAccess;
import org.agito.demo.mdm.material.MaterialBPMOAction;
import org.agito.demo.mdm.material.MaterialBPMOLanguage;
import org.agito.demo.mdm.material.MaterialBPMOLifecycle;
import org.agito.demo.mdm.material.MaterialBPMOProcessActivity;
// @@end

// @@begin head:controller
/**
 * BPMOController for MaterialBPMO.
 * 
 * @author andreas.weise
 */
// @@end
@BPMO(id = "MaterialBPMO", version = "1.0.0", xml = "org/agito/demo/mdm/material/MaterialBPMO.bpmo")
public class MaterialBPMOController extends BPMOController<MaterialBPMOAccess, MaterialBPMOAction, MaterialBPMOLifecycle, MaterialBPMOLanguage, MaterialBPMOProcessActivity, MaterialBPMO> {

	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(MaterialBPMOController.class);

	public MaterialBPMOController(IBPMOControllerContext context) {
		super(context);
	}

	// @@begin others

	@Override
	public Object cpsExecuteBPMOAction(MaterialBPMOAccess bpmoAccess, MaterialBPMOAction action,
			Map<String, Object> parameters) {
		return super.cpsExecuteBPMOAction(bpmoAccess, action, parameters);
	}

	@Override
	public void cpsBeforeSaveBPMO(MaterialBPMOAccess bpmoAccess) {
		Map<MaterialBPMOLanguage, String> titles = new HashMap<MaterialBPMOLanguage, String>();
		titles.put(MaterialBPMOLanguage.en, String.format("Material (%s / %s)",
				bpmoAccess.getName().getCurrentValue() == null ? "" : bpmoAccess.getName().getCurrentValue(),
				bpmoAccess.getMaterialType().getCurrentValue() == null ? "" : bpmoAccess.getMaterialType()
						.getCurrentValue().getValue()));
		bpmoAccess.getContext().getBPMOHeader().getParent().setTitle(titles);

	}
	// @@end
}
