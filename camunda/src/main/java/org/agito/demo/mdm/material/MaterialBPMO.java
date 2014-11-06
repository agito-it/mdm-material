package org.agito.demo.mdm.material;


import de.agito.cps.core.bpmo.ControlType;
import de.agito.cps.core.bpmo.DataTypeFactory;
import de.agito.cps.core.bpmo.IEnumInspector;
import de.agito.cps.core.bpmo.api.enums.IBODataElement;
import de.agito.cps.core.bpmo.api.enums.IBOId;
import de.agito.cps.core.bpmo.api.enums.IBONode;


/**
 * Enum for MaterialBPMO.
 *
 * @author joerg.burmeister
 */
public enum MaterialBPMO implements IBODataElement {

	/**
	 * <b>Material Number</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	MaterialNumber("MaterialNumber", "Header$MaterialNumber", ControlType.INTERACTIVE),

	/**
	 * <b>Name</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	Name("Name", "Header$Name", ControlType.INTERACTIVE),

	/**
	 * <b>Material Type</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	MaterialType("MaterialType", "Header$MaterialType", ControlType.INTERACTIVE),

	/**
	 * <b>Gross Weight</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	GrossWeight("GrossWeight", "Header$GrossWeight", ControlType.INTERACTIVE),

	/**
	 * <b>Net Weight</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	NetWeight("NetWeight", "Header$NetWeight", ControlType.INTERACTIVE),

	/**
	 * <b>Volume</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	Volume("Volume", "Header$Volume", ControlType.INTERACTIVE),

	/**
	 * <b>Allowed Packaging Weight</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	AllowedPackagingWeight("AllowedPackagingWeight", "Header$AllowedPackagingWeight", ControlType.INTERACTIVE),

	/**
	 * <b>Allowed Packaging Volume</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	AllowedPackagingVolume("AllowedPackagingVolume", "Header$AllowedPackagingVolume", ControlType.INTERACTIVE),

	/**
	 * <b>Container Requiements</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	ContainerRequiements("ContainerRequiements", "Header$ContainerRequiements", ControlType.INTERACTIVE),

	/**
	 * <b>Storage Conditions</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	StorageConditions("StorageConditions", "Header$StorageConditions", ControlType.INTERACTIVE),

	/**
	 * <b>Transportation Group</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	TransportationGroup("TransportationGroup", "Header$TransportationGroup", ControlType.INTERACTIVE),

	/**
	 * <b>Label Type</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	LabelType("LabelType", "Header$LabelType", ControlType.INTERACTIVE),

	/**
	 * <b>Label Form</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	LabelForm("LabelForm", "Header$LabelForm", ControlType.INTERACTIVE),

	/**
	 * <b>Base Unit Of Measure</b>
	 * <p>
	 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	BaseUnitOfMeasure("BaseUnitOfMeasure", "Header$BaseUnitOfMeasure", ControlType.INTERACTIVE),

	/**
	 * <b>Alternative Unit Of Measures</b>
	 * <p>
	 * <i>{@link ArtifactType TABLE}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	AlternativeUnitOfMeasures("AlternativeUnitOfMeasures", "Header$AlternativeUnitOfMeasures", ControlType.INTERACTIVE),

	/**
	 * <b>Alternative Unit Of Measure</b>
	 * <p>
	 * <i>{@link ArtifactType COLUMN}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	AlternativeUnitOfMeasures$AlternativeUnitOfMeasure("AlternativeUnitOfMeasure", "Header$AlternativeUnitOfMeasures$AlternativeUnitOfMeasure", ControlType.INTERACTIVE),

	/**
	 * <b>Numerator Conversion</b>
	 * <p>
	 * <i>{@link ArtifactType COLUMN}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	AlternativeUnitOfMeasures$NumeratorConversion("NumeratorConversion", "Header$AlternativeUnitOfMeasures$NumeratorConversion", ControlType.INTERACTIVE),

	/**
	 * <b>Denominator Conversion</b>
	 * <p>
	 * <i>{@link ArtifactType COLUMN}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	AlternativeUnitOfMeasures$DenominatorConversion("DenominatorConversion", "Header$AlternativeUnitOfMeasures$DenominatorConversion", ControlType.INTERACTIVE);

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
		 * <i>{@link ArtifactType NODE_KEY}<i><br>
		 * <i>{@link DataType ENUM}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		PlantId("PlantId", "Header.Plants$PlantId", ControlType.INTERACTIVE),

		/**
		 * <b>Production Supervisor</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		ProductionSupervisor("ProductionSupervisor", "Header.Plants$ProductionSupervisor", ControlType.INTERACTIVE),

		/**
		 * <b>Processing Time</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		ProcessingTime("ProcessingTime", "Header.Plants$ProcessingTime", ControlType.INTERACTIVE),

		/**
		 * <b>Base Quantity</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		BaseQuantity("BaseQuantity", "Header.Plants$BaseQuantity", ControlType.INTERACTIVE),

		/**
		 * <b>Storage Costs Indicator</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		StorageCostsIndicator("StorageCostsIndicator", "Header.Plants$StorageCostsIndicator", ControlType.INTERACTIVE),

		/**
		 * <b>Valuation Category</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		ValuationCategory("ValuationCategory", "Header.Plants$ValuationCategory", ControlType.INTERACTIVE),

		/**
		 * <b>Minimum Lot Size</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType NUMERIC}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		MinimumLotSize("MinimumLotSize", "Header.Plants$MinimumLotSize", ControlType.INTERACTIVE),

		/**
		 * <b>Maximum Lot Size</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType NUMERIC}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		MaximumLotSize("MaximumLotSize", "Header.Plants$MaximumLotSize", ControlType.INTERACTIVE),

		/**
		 * <b>Fixed Lot Size</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType NUMERIC}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		FixedLotSize("FixedLotSize", "Header.Plants$FixedLotSize", ControlType.INTERACTIVE);

		/**
		 * <b>Node Identifier</b>
		 */
		public final static IBONode $ID = new IBONode.BONode("Plants", "Header.Plants", ControlType.INTERACTIVE);

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
			 * <i>{@link ArtifactType NODE_KEY}<i><br>
			 * <i>{@link DataType STRING}<i><br>
			 * <i>{@link ControlType INTERACTIVE}<i><br>
			 */
			StorageLocationId("StorageLocationId", "Header.Plants.StorageLocations$StorageLocationId", ControlType.INTERACTIVE),

