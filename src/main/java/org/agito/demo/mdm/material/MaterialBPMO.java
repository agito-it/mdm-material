package org.agito.demo.mdm.material;


import de.agito.cps.core.bpmo.ControlType;
import de.agito.cps.core.bpmo.DataTypeFactory;
import de.agito.cps.core.bpmo.IEnumInspector;
import de.agito.cps.core.bpmo.api.enums.IBODataElement;
import de.agito.cps.core.bpmo.api.enums.IBOId;
import de.agito.cps.core.bpmo.api.enums.IBONode;


/**
 * Enum for Header.
 *
 * @author Jörg Burmeister
 */
public enum MaterialBPMO implements IBODataElement {

	/**
	 * <b>Material Number</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	MaterialNumber("MaterialNumber", "Header$MaterialNumber", ControlType.INTERACTIVE),

	/**
	 * <b>Name</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	Name("Name", "Header$Name", ControlType.INTERACTIVE),

	/**
	 * <b>Material Type</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	MaterialType("MaterialType", "Header$MaterialType", ControlType.INTERACTIVE),

	/**
	 * <b>Gross Weight</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	GrossWeight("GrossWeight", "Header$GrossWeight", ControlType.DEFAULT),

	/**
	 * <b>Net Weight</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	NetWeight("NetWeight", "Header$NetWeight", ControlType.DEFAULT),

	/**
	 * <b>Volume</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	Volume("Volume", "Header$Volume", ControlType.DEFAULT),

	/**
	 * <b>Allowed Packaging Weight</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	AllowedPackagingWeight("AllowedPackagingWeight", "Header$AllowedPackagingWeight", ControlType.DEFAULT),

	/**
	 * <b>Allowed Packaging Volume</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	AllowedPackagingVolume("AllowedPackagingVolume", "Header$AllowedPackagingVolume", ControlType.DEFAULT),

	/**
	 * <b>Container Requiements</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	ContainerRequiements("ContainerRequiements", "Header$ContainerRequiements", ControlType.DEFAULT),

	/**
	 * <b>Storage Conditions</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	StorageConditions("StorageConditions", "Header$StorageConditions", ControlType.DEFAULT),

	/**
	 * <b>Transportation Group</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	TransportationGroup("TransportationGroup", "Header$TransportationGroup", ControlType.DEFAULT),

	/**
	 * <b>Label Type</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	LabelType("LabelType", "Header$LabelType", ControlType.DEFAULT),

	/**
	 * <b>Label Form</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	LabelForm("LabelForm", "Header$LabelForm", ControlType.DEFAULT),

	/**
	 * <b>Base Unit Of Measure</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	BaseUnitOfMeasure("BaseUnitOfMeasure", "Header$BaseUnitOfMeasure", ControlType.DEFAULT),

	/**
	 * <b>Alternative Unit Of Measures</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType TABLE_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	AlternativeUnitOfMeasures("AlternativeUnitOfMeasures", "Header$AlternativeUnitOfMeasures", ControlType.DEFAULT),

	/**
	 * <b>Alternative Unit Of Measure</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	AlternativeUnitOfMeasures$AlternativeUnitOfMeasure("AlternativeUnitOfMeasure", "Header$AlternativeUnitOfMeasures$AlternativeUnitOfMeasure", ControlType.DEFAULT),

	/**
	 * <b>Numerator Conversion</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	AlternativeUnitOfMeasures$NumeratorConversion("NumeratorConversion", "Header$AlternativeUnitOfMeasures$NumeratorConversion", ControlType.DEFAULT),

	/**
	 * <b>Denominator Conversion</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	AlternativeUnitOfMeasures$DenominatorConversion("DenominatorConversion", "Header$AlternativeUnitOfMeasures$DenominatorConversion", ControlType.DEFAULT);

	/**
	 * <b>BPMO Identifier</b>
	 */
	public final static IBOId $BPMO = new IBOId.BOId("MaterialBPMO");

	/**
	 * <b>Node Identifier</b>
	 */
	public final static IBONode $ID = new IBONode.BONode("Header", "Header", ControlType.DEFAULT);

	private final static IEnumInspector ENUM_INSPECTOR = DataTypeFactory.getInstance().createEnumInspector(MaterialBPMO.class);
	private MaterialBPMO(String id, String path, ControlType controlType) { this.id = id; this.path = path; this.controlType = controlType; }
	private String id;
	public String getId() { return id; }
	private String path;
	public String getPath() { return path; }
	private ControlType controlType;
	public ControlType getControlType() { return controlType; }

