package org.agito.demo.mdm.material.dto;

import java.math.BigDecimal;
import java.util.List;

import org.agito.demo.mdm.material.MaterialBPMOAccess;
import org.agito.demo.mdm.material.MaterialBPMOAccess.AlternativeUnitOfMeasures.Row;

/**
 * This is a mock implementation for a material service interface.
 * 
 * @author agito
 * 
 */
public class MaterialMockService {

	public void fillMaterial(MaterialHeaderDTO materialHeaderDTO, MaterialBPMOAccess bpmoAccess) {
		bpmoAccess.getName().setOriginalValue(materialHeaderDTO.getName());
		bpmoAccess.getMaterialNumber().setOriginalValue(materialHeaderDTO.getNumber());

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
		MaterialBPMOAccess plant = bpmoAccess.getPlants().createAndAddElement("Berlin");
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
		plant = bpmoAccess.getPlants().createAndAddElement("Munich");
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
	}

	public List<MaterialHeaderDTO> findMaterialByElements(String name, int number) {
		return new MaterialHeaderList().findMaterialByElements(name, number);
	}

}