			/**
			 * <b>Stock In Quality Inspection</b>
			 * <p>
			 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
			 * <i>{@link DataType STRING}<i><br>
			 * <i>{@link ControlType INTERACTIVE}<i><br>
			 */
			StockInQualityInspection("StockInQualityInspection", "Header.Plants.StorageLocations$StockInQualityInspection", ControlType.INTERACTIVE),

			/**
			 * <b>Total Stock</b>
			 * <p>
			 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
			 * <i>{@link DataType STRING}<i><br>
			 * <i>{@link ControlType INTERACTIVE}<i><br>
			 */
			TotalStock("TotalStock", "Header.Plants.StorageLocations$TotalStock", ControlType.INTERACTIVE),

			/**
			 * <b>Blocked Stock</b>
			 * <p>
			 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
			 * <i>{@link DataType STRING}<i><br>
			 * <i>{@link ControlType INTERACTIVE}<i><br>
			 */
			BlockedStock("BlockedStock", "Header.Plants.StorageLocations$BlockedStock", ControlType.INTERACTIVE);

			/**
			 * <b>Node Identifier</b>
			 */
			public final static IBONode $ID = new IBONode.BONode("StorageLocations", "Header.Plants.StorageLocations", ControlType.INTERACTIVE);

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
		 * <i>{@link ArtifactType NODE_KEY}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		SalesOrganization("SalesOrganization", "Header.SalesOrganizations$SalesOrganization", ControlType.INTERACTIVE),