	/**
	 * Retrieve a content element for a given path.
	 * 
	 * @return
	 *             Element requested or {@link IEnumInspector}.UNDEFINED_DATA if element does not exist. 
	 * @throws ClassCastException
	 *             when the corresponding element for given path is not a content element.
	 */
	public static IBODataElement getIBODataElementByPath(String path) throws ClassCastException { return ENUM_INSPECTOR.getIBODataElementByPath(path); }

	/**
	 * Retrieve a node for a given path.
	 * 
	 * @return
	 *             Node requested or {@link IEnumInspector}.UNDEFINED_NODE if node does not exist. 
	 * @throws ClassCastException
	 *             when the corresponding element for given path is not a node.
	 */ 
	public static IBONode getIBONodeByPath(String path) throws ClassCastException { return ENUM_INSPECTOR.getIBONodeByPath(path); }

	public static enum Plants implements IBODataElement {

		/**
		 * <b>Plant Id</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType NODE_KEY_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		PlantId("PlantId", "Header.Plants$PlantId", ControlType.DEFAULT),

		/**
		 * <b>Production Supervisor</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		ProductionSupervisor("ProductionSupervisor", "Header.Plants$ProductionSupervisor", ControlType.DEFAULT),

		/**
		 * <b>Processing Time</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		ProcessingTime("ProcessingTime", "Header.Plants$ProcessingTime", ControlType.DEFAULT),

		/**
		 * <b>Base Quantity</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		BaseQuantity("BaseQuantity", "Header.Plants$BaseQuantity", ControlType.DEFAULT),

		/**
		 * <b>Storage Costs Indicator</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		StorageCostsIndicator("StorageCostsIndicator", "Header.Plants$StorageCostsIndicator", ControlType.DEFAULT),

		/**
		 * <b>Valuation Category</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		ValuationCategory("ValuationCategory", "Header.Plants$ValuationCategory", ControlType.DEFAULT),

		/**
		 * <b>Minimum Lot Size</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		MinimumLotSize("MinimumLotSize", "Header.Plants$MinimumLotSize", ControlType.DEFAULT),

		/**
		 * <b>Maximum Lot Size</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		MaximumLotSize("MaximumLotSize", "Header.Plants$MaximumLotSize", ControlType.DEFAULT),

		/**
		 * <b>Fixed Lot Size</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		FixedLotSize("FixedLotSize", "Header.Plants$FixedLotSize", ControlType.DEFAULT);

		/**
		 * <b>Node Identifier</b>
		 */
		public final static IBONode $ID = new IBONode.BONode("Plants", "Header.Plants", ControlType.DEFAULT);

		private Plants(String id, String path, ControlType controlType) { this.id = id; this.path = path; this.controlType = controlType; }
		private String id;
		public String getId() { return id; }
		private String path;
		public String getPath() { return path; }
		private ControlType controlType;
		public ControlType getControlType() { return controlType; }

		public static enum StorageLocations implements IBODataElement {

			/**
			 * <b>Storage Location Id</b>
			 * <p>
			 * <i>{@link DefinitionArtifactType NODE_KEY_DEFINITION}<i><br>
			 * <i>{@link DataType STRING}<i><br>
			 * <i>{@link ControlType DEFAULT}<i><br>
			 */
			StorageLocationId("StorageLocationId", "Header.Plants.StorageLocations$StorageLocationId", ControlType.DEFAULT),

			/**
			 * <b>Total Stock</b>
			 * <p>
			 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
			 * <i>{@link DataType STRING}<i><br>
			 * <i>{@link ControlType DEFAULT}<i><br>
			 */
			TotalStock("TotalStock", "Header.Plants.StorageLocations$TotalStock", ControlType.DEFAULT),

			/**
			 * <b>Stock In Quality Inspection</b>
			 * <p>
			 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
			 * <i>{@link DataType STRING}<i><br>
			 * <i>{@link ControlType DEFAULT}<i><br>
			 */
			StockInQualityInspection("StockInQualityInspection", "Header.Plants.StorageLocations$StockInQualityInspection", ControlType.DEFAULT),

			/**
			 * <b>Blocked Stock</b>
			 * <p>
			 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
			 * <i>{@link DataType STRING}<i><br>
			 * <i>{@link ControlType DEFAULT}<i><br>
			 */
			BlockedStock("BlockedStock", "Header.Plants.StorageLocations$BlockedStock", ControlType.DEFAULT);

