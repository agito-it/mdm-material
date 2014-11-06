package org.agito.demo.mdm.material;

// @@begin imports

import de.agito.cps.core.bpmo.ControlAttribute;
import de.agito.cps.core.bpmo.ControlScope;
import de.agito.cps.core.bpmo.DataTypeFactory;
import de.agito.cps.core.bpmo.ExpressionType;
import de.agito.cps.core.bpmo.IBPMOData;
import de.agito.cps.core.bpmo.IControlAttributes;
import de.agito.cps.core.bpmo.IInstanceArtifact;
import de.agito.cps.core.bpmo.INode;
import de.agito.cps.core.bpmo.INodeElement;
import de.agito.cps.core.bpmo.MessageSeverity;
import de.agito.cps.core.bpmo.annotations.BPMO;
import de.agito.cps.core.bpmo.annotations.Expression;
import de.agito.cps.core.bpmo.annotations.ExpressionDependency;
import de.agito.cps.core.bpmo.api.controller.BPMOController;
import de.agito.cps.core.bpmo.api.controller.IBPMOControllerContext;
import de.agito.cps.core.engine.runtime.BusinessLog;
import de.agito.cps.core.utils.StringUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import org.agito.demo.mdm.material.MaterialBPMO;
import org.agito.demo.mdm.material.MaterialBPMOAccess;
import org.agito.demo.mdm.material.MaterialBPMOAccess.AlternativeUnitOfMeasures;
import org.agito.demo.mdm.material.MaterialBPMOAccess.BaseUnitOfMeasure;
import org.agito.demo.mdm.material.MaterialBPMOAction;
import org.agito.demo.mdm.material.MaterialBPMOLanguage;
import org.agito.demo.mdm.material.MaterialBPMOLifecycle;
import org.agito.demo.mdm.material.MaterialBPMOProcessActivity;
import org.agito.demo.mdm.material.dto.MaterialHeaderDTO;
import org.agito.demo.mdm.material.dto.MaterialMockService;

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

	public MaterialBPMOController(final IBPMOControllerContext context) {
		super(context);
	}

	// @@begin head:change:BaseUnitOfMeasure
	/**
	 * Hook for change expression of BaseUnitOfMeasure
	 */
	// @@end
	@Expression(artifact = "Header$BaseUnitOfMeasure", type = ExpressionType.CHANGE)
	@ExpressionDependency("Header$BaseUnitOfMeasure")
	public void cpsChangeBaseUnitOfMeasure(final MaterialBPMOAccess bpmoAccess) {
		final BaseUnitOfMeasure baseUnitOfMeasure = bpmoAccess.getBaseUnitOfMeasure();
		/*
		 * The value of BaseUnitOfMeasure must exist on AlternativeUnitOfMeasures as well
		 */
		// @@begin body:change:BaseUnitOfMeasure
		if (baseUnitOfMeasure.getValue() != null)

			if (bpmoAccess.getAlternativeUnitOfMeasures().getRowsByKeyValues(baseUnitOfMeasure.getValueKey()).isEmpty()) {
				AlternativeUnitOfMeasures.Row altRow = bpmoAccess.getAlternativeUnitOfMeasures().createAndAddRow();
				altRow.getAlternativeUnitOfMeasure().setValue(baseUnitOfMeasure.getValueKey());
				altRow.getDenominatorConversion().setValue("1");
				altRow.getNumeratorConversion().setValue("1");
			}

		// @@end
	}

	// @@begin head:validate:AlternativeUnitOfMeasures
	/**
	 * Hook for validate expression of AlternativeUnitOfMeasures
	 */
	// @@end
	@Expression(artifact = "Header$AlternativeUnitOfMeasures", type = ExpressionType.VALIDATION)
	@ExpressionDependency("Header$BaseUnitOfMeasure")
	public boolean cpsValidateAlternativeUnitOfMeasures(final MaterialBPMOAccess bpmoAccess) {
		final AlternativeUnitOfMeasures alternativeUnitOfMeasures = bpmoAccess.getAlternativeUnitOfMeasures();
		final BaseUnitOfMeasure baseUnitOfMeasure = bpmoAccess.getBaseUnitOfMeasure();
		/*
		 * Check, if the BaseUnitOfMeasure is part of the AOM table.
		 */
		// @@begin body:validate:AlternativeUnitOfMeasures

		if (baseUnitOfMeasure.getValue() != null)
			if (alternativeUnitOfMeasures.getRowsByKeyValues(baseUnitOfMeasure.getValueKey()).isEmpty()) {
				alternativeUnitOfMeasures.addMessage(MessageSeverity.ERROR, "000",
						String.format("The Alternative Unit Of Measure %s is mandatory", baseUnitOfMeasure.getValue()));
				return false;
			}

		return true;

		// @@end
	}

	// @@begin head:controlcell:AlternativeUnitOfMeasures$AlternativeUnitOfMeasure
	/**
	 * Hook for cell based control expression of AlternativeUnitOfMeasures$AlternativeUnitOfMeasure
	 */
	// @@end
	@Expression(artifact = "Header$AlternativeUnitOfMeasures$AlternativeUnitOfMeasure", type = ExpressionType.CELL_BASED_CONTROL)
	@ExpressionDependency("Header$BaseUnitOfMeasure")
	public void cpsControlAlternativeUnitOfMeasures$AlternativeUnitOfMeasure(final MaterialBPMOAccess bpmoAccess, final IControlAttributes controlAttributes, final AlternativeUnitOfMeasures.Row rowAccess) {
		final BaseUnitOfMeasure baseUnitOfMeasure = bpmoAccess.getBaseUnitOfMeasure();
		/*
		 * Should only editable if the value not equals BaseUnitOfMeasure
		 */
		// @@begin body:controlcell:AlternativeUnitOfMeasures$AlternativeUnitOfMeasure
		if (controlAttributes.getRequestedAttributes().contains(ControlAttribute.EDITABLE))
			if (baseUnitOfMeasure.getValue() != null && rowAccess.getAlternativeUnitOfMeasure().getValue() != null)
				controlAttributes.setEditable(!baseUnitOfMeasure.getValueKey().equals(
						rowAccess.getAlternativeUnitOfMeasure().getValueKey()));

		// @@end
	}

	// @@begin others

	@Override
	public Object cpsExecuteBPMOAction(MaterialBPMOAccess bpmoAccess, MaterialBPMOAction action,
			Map<String, Object> parameters) {
		BusinessLog businessLog;
		switch (action) {
		case ReadMaterial:
			try {
				// IgnoreControls on the bpmo
				bpmoAccess.getBPMOData().setIgnoreControlsOnValueChange();

				// clear all previous generated messages
				bpmoAccess.getBPMOData().clearMessages();
				MaterialHeaderDTO material = (MaterialHeaderDTO) parameters.get(ActionParameter.MATERIAL_HEADER_DTO
						.toString());

				// +++ simulate a service interface to get more details of material +++
				new MaterialMockService().fillMaterial(material, bpmoAccess);

			} catch (RuntimeException e) {
				throw e;
			} finally {
				// reset IgnoreControls
				bpmoAccess.getBPMOData().resetIgnoreControlsOnValueChange();
			}
			return null;
		case FindMaterial:
			// +++ simulate a service interface to find material +++
			return new MaterialMockService().findMaterialByElements(
					(String) parameters.get(ActionParameter.MATERIAL_HEADER_QUERY_ATTIBUTE_NAME.toString()),
					(Integer) parameters.get(ActionParameter.MATERIAL_HEADER_QUERY_ATTIBUTE_NUMBER.toString()));
		case EvaluateApproveActivities:
			// +++ evaluate all responsible activities for request approval +++
			return getResponsibleProcessActivities(bpmoAccess.getContext(), null);
		case CreateMaterial:
			// +++ simulate a service interface to create or update material +++

			// -------- do something on interface

			// write new material number given from interface to BO
			bpmoAccess.getMaterialNumber().setValue(
					StringUtils.leftPad(String.valueOf(new Random().nextInt(999999999)), 10, "0"));

		case UpdateMaterial:

			// +++ simulate a service interface to create or update material +++

			// -------- do something on interface

			// write processing info to business log
			businessLog = DataTypeFactory.getInstance().createBusinessLog();
			businessLog.addInfoLogEntry("Request processed", String.format("%s: Material %s Processing id \"%s\"",
					action, bpmoAccess.getMaterialNumber().getValue(), UUID.randomUUID().toString()));
			bpmoAccess.getBPMO().saveBusinessLog(businessLog, false);

			parameters.put("IsProcessed", true);
			break;
		}
		return super.cpsExecuteBPMOAction(bpmoAccess, action, parameters);
	}

	/**
	 * Get each modified {@link IInstanceArtifact} of {@link IBPMOData} and collect the corresponding<br>
	 * static modeled process activities for {@link ControlScope#RESPONSIBILITY}
	 * 
	 * @param nodeElement
	 * @param set
	 * @return set of process activities
	 */
	private Set<String> getResponsibleProcessActivities(INodeElement nodeElement, Set<String> set) {
		if (set == null)
			set = new HashSet<String>();
		for (IInstanceArtifact<?, ?> artifact : nodeElement.getModifiedArtifacts())
			set.addAll(artifact.getDefinition().getProcessActivities(lifecycle, ControlScope.RESPONSIBILITY));
		for (INode node : nodeElement.getChildNodes())
			for (INodeElement iNodeElement : node.getNodeElements())
				getResponsibleProcessActivities(iNodeElement, set);

		return set;
	}

	@Override
	public void cpsBeforeSaveBPMO(MaterialBPMOAccess bpmoAccess) {
		Map<MaterialBPMOLanguage, String> titles = new HashMap<MaterialBPMOLanguage, String>();
		for (MaterialBPMOLanguage language : MaterialBPMOLanguage.values())
			titles.put(language, String.format("Material (%s / %s)", bpmoAccess.getName().getValue() == null ? ""
					: bpmoAccess.getName().getValue(), bpmoAccess.getMaterialType().getValue() == null ? ""
					: bpmoAccess.getMaterialType().getValue(language).getValue()));
		bpmoAccess.getBPMO().setTitle(titles);
	}

	@Override
	public boolean cpsBeforeValidation(MaterialBPMOAccess bpmoAccess, boolean formatOnly, boolean resetInvalidValue) {
		if (!formatOnly)
			switch (getLifecycle()) {
			case CREATE:
				break;
			case UPDATE:
				if (bpmoAccess.getMaterialNumber().getValue() == null) {
					bpmoAccess.addMessage(DataTypeFactory.getInstance().createMessage(MessageSeverity.ERROR, "000",
							"Please select a material"));
					return false;
				}

			}
		return true;
	}

	public enum ActionParameter {
		MATERIAL_HEADER_DTO,

		MATERIAL_HEADER_QUERY_ATTIBUTE_NAME,

		MATERIAL_HEADER_QUERY_ATTIBUTE_NUMBER;
	}
	// @@end
}