		/**
		 * <b>Distribution Channel</b>
		 * <p>
		 * <i>{@link ArtifactType NODE_KEY}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		DistributionChannel("DistributionChannel", "Header.SalesOrganizations$DistributionChannel", ControlType.INTERACTIVE),

		/**
		 * <b>Statistics Group</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		StatisticsGroup("StatisticsGroup", "Header.SalesOrganizations$StatisticsGroup", ControlType.INTERACTIVE),

		/**
		 * <b>Volume Rebate Group</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		VolumeRebateGroup("VolumeRebateGroup", "Header.SalesOrganizations$VolumeRebateGroup", ControlType.INTERACTIVE),

		/**
		 * <b>Commission Group</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		CommissionGroup("CommissionGroup", "Header.SalesOrganizations$CommissionGroup", ControlType.INTERACTIVE),

		/**
		 * <b>Cash Discount</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		CashDiscount("CashDiscount", "Header.SalesOrganizations$CashDiscount", ControlType.INTERACTIVE),

		/**
		 * <b>Delivery Unit</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		DeliveryUnit("DeliveryUnit", "Header.SalesOrganizations$DeliveryUnit", ControlType.INTERACTIVE),

		/**
		 * <b>Sales Unit</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		SalesUnit("SalesUnit", "Header.SalesOrganizations$SalesUnit", ControlType.INTERACTIVE),

		/**
		 * <b>Minimum Order Quantity</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		MinimumOrderQuantity("MinimumOrderQuantity", "Header.SalesOrganizations$MinimumOrderQuantity", ControlType.INTERACTIVE),

		/**
		 * <b>Minimum Delivery Quantity</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		MinimumDeliveryQuantity("MinimumDeliveryQuantity", "Header.SalesOrganizations$MinimumDeliveryQuantity", ControlType.INTERACTIVE),

		/**
		 * <b>Item Category Group</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		ItemCategoryGroup("ItemCategoryGroup", "Header.SalesOrganizations$ItemCategoryGroup", ControlType.INTERACTIVE),

		/**
		 * <b>Delivering Plant</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		DeliveringPlant("DeliveringPlant", "Header.SalesOrganizations$DeliveringPlant", ControlType.INTERACTIVE),

		/**
		 * <b>Pricing</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		Pricing("Pricing", "Header.SalesOrganizations$Pricing", ControlType.INTERACTIVE),

		/**
		 * <b>Listed In Store From</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		ListedInStoreFrom("ListedInStoreFrom", "Header.SalesOrganizations$ListedInStoreFrom", ControlType.INTERACTIVE),

		/**
		 * <b>Listed In Store To</b>
		 * <p>
		 * <i>{@link ArtifactType CHARACTERISTIC}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		ListedInStoreTo("ListedInStoreTo", "Header.SalesOrganizations$ListedInStoreTo", ControlType.INTERACTIVE),

		/**
		 * <b>Sales Texts</b>
		 * <p>
		 * <i>{@link ArtifactType TABLE}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		SalesTexts("SalesTexts", "Header.SalesOrganizations$SalesTexts", ControlType.INTERACTIVE),

		/**
		 * <b>Language</b>
		 * <p>
		 * <i>{@link ArtifactType COLUMN}<i><br>
		 * <i>{@link DataType ENUM}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		SalesTexts$Language("Language", "Header.SalesOrganizations$SalesTexts$Language", ControlType.INTERACTIVE),

		/**
		 * <b>Text</b>
		 * <p>
		 * <i>{@link ArtifactType COLUMN}<i><br>
		 * <i>{@link DataType STRING}<i><br>
		 * <i>{@link ControlType INTERACTIVE}<i><br>
		 */
		SalesTexts$Text("Text", "Header.SalesOrganizations$SalesTexts$Text", ControlType.INTERACTIVE);

		/**
		 * <b>Node Identifier</b>
		 */
		public final static IBONode $ID = new IBONode.BONode("SalesOrganizations", "Header.SalesOrganizations", ControlType.INTERACTIVE);

		private SalesOrganizations(String id, String path, ControlType controlType) { this.id = id; this.path = path; this.controlType = controlType; }
		private String id;
		public String getId() { return id; }
		private String path;
		public String getPath() { return path; }
		private ControlType controlType;
		public ControlType getControlType() { return controlType; }

	}

}

