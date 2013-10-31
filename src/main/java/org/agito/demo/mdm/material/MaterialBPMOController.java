package org.agito.demo.mdm.material;

// @@begin imports

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.agito.demo.mdm.material.MaterialBPMOAccess.AlternativeUnitOfMeasures;
import org.agito.demo.mdm.material.MaterialBPMOAccess.AlternativeUnitOfMeasures.Row;
import org.agito.demo.mdm.material.MaterialBPMOAccess.BaseUnitOfMeasure;
import org.agito.demo.mdm.material.dto.MaterialHeaderDTO;
import org.agito.demo.mdm.material.dto.MaterialHeaderList;

import de.agito.cps.core.annotations.BPMO;
import de.agito.cps.core.annotations.Expression;
import de.agito.cps.core.annotations.ExpressionDependency;
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
import de.agito.cps.core.bpmo.api.controller.BPMOController;
import de.agito.cps.core.bpmo.api.controller.IBPMOControllerContext;
import de.agito.cps.core.engine.runtime.BusinessLog;
import de.agito.cps.core.logger.Logger;
import de.agito.cps.core.utils.StringUtils;

// @@end

// @@begin head:controller
/**
 * BPMOController for MaterialBPMO.
 * 
 * @author andreas.weise
 */
// @@end
@BPMO(id = "MaterialBPMO", version = "1.0.0", xml = "org/agito/demo/mdm/material/MaterialBPMO.bpmo")
public class MaterialBPMOController
		extends
		BPMOController<MaterialBPMOAccess, MaterialBPMOAction, MaterialBPMOLifecycle, MaterialBPMOLanguage, MaterialBPMOProcessActivity, MaterialBPMO> {

	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(MaterialBPMOController.class);

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

			if (bpmoAccess.getAlternativeUnitOfMeasures().getRowsByKeyValues(baseUnitOfMeasure.getValue().getKey())
					.isEmpty())
				bpmoAccess.getAlternativeUnitOfMeasures().createAndAddRow().getAlternativeUnitOfMeasure()
						.setValue(baseUnitOfMeasure.getValue().getKey());

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
		/*
		 * Check, if the value of BaseUnitOfMeasure part of table entries
		 */
		final BaseUnitOfMeasure baseUnitOfMeasure = bpmoAccess.getBaseUnitOfMeasure();
		// @@begin body:validate:AlternativeUnitOfMeasures

		if (baseUnitOfMeasure.getValue() != null)
			if (alternativeUnitOfMeasures.getRowsByKeyValues(baseUnitOfMeasure.getValue().getKey()).isEmpty()) {
				alternativeUnitOfMeasures
						.addMessage(DataTypeFactory.getInstance().createMessage(
								MessageSeverity.ERROR,
								"000",
								String.format("The Alternative Unit Of Measure %s is mandatory",
										baseUnitOfMeasure.getValue())));
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
	public void cpsControlAlternativeUnitOfMeasures$AlternativeUnitOfMeasure(final MaterialBPMOAccess bpmoAccess,
			final IControlAttributes controlAttributes, final AlternativeUnitOfMeasures.Row rowAccess) {
		/*
		 * Should only editable if the value not equals BaseUnitOfMeasure
		 */
		final BaseUnitOfMeasure baseUnitOfMeasure = bpmoAccess.getBaseUnitOfMeasure();
		// @@begin body:controlcell:AlternativeUnitOfMeasures$AlternativeUnitOfMeasure
		if (baseUnitOfMeasure.getValue() != null && rowAccess.getAlternativeUnitOfMeasure().getValue() != null)
			if (controlAttributes.getRequestedAttributes().contains(ControlAttribute.EDITABLE))
				controlAttributes.setEditable(!baseUnitOfMeasure.getValue().getKey()
						.equals(rowAccess.getAlternativeUnitOfMeasure().getValue().getKey()));

		// @@end
	}

	// @@begin others

	@Override
	public Object cpsExecuteBPMOAction(MaterialBPMOAccess bpmoAccess, MaterialBPMOAction action,
			Map<String, Object> parameters) {
		BusinessLog businessLog;
		switch (action) {
		case ReadMaterial:
			bpmoAccess.getBPMOData().clearMessages();
			MaterialHeaderDTO material = (MaterialHeaderDTO) parameters.get(ActionParameter.MATERIAL_HEADER_DTO
					.toString());
			bpmoAccess.getName().setOriginalValue(material.getName());
			bpmoAccess.getMaterialNumber().setOriginalValue(material.getNumber());

			// +++ simulate a service interface to get more details of material +++

			// set same sample data
			bpmoAccess.getMaterialType().setOriginalValue("1");
			bpmoAccess.getGrossWeight().setOriginalValue("10");
			bpmoAccess.getNetWeight().setOriginalValue("12");
			bpmoAccess.getAllowedPackagingWeight().setOriginalValue("15");
			bpmoAccess.getAllowedPackagingVolume().setOriginalValue("60");
			bpmoAccess.getVolume().setOriginalValue("14");
			bpmoAccess.getBaseUnitOfMeasure().setOriginalValue("KGM");

			// add row for BaseUnitOfMeasure
			Row row = bpmoAccess.getAlternativeUnitOfMeasures().createOriginalRow();
			row.getAlternativeUnitOfMeasure().setValue(bpmoAccess.getBaseUnitOfMeasure().getOriginalValue().getKey());
			row.getDenominatorConversion().setValue("1");
			row.getNumeratorConversion().setValue("5");
			bpmoAccess.getAlternativeUnitOfMeasures().addOriginalRow(row);

			// add further UnitOfMeasure
			row = bpmoAccess.getAlternativeUnitOfMeasures().createOriginalRow();
			row.getAlternativeUnitOfMeasure().setValue("GRM");
			row.getDenominatorConversion().setValue("4");
			row.getNumeratorConversion().setValue("6");
			bpmoAccess.getAlternativeUnitOfMeasures().addOriginalRow(row);

			// add plant
			MaterialBPMOAccess plant = bpmoAccess.getPlants().createAndAddElement(
					bpmoAccess.getPlants().createPlantId("Berlin"));
			plant.getPlants$ProductionSupervisor().setOriginalValue("Supervisor a");
			plant.getPlants$MinimumLotSize().setOriginalValue(new BigDecimal(1));
			plant.getPlants$MaximumLotSize().setOriginalValue(new BigDecimal(5));
			plant.setErasable(false);

			// add storage location
			MaterialBPMOAccess storageLocation = plant.getPlants$StorageLocations().createAndAddElement(
					plant.getPlants$StorageLocations().createStorageLocationId("0971"));
			storageLocation.getPlants$StorageLocations$StockInQualityInspection().setOriginalValue("done");
			storageLocation = plant.getPlants$StorageLocations().createAndAddElement(
					plant.getPlants$StorageLocations().createStorageLocationId("0972"));
			storageLocation.getPlants$StorageLocations$StockInQualityInspection().setOriginalValue("none");
			storageLocation.setErasable(false);

			// add plant
			plant = bpmoAccess.getPlants().createAndAddElement(bpmoAccess.getPlants().createPlantId("Munich"));
			plant.getPlants$ProductionSupervisor().setOriginalValue("Supervisor b");
			plant.getPlants$MinimumLotSize().setOriginalValue(new BigDecimal(2));
			plant.getPlants$MaximumLotSize().setOriginalValue(new BigDecimal(7));
			plant.setErasable(false);
			plant.getContext().setDeletionFlagOriginalValue();

			// add storage location
			storageLocation = plant.getPlants$StorageLocations().createAndAddElement(
					plant.getPlants$StorageLocations().createStorageLocationId("1071"));
			storageLocation.getPlants$StorageLocations$StockInQualityInspection().setOriginalValue("done");
			storageLocation.setErasable(false);

			// add sales org
			MaterialBPMOAccess salesOrg = bpmoAccess.getSalesOrganizations().createAndAddElement(
					bpmoAccess.getSalesOrganizations().createSalesOrganization("8637"),
					bpmoAccess.getSalesOrganizations().createDistributionChannel("98"));
			salesOrg.getSalesOrganizations$StatisticsGroup().setOriginalValue("group 1");
			salesOrg.getSalesOrganizations$MinimumOrderQuantity().setOriginalValue("3");
			salesOrg.getSalesOrganizations$MinimumDeliveryQuantity().setOriginalValue("9");
			salesOrg.setErasable(false);

			org.agito.demo.mdm.material.MaterialBPMOAccess.SalesOrganizations.SalesTexts.Row rowTexts = salesOrg
					.getSalesOrganizations$SalesTexts().createOriginalRow();
			rowTexts.getLanguage().setValue("en");
			rowTexts.getText().setValue("text en");
			salesOrg.getSalesOrganizations$SalesTexts().addOriginalRow(rowTexts);

			return null;
		case FindMaterial:
			// +++ simulate a service interface to find material +++
			MaterialHeaderList headerList = new MaterialHeaderList();
			return headerList.findMaterialByElements(
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
					: bpmoAccess.getMaterialType().getValue().getValue()));
		bpmoAccess.getBPMO().setTitle(titles);
	}

	@Override
	public boolean cpsBeforeValidate(MaterialBPMOAccess bpmoAccess, boolean formatOnly, boolean resetInvalidValue) {
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