			/**
			 * <b>Node Identifier</b>
			 */
			public final static IBONode $ID = new IBONode.BONode("StorageLocations", "Header.Plants.StorageLocations", ControlType.DEFAULT);

			private StorageLocations(String id, String path, ControlType controlType) { this.id = id; this.path = path; this.controlType = controlType; }
			private String id;
			public String getId() { return id; }
			private String path;
			public String getPath() { return path; }
			private ControlType controlType;
			public ControlType getControlType() { return controlType; }

		}

	}

	public static enum SalesOrganizations implements IBODataElement {

		/**
		 * <b>Sales Organization</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType NODE_KEY_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		SalesOrganization("SalesOrganization", "Header.SalesOrganizations$SalesOrganization", ControlType.DEFAULT),

		/**
		 * <b>Distribution Channel</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType NODE_KEY_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		DistributionChannel("DistributionChannel", "Header.SalesOrganizations$DistributionChannel", ControlType.DEFAULT),

		/**
		 * <b>Statistics Group</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		StatisticsGroup("StatisticsGroup", "Header.SalesOrganizations$StatisticsGroup", ControlType.DEFAULT),

		/**
		 * <b>Volume Rebate Group</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		VolumeRebateGroup("VolumeRebateGroup", "Header.SalesOrganizations$VolumeRebateGroup", ControlType.DEFAULT),

		/**
		 * <b>Commission Group</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		CommissionGroup("CommissionGroup", "Header.SalesOrganizations$CommissionGroup", ControlType.DEFAULT),

		/**
		 * <b>Cash Discount</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		CashDiscount("CashDiscount", "Header.SalesOrganizations$CashDiscount", ControlType.DEFAULT),

		/**
		 * <b>Delivery Unit</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		DeliveryUnit("DeliveryUnit", "Header.SalesOrganizations$DeliveryUnit", ControlType.DEFAULT),

		/**
		 * <b>Sales Unit</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		SalesUnit("SalesUnit", "Header.SalesOrganizations$SalesUnit", ControlType.DEFAULT),

		/**
		 * <b>Minimum Order Quantity</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		MinimumOrderQuantity("MinimumOrderQuantity", "Header.SalesOrganizations$MinimumOrderQuantity", ControlType.DEFAULT),

		/**
		 * <b>Minimum Delivery Quantity</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		MinimumDeliveryQuantity("MinimumDeliveryQuantity", "Header.SalesOrganizations$MinimumDeliveryQuantity", ControlType.DEFAULT),

		/**
		 * <b>Item Category Group</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		ItemCategoryGroup("ItemCategoryGroup", "Header.SalesOrganizations$ItemCategoryGroup", ControlType.DEFAULT),

		/**
		 * <b>Delivering Plant</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		DeliveringPlant("DeliveringPlant", "Header.SalesOrganizations$DeliveringPlant", ControlType.DEFAULT),

		/**
		 * <b>Pricing</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		Pricing("Pricing", "Header.SalesOrganizations$Pricing", ControlType.DEFAULT),

		/**
		 * <b>Listed In Store From</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		ListedInStoreFrom("ListedInStoreFrom", "Header.SalesOrganizations$ListedInStoreFrom", ControlType.DEFAULT),

		/**
		 * <b>Listed In Store To</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		ListedInStoreTo("ListedInStoreTo", "Header.SalesOrganizations$ListedInStoreTo", ControlType.DEFAULT),

		/**
		 * <b>Sales Texts</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType TABLE_DEFINITION}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		SalesTexts("SalesTexts", "Header.SalesOrganizations$SalesTexts", ControlType.DEFAULT),

		/**
		 * <b>Language</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		SalesTexts$Language("Language", "Header.SalesOrganizations$SalesTexts$Language", ControlType.DEFAULT),

		/**
		 * <b>Text</b>
		 * <p>
		 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType DEFAULT}<i><br>
		 */
		SalesTexts$Text("Text", "Header.SalesOrganizations$SalesTexts$Text", ControlType.DEFAULT);

		/**
		 * <b>Node Identifier</b>
		 */
		public final static IBONode $ID = new IBONode.BONode("SalesOrganizations", "Header.SalesOrganizations", ControlType.DEFAULT);

		private SalesOrganizations(String id, String path, ControlType controlType) { this.id = id; this.path = path; this.controlType = controlType; }
		private String id;
		public String getId() { return id; }
		private String path;
		public String getPath() { return path; }
		private ControlType controlType;
		public ControlType getControlType() { return controlType; }

	}

}

