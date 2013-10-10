package org.agito.demo.mdm.material;

import de.agito.cps.core.bpmo.IKeywordEntry;
import de.agito.cps.core.bpmo.INodeElement;
import de.agito.cps.core.bpmo.INodeElementKey;
import de.agito.cps.core.bpmo.IRow;
import de.agito.cps.core.bpmo.api.access.BPMOAccess;
import de.agito.cps.core.bpmo.api.access.CellAccess;
import de.agito.cps.core.bpmo.api.access.CharacteristicAccess;
import de.agito.cps.core.bpmo.api.access.NodeAccess;
import de.agito.cps.core.bpmo.api.access.NodeKeyAccess;
import de.agito.cps.core.bpmo.api.access.RowAccess;
import de.agito.cps.core.bpmo.api.access.TableAccessCurrent;

/**
 * BPMOAccess for Header.
 * 
 * @author Jörg Burmeister
 */
public final class MaterialBPMOAccess extends BPMOAccess<MaterialBPMOAccess> {

	public MaterialBPMOAccess(INodeElement context) { super(context); }

	/**
	 * <b>MaterialNumber</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public MaterialNumber getMaterialNumber() { return super.<MaterialNumber>getCharacteristicAccess(MaterialNumber.class, "Header$MaterialNumber"); }
	/**
	 * <b>Name</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public Name getName() { return super.<Name>getCharacteristicAccess(Name.class, "Header$Name"); }
	/**
	 * <b>MaterialType</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public MaterialType getMaterialType() { return super.<MaterialType>getCharacteristicAccess(MaterialType.class, "Header$MaterialType"); }
	/**
	 * <b>GrossWeight</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public GrossWeight getGrossWeight() { return super.<GrossWeight>getCharacteristicAccess(GrossWeight.class, "Header$GrossWeight"); }
	/**
	 * <b>NetWeight</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public NetWeight getNetWeight() { return super.<NetWeight>getCharacteristicAccess(NetWeight.class, "Header$NetWeight"); }
	/**
	 * <b>Volume</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Volume getVolume() { return super.<Volume>getCharacteristicAccess(Volume.class, "Header$Volume"); }
	/**
	 * <b>AllowedPackagingWeight</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public AllowedPackagingWeight getAllowedPackagingWeight() { return super.<AllowedPackagingWeight>getCharacteristicAccess(AllowedPackagingWeight.class, "Header$AllowedPackagingWeight"); }
	/**
	 * <b>AllowedPackagingVolume</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public AllowedPackagingVolume getAllowedPackagingVolume() { return super.<AllowedPackagingVolume>getCharacteristicAccess(AllowedPackagingVolume.class, "Header$AllowedPackagingVolume"); }
	/**
	 * <b>ContainerRequiements</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public ContainerRequiements getContainerRequiements() { return super.<ContainerRequiements>getCharacteristicAccess(ContainerRequiements.class, "Header$ContainerRequiements"); }
	/**
	 * <b>StorageConditions</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public StorageConditions getStorageConditions() { return super.<StorageConditions>getCharacteristicAccess(StorageConditions.class, "Header$StorageConditions"); }
	/**
	 * <b>TransportationGroup</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public TransportationGroup getTransportationGroup() { return super.<TransportationGroup>getCharacteristicAccess(TransportationGroup.class, "Header$TransportationGroup"); }
	/**
	 * <b>LabelType</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public LabelType getLabelType() { return super.<LabelType>getCharacteristicAccess(LabelType.class, "Header$LabelType"); }
	/**
	 * <b>LabelForm</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public LabelForm getLabelForm() { return super.<LabelForm>getCharacteristicAccess(LabelForm.class, "Header$LabelForm"); }
	/**
	 * <b>BaseUnitOfMeasure</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public BaseUnitOfMeasure getBaseUnitOfMeasure() { return super.<BaseUnitOfMeasure>getCharacteristicAccess(BaseUnitOfMeasure.class, "Header$BaseUnitOfMeasure"); }
	/**
	 * <b>AlternativeUnitOfMeasures</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType TABLE_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public AlternativeUnitOfMeasures getAlternativeUnitOfMeasures() { return super.<AlternativeUnitOfMeasures>getTableAccessCV(AlternativeUnitOfMeasures.class, "Header$AlternativeUnitOfMeasures"); }
	/**
	 * <b>Plants</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType NODE_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants getPlants() { return super.<Plants>getNodeAccess(Plants.class, "Header.Plants"); }
	/**
	 * <b>PlantId</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType NODE_KEY_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.PlantId getPlants$PlantId() { return super.<Plants.PlantId>getKeyAccess(Plants.PlantId.class, "Header.Plants$PlantId"); }
	/**
	 * <b>ProductionSupervisor</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.ProductionSupervisor getPlants$ProductionSupervisor() { return super.<Plants.ProductionSupervisor>getCharacteristicAccess(Plants.ProductionSupervisor.class, "Header.Plants$ProductionSupervisor"); }
	/**
	 * <b>ProcessingTime</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.ProcessingTime getPlants$ProcessingTime() { return super.<Plants.ProcessingTime>getCharacteristicAccess(Plants.ProcessingTime.class, "Header.Plants$ProcessingTime"); }
	/**
	 * <b>BaseQuantity</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.BaseQuantity getPlants$BaseQuantity() { return super.<Plants.BaseQuantity>getCharacteristicAccess(Plants.BaseQuantity.class, "Header.Plants$BaseQuantity"); }
	/**
	 * <b>StorageCostsIndicator</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.StorageCostsIndicator getPlants$StorageCostsIndicator() { return super.<Plants.StorageCostsIndicator>getCharacteristicAccess(Plants.StorageCostsIndicator.class, "Header.Plants$StorageCostsIndicator"); }
	/**
	 * <b>ValuationCategory</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.ValuationCategory getPlants$ValuationCategory() { return super.<Plants.ValuationCategory>getCharacteristicAccess(Plants.ValuationCategory.class, "Header.Plants$ValuationCategory"); }
	/**
	 * <b>MinimumLotSize</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.MinimumLotSize getPlants$MinimumLotSize() { return super.<Plants.MinimumLotSize>getCharacteristicAccess(Plants.MinimumLotSize.class, "Header.Plants$MinimumLotSize"); }
	/**
	 * <b>MaximumLotSize</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.MaximumLotSize getPlants$MaximumLotSize() { return super.<Plants.MaximumLotSize>getCharacteristicAccess(Plants.MaximumLotSize.class, "Header.Plants$MaximumLotSize"); }
	/**
	 * <b>FixedLotSize</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.FixedLotSize getPlants$FixedLotSize() { return super.<Plants.FixedLotSize>getCharacteristicAccess(Plants.FixedLotSize.class, "Header.Plants$FixedLotSize"); }
	/**
	 * <b>StorageLocations</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType NODE_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.StorageLocations getPlants$StorageLocations() { return super.<Plants.StorageLocations>getNodeAccess(Plants.StorageLocations.class, "Header.Plants.StorageLocations"); }
	/**
	 * <b>StorageLocationId</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType NODE_KEY_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.StorageLocations.StorageLocationId getPlants$StorageLocations$StorageLocationId() { return super.<Plants.StorageLocations.StorageLocationId>getKeyAccess(Plants.StorageLocations.StorageLocationId.class, "Header.Plants.StorageLocations$StorageLocationId"); }
	/**
	 * <b>TotalStock</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.StorageLocations.TotalStock getPlants$StorageLocations$TotalStock() { return super.<Plants.StorageLocations.TotalStock>getCharacteristicAccess(Plants.StorageLocations.TotalStock.class, "Header.Plants.StorageLocations$TotalStock"); }
	/**
	 * <b>StockInQualityInspection</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.StorageLocations.StockInQualityInspection getPlants$StorageLocations$StockInQualityInspection() { return super.<Plants.StorageLocations.StockInQualityInspection>getCharacteristicAccess(Plants.StorageLocations.StockInQualityInspection.class, "Header.Plants.StorageLocations$StockInQualityInspection"); }
	/**
	 * <b>BlockedStock</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public Plants.StorageLocations.BlockedStock getPlants$StorageLocations$BlockedStock() { return super.<Plants.StorageLocations.BlockedStock>getCharacteristicAccess(Plants.StorageLocations.BlockedStock.class, "Header.Plants.StorageLocations$BlockedStock"); }
	/**
	 * <b>SalesOrganizations</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType NODE_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations getSalesOrganizations() { return super.<SalesOrganizations>getNodeAccess(SalesOrganizations.class, "Header.SalesOrganizations"); }
	/**
	 * <b>SalesOrganization</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType NODE_KEY_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.SalesOrganization getSalesOrganizations$SalesOrganization() { return super.<SalesOrganizations.SalesOrganization>getKeyAccess(SalesOrganizations.SalesOrganization.class, "Header.SalesOrganizations$SalesOrganization"); }
	/**
	 * <b>DistributionChannel</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType NODE_KEY_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.DistributionChannel getSalesOrganizations$DistributionChannel() { return super.<SalesOrganizations.DistributionChannel>getKeyAccess(SalesOrganizations.DistributionChannel.class, "Header.SalesOrganizations$DistributionChannel"); }
	/**
	 * <b>StatisticsGroup</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.StatisticsGroup getSalesOrganizations$StatisticsGroup() { return super.<SalesOrganizations.StatisticsGroup>getCharacteristicAccess(SalesOrganizations.StatisticsGroup.class, "Header.SalesOrganizations$StatisticsGroup"); }
	/**
	 * <b>VolumeRebateGroup</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.VolumeRebateGroup getSalesOrganizations$VolumeRebateGroup() { return super.<SalesOrganizations.VolumeRebateGroup>getCharacteristicAccess(SalesOrganizations.VolumeRebateGroup.class, "Header.SalesOrganizations$VolumeRebateGroup"); }
	/**
	 * <b>CommissionGroup</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.CommissionGroup getSalesOrganizations$CommissionGroup() { return super.<SalesOrganizations.CommissionGroup>getCharacteristicAccess(SalesOrganizations.CommissionGroup.class, "Header.SalesOrganizations$CommissionGroup"); }
	/**
	 * <b>CashDiscount</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.CashDiscount getSalesOrganizations$CashDiscount() { return super.<SalesOrganizations.CashDiscount>getCharacteristicAccess(SalesOrganizations.CashDiscount.class, "Header.SalesOrganizations$CashDiscount"); }
	/**
	 * <b>DeliveryUnit</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.DeliveryUnit getSalesOrganizations$DeliveryUnit() { return super.<SalesOrganizations.DeliveryUnit>getCharacteristicAccess(SalesOrganizations.DeliveryUnit.class, "Header.SalesOrganizations$DeliveryUnit"); }
	/**
	 * <b>SalesUnit</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.SalesUnit getSalesOrganizations$SalesUnit() { return super.<SalesOrganizations.SalesUnit>getCharacteristicAccess(SalesOrganizations.SalesUnit.class, "Header.SalesOrganizations$SalesUnit"); }
	/**
	 * <b>MinimumOrderQuantity</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.MinimumOrderQuantity getSalesOrganizations$MinimumOrderQuantity() { return super.<SalesOrganizations.MinimumOrderQuantity>getCharacteristicAccess(SalesOrganizations.MinimumOrderQuantity.class, "Header.SalesOrganizations$MinimumOrderQuantity"); }
	/**
	 * <b>MinimumDeliveryQuantity</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.MinimumDeliveryQuantity getSalesOrganizations$MinimumDeliveryQuantity() { return super.<SalesOrganizations.MinimumDeliveryQuantity>getCharacteristicAccess(SalesOrganizations.MinimumDeliveryQuantity.class, "Header.SalesOrganizations$MinimumDeliveryQuantity"); }
	/**
	 * <b>ItemCategoryGroup</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.ItemCategoryGroup getSalesOrganizations$ItemCategoryGroup() { return super.<SalesOrganizations.ItemCategoryGroup>getCharacteristicAccess(SalesOrganizations.ItemCategoryGroup.class, "Header.SalesOrganizations$ItemCategoryGroup"); }
	/**
	 * <b>DeliveringPlant</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.DeliveringPlant getSalesOrganizations$DeliveringPlant() { return super.<SalesOrganizations.DeliveringPlant>getCharacteristicAccess(SalesOrganizations.DeliveringPlant.class, "Header.SalesOrganizations$DeliveringPlant"); }
	/**
	 * <b>Pricing</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.Pricing getSalesOrganizations$Pricing() { return super.<SalesOrganizations.Pricing>getCharacteristicAccess(SalesOrganizations.Pricing.class, "Header.SalesOrganizations$Pricing"); }
	/**
	 * <b>ListedInStoreFrom</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.ListedInStoreFrom getSalesOrganizations$ListedInStoreFrom() { return super.<SalesOrganizations.ListedInStoreFrom>getCharacteristicAccess(SalesOrganizations.ListedInStoreFrom.class, "Header.SalesOrganizations$ListedInStoreFrom"); }
	/**
	 * <b>ListedInStoreTo</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.ListedInStoreTo getSalesOrganizations$ListedInStoreTo() { return super.<SalesOrganizations.ListedInStoreTo>getCharacteristicAccess(SalesOrganizations.ListedInStoreTo.class, "Header.SalesOrganizations$ListedInStoreTo"); }
	/**
	 * <b>SalesTexts</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType TABLE_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	public SalesOrganizations.SalesTexts getSalesOrganizations$SalesTexts() { return super.<SalesOrganizations.SalesTexts>getTableAccessCV(SalesOrganizations.SalesTexts.class, "Header.SalesOrganizations$SalesTexts"); }

	@SuppressWarnings("unchecked")
	public final static class MaterialNumber extends CharacteristicAccess {
		protected MaterialNumber(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class Name extends CharacteristicAccess {
		protected Name(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class MaterialType extends CharacteristicAccess {
		protected MaterialType(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public IKeywordEntry getCurrentValue() { return super.<IKeywordEntry>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class GrossWeight extends CharacteristicAccess {
		protected GrossWeight(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class NetWeight extends CharacteristicAccess {
		protected NetWeight(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class Volume extends CharacteristicAccess {
		protected Volume(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class AllowedPackagingWeight extends CharacteristicAccess {
		protected AllowedPackagingWeight(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class AllowedPackagingVolume extends CharacteristicAccess {
		protected AllowedPackagingVolume(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class ContainerRequiements extends CharacteristicAccess {
		protected ContainerRequiements(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class StorageConditions extends CharacteristicAccess {
		protected StorageConditions(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class TransportationGroup extends CharacteristicAccess {
		protected TransportationGroup(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class LabelType extends CharacteristicAccess {
		protected LabelType(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class LabelForm extends CharacteristicAccess {
		protected LabelForm(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	@SuppressWarnings("unchecked")
	public final static class BaseUnitOfMeasure extends CharacteristicAccess {
		protected BaseUnitOfMeasure(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getCurrentValue() { return super.<String>getCurrentValue(); }
		public void setCurrentValue(String value) { super.setCurrentValue(value); }
	}

	public final static class AlternativeUnitOfMeasures extends TableAccessCurrent<AlternativeUnitOfMeasures.Current> {
		protected AlternativeUnitOfMeasures(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }

		public final static class Current extends RowAccess {
			protected Current(BPMOAccess<?> bpmoAccess, IRow row) { super(bpmoAccess, row); }
			public AlternativeUnitOfMeasure getAlternativeUnitOfMeasure() { return super.<AlternativeUnitOfMeasure>getCellAccess(AlternativeUnitOfMeasure.class, "Header$AlternativeUnitOfMeasures$AlternativeUnitOfMeasure", "AlternativeUnitOfMeasure"); }
			public NumeratorConversion getNumeratorConversion() { return super.<NumeratorConversion>getCellAccess(NumeratorConversion.class, "Header$AlternativeUnitOfMeasures$NumeratorConversion", "NumeratorConversion"); }
			public DenominatorConversion getDenominatorConversion() { return super.<DenominatorConversion>getCellAccess(DenominatorConversion.class, "Header$AlternativeUnitOfMeasures$DenominatorConversion", "DenominatorConversion"); }
			public void erase() { super.eraseRow(); }

			@SuppressWarnings("unchecked")
			public final static class AlternativeUnitOfMeasure extends CellAccess {
				protected AlternativeUnitOfMeasure(BPMOAccess<?> bpmoAccess, RowAccess rowAccess, String path, String id) { super(bpmoAccess, rowAccess, path, id); }
				public String getValue() { return super.<String>getValue(); }
				public void setValue(String value) { super.setValue(value); }
			}

			@SuppressWarnings("unchecked")
			public final static class NumeratorConversion extends CellAccess {
				protected NumeratorConversion(BPMOAccess<?> bpmoAccess, RowAccess rowAccess, String path, String id) { super(bpmoAccess, rowAccess, path, id); }
				public String getValue() { return super.<String>getValue(); }
				public void setValue(String value) { super.setValue(value); }
			}

			@SuppressWarnings("unchecked")
			public final static class DenominatorConversion extends CellAccess {
				protected DenominatorConversion(BPMOAccess<?> bpmoAccess, RowAccess rowAccess, String path, String id) { super(bpmoAccess, rowAccess, path, id); }
				public String getValue() { return super.<String>getValue(); }
				public void setValue(String value) { super.setValue(value); }
			}
		}
	}

	public final static class Plants extends NodeAccess<MaterialBPMOAccess> {
		protected Plants(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }

		public Plants.PlantId createPlantId(String value) { return super.<Plants.PlantId>createKeyAccess(Plants.PlantId.class, "Header.Plants$PlantId", value); }

		public MaterialBPMOAccess createAndAddElement(Plants.PlantId plantId) { return super.createAndAddElement(plantId); }

		@SuppressWarnings("unchecked")
		public final static class PlantId extends NodeKeyAccess {
			protected PlantId(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			protected PlantId(BPMOAccess<?> bpmoAccess, String path, INodeElementKey nodeElementKey, Object value) { super(bpmoAccess, path, nodeElementKey, value); }
			public String getValue() { return super.<String>getValue(); }
		}

		@SuppressWarnings("unchecked")
		public final static class ProductionSupervisor extends CharacteristicAccess {
			protected ProductionSupervisor(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class ProcessingTime extends CharacteristicAccess {
			protected ProcessingTime(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class BaseQuantity extends CharacteristicAccess {
			protected BaseQuantity(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class StorageCostsIndicator extends CharacteristicAccess {
			protected StorageCostsIndicator(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class ValuationCategory extends CharacteristicAccess {
			protected ValuationCategory(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class MinimumLotSize extends CharacteristicAccess {
			protected MinimumLotSize(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class MaximumLotSize extends CharacteristicAccess {
			protected MaximumLotSize(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class FixedLotSize extends CharacteristicAccess {
			protected FixedLotSize(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		public final static class StorageLocations extends NodeAccess<MaterialBPMOAccess> {
			protected StorageLocations(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }

			public Plants.StorageLocations.StorageLocationId createStorageLocationId(String value) { return super.<Plants.StorageLocations.StorageLocationId>createKeyAccess(Plants.StorageLocations.StorageLocationId.class, "Header.Plants.StorageLocations$StorageLocationId", value); }

			public MaterialBPMOAccess createAndAddElement(Plants.StorageLocations.StorageLocationId storageLocationId) { return super.createAndAddElement(storageLocationId); }

			@SuppressWarnings("unchecked")
			public final static class StorageLocationId extends NodeKeyAccess {
				protected StorageLocationId(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
				protected StorageLocationId(BPMOAccess<?> bpmoAccess, String path, INodeElementKey nodeElementKey, Object value) { super(bpmoAccess, path, nodeElementKey, value); }
				public String getValue() { return super.<String>getValue(); }
			}

			@SuppressWarnings("unchecked")
			public final static class TotalStock extends CharacteristicAccess {
				protected TotalStock(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
				public String getCurrentValue() { return super.<String>getCurrentValue(); }
				public void setCurrentValue(String value) { super.setCurrentValue(value); }
			}

			@SuppressWarnings("unchecked")
			public final static class StockInQualityInspection extends CharacteristicAccess {
				protected StockInQualityInspection(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
				public String getCurrentValue() { return super.<String>getCurrentValue(); }
				public void setCurrentValue(String value) { super.setCurrentValue(value); }
			}

			@SuppressWarnings("unchecked")
			public final static class BlockedStock extends CharacteristicAccess {
				protected BlockedStock(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
				public String getCurrentValue() { return super.<String>getCurrentValue(); }
				public void setCurrentValue(String value) { super.setCurrentValue(value); }
			}
		}
	}

	public final static class SalesOrganizations extends NodeAccess<MaterialBPMOAccess> {
		protected SalesOrganizations(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }

		public SalesOrganizations.SalesOrganization createSalesOrganization(String value) { return super.<SalesOrganizations.SalesOrganization>createKeyAccess(SalesOrganizations.SalesOrganization.class, "Header.SalesOrganizations$SalesOrganization", value); }

		public SalesOrganizations.DistributionChannel createDistributionChannel(String value) { return super.<SalesOrganizations.DistributionChannel>createKeyAccess(SalesOrganizations.DistributionChannel.class, "Header.SalesOrganizations$DistributionChannel", value); }

		public MaterialBPMOAccess createAndAddElement(SalesOrganizations.SalesOrganization salesOrganization, SalesOrganizations.DistributionChannel distributionChannel) { return super.createAndAddElement(salesOrganization, distributionChannel); }

		@SuppressWarnings("unchecked")
		public final static class SalesOrganization extends NodeKeyAccess {
			protected SalesOrganization(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			protected SalesOrganization(BPMOAccess<?> bpmoAccess, String path, INodeElementKey nodeElementKey, Object value) { super(bpmoAccess, path, nodeElementKey, value); }
			public String getValue() { return super.<String>getValue(); }
		}

		@SuppressWarnings("unchecked")
		public final static class DistributionChannel extends NodeKeyAccess {
			protected DistributionChannel(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			protected DistributionChannel(BPMOAccess<?> bpmoAccess, String path, INodeElementKey nodeElementKey, Object value) { super(bpmoAccess, path, nodeElementKey, value); }
			public String getValue() { return super.<String>getValue(); }
		}

		@SuppressWarnings("unchecked")
		public final static class StatisticsGroup extends CharacteristicAccess {
			protected StatisticsGroup(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class VolumeRebateGroup extends CharacteristicAccess {
			protected VolumeRebateGroup(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class CommissionGroup extends CharacteristicAccess {
			protected CommissionGroup(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class CashDiscount extends CharacteristicAccess {
			protected CashDiscount(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class DeliveryUnit extends CharacteristicAccess {
			protected DeliveryUnit(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class SalesUnit extends CharacteristicAccess {
			protected SalesUnit(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class MinimumOrderQuantity extends CharacteristicAccess {
			protected MinimumOrderQuantity(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class MinimumDeliveryQuantity extends CharacteristicAccess {
			protected MinimumDeliveryQuantity(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class ItemCategoryGroup extends CharacteristicAccess {
			protected ItemCategoryGroup(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class DeliveringPlant extends CharacteristicAccess {
			protected DeliveringPlant(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class Pricing extends CharacteristicAccess {
			protected Pricing(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class ListedInStoreFrom extends CharacteristicAccess {
			protected ListedInStoreFrom(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class ListedInStoreTo extends CharacteristicAccess {
			protected ListedInStoreTo(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
			public String getCurrentValue() { return super.<String>getCurrentValue(); }
			public void setCurrentValue(String value) { super.setCurrentValue(value); }
		}

		public final static class SalesTexts extends TableAccessCurrent<SalesTexts.Current> {
			protected SalesTexts(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }

			public final static class Current extends RowAccess {
				protected Current(BPMOAccess<?> bpmoAccess, IRow row) { super(bpmoAccess, row); }
				public Language getLanguage() { return super.<Language>getCellAccess(Language.class, "Header.SalesOrganizations$SalesTexts$Language", "Language"); }
				public Text getText() { return super.<Text>getCellAccess(Text.class, "Header.SalesOrganizations$SalesTexts$Text", "Text"); }
				public void erase() { super.eraseRow(); }

				@SuppressWarnings("unchecked")
				public final static class Language extends CellAccess {
					protected Language(BPMOAccess<?> bpmoAccess, RowAccess rowAccess, String path, String id) { super(bpmoAccess, rowAccess, path, id); }
					public String getValue() { return super.<String>getValue(); }
					public void setValue(String value) { super.setValue(value); }
				}

				@SuppressWarnings("unchecked")
				public final static class Text extends CellAccess {
					protected Text(BPMOAccess<?> bpmoAccess, RowAccess rowAccess, String path, String id) { super(bpmoAccess, rowAccess, path, id); }
					public String getValue() { return super.<String>getValue(); }
					public void setValue(String value) { super.setValue(value); }
				}
			}
		}
	}

}
