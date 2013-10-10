package org.agito.demo.mdm.material;

// @@begin imports
import de.agito.cps.core.logger.Logger;
import de.agito.cps.ui.vaadin.bpmo.BPMOUIController;
import de.agito.cps.ui.vaadin.bpmo.IBPMOUIControllerContext;
import de.agito.cps.ui.vaadin.bpmo.annotation.Navigation;
import de.agito.cps.ui.vaadin.bpmo.annotation.StyleController;
import de.agito.cps.ui.vaadin.bpmo.enums.NavigationType;
import de.agito.cps.ui.vaadin.bpmo.enums.SeparatorStyle;
import de.agito.cps.ui.vaadin.bpmo.enums.UNIT;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayoutManager;
import de.agito.cps.ui.vaadin.bpmo.styles.IDefaultStyleController;
import org.agito.demo.mdm.material.MaterialBPMO;
import org.agito.demo.mdm.material.MaterialBPMOAccess;
import org.agito.demo.mdm.material.MaterialBPMOAction;
import org.agito.demo.mdm.material.MaterialBPMOLanguage;
import org.agito.demo.mdm.material.MaterialBPMOLifecycle;
import org.agito.demo.mdm.material.MaterialBPMOProcessActivity;
// @@end

// @@begin head:uicontroller
/**
 * Vaadin UI Controller for MaterialBPMO.
 * 
 * @author andreas.weise
 */
// @@end
public class MaterialBPMOUIController extends BPMOUIController<MaterialBPMOAccess, MaterialBPMOAction, MaterialBPMOLifecycle, MaterialBPMOLanguage, MaterialBPMOProcessActivity, MaterialBPMO> {

	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(MaterialBPMOUIController.class);

	public MaterialBPMOUIController(IBPMOUIControllerContext context) {
		super(context);
	}

	// @@begin head:init:Header
	/**
	 * Hook for node element init of Header
	 */
	// @@end
	@Navigation(artifact = "Header", type = NavigationType.NODE_ELEMENT_INIT)
	public void cpsInitHeader(MaterialBPMOAccess bpmoAccess) {
		// @@begin body:init:Header
		IFlowLayoutManager layoutManager = styleController.getLayoutManager();
		layoutManager
				.createAndAddSeparator()
				.setTitle(
						bpmoAccess.getMaterialNumber().getCurrentValue() == null ? "Material" : "Material "
								.concat(bpmoAccess.getMaterialNumber().getCurrentValue()))
				.setTitleStyleName(SeparatorStyle.H1).addTitleStyleName(SeparatorStyle.HR)
				.setContentWidth(90, UNIT.PERCENTAGE);
		layoutManager.createAndAddElements(MaterialBPMO.MaterialNumber, MaterialBPMO.Name, MaterialBPMO.MaterialType);
		layoutManager.addLineBreak();
		layoutManager
				.createAndAddGroupContent()
				.createAndAddElements(MaterialBPMO.GrossWeight, MaterialBPMO.NetWeight,
						MaterialBPMO.AllowedPackagingWeight, MaterialBPMO.Volume, MaterialBPMO.AllowedPackagingVolume,
						MaterialBPMO.BaseUnitOfMeasure).setCaption("Dimension").setDimension(3);
		layoutManager.addLineBreak();
		layoutManager.createAndAddSeparator().setHeight(20, UNIT.PIXEL);
		layoutManager.addLineBreak();
		layoutManager.createAndAddGroupContent().fillContent(MaterialBPMO.AlternativeUnitOfMeasures)
				.setCaption("Packaging").setDimension(3);

		// @@end
	}

	// @@begin head:destroy:Header
	/**
	 * Hook for node element destroy of Header
	 */
	// @@end
	@Navigation(artifact = "Header", type = NavigationType.NODE_ELEMENT_DESTROY)
	public void cpsDestroyHeader(MaterialBPMOAccess bpmoAccess) {
		// @@begin body:destroy:Header

		// @@end
	}

	// @@begin head:init:Plants
	/**
	 * Hook for node element init of Plants
	 */
	// @@end
	@Navigation(artifact = "Header.Plants", type = NavigationType.NODE_ELEMENT_INIT)
	public void cpsInitPlants(MaterialBPMOAccess bpmoAccess) {
		// @@begin body:init:Plants

		IFlowLayoutManager layoutManager = styleController.getLayoutManager();
		layoutManager.createAndAddSeparator()
				.setTitle(String.format("Plant %s", bpmoAccess.getPlants$PlantId().getValue()))
				.setTitleStyleName(SeparatorStyle.H1).addTitleStyleName(SeparatorStyle.HR)
				.setContentWidth(90, UNIT.PERCENTAGE);
		layoutManager.fillContent(MaterialBPMO.Plants.MaximumLotSize, MaterialBPMO.Plants.MinimumLotSize,
				MaterialBPMO.Plants.FixedLotSize);
		layoutManager.addLineBreak();
		layoutManager.createAndAddGroupContent().fillContent().setCaption("Lot Sizes").setDimension(3);

		// @@end
	}

	// @@begin head:destroy:Plants
	/**
	 * Hook for node element destroy of Plants
	 */
	// @@end
	@Navigation(artifact = "Header.Plants", type = NavigationType.NODE_ELEMENT_DESTROY)
	public void cpsDestroyPlants(MaterialBPMOAccess bpmoAccess) {
		// @@begin body:destroy:Plants

		// @@end
	}

	// @@begin head:init:Plants$StorageLocations
	/**
	 * Hook for node element init of Plants$StorageLocations
	 */
	// @@end
	@Navigation(artifact = "Header.Plants.StorageLocations", type = NavigationType.NODE_ELEMENT_INIT)
	public void cpsInitPlants$StorageLocations(MaterialBPMOAccess bpmoAccess) {
		// @@begin body:init:Plants$StorageLocations

		IFlowLayoutManager layoutManager = styleController.getLayoutManager();
		layoutManager
				.createAndAddSeparator()
				.setTitle(
						String.format("Storage Location %s", bpmoAccess.getPlants$StorageLocations$StorageLocationId()
								.getValue())).setTitleStyleName(SeparatorStyle.H1).addTitleStyleName(SeparatorStyle.HR)
				.setContentWidth(90, UNIT.PERCENTAGE);

		// @@end
	}

	// @@begin head:destroy:Plants$StorageLocations
	/**
	 * Hook for node element destroy of Plants$StorageLocations
	 */
	// @@end
	@Navigation(artifact = "Header.Plants.StorageLocations", type = NavigationType.NODE_ELEMENT_DESTROY)
	public void cpsDestroyPlants$StorageLocations(MaterialBPMOAccess bpmoAccess) {
		// @@begin body:destroy:Plants$StorageLocations

		// @@end
	}

	// @@begin head:init:SalesOrganizations
	/**
	 * Hook for node element init of SalesOrganizations
	 */
	// @@end
	@Navigation(artifact = "Header.SalesOrganizations", type = NavigationType.NODE_ELEMENT_INIT)
	public void cpsInitSalesOrganizations(MaterialBPMOAccess bpmoAccess) {
		// @@begin body:init:SalesOrganizations
		IFlowLayoutManager layoutManager = styleController.getLayoutManager();
		layoutManager
				.createAndAddSeparator()
				.setTitle(
						String.format("Sales Organization %s Distribution Channel %s", bpmoAccess
								.getSalesOrganizations$SalesOrganization().getValue(), bpmoAccess
								.getSalesOrganizations$DistributionChannel().getValue()))
				.setTitleStyleName(SeparatorStyle.H1).addTitleStyleName(SeparatorStyle.HR)
				.setContentWidth(90, UNIT.PERCENTAGE);
		// @@end
	}

	// @@begin head:destroy:SalesOrganizations
	/**
	 * Hook for node element destroy of SalesOrganizations
	 */
	// @@end
	@Navigation(artifact = "Header.SalesOrganizations", type = NavigationType.NODE_ELEMENT_DESTROY)
	public void cpsDestroySalesOrganizations(MaterialBPMOAccess bpmoAccess) {
		// @@begin body:destroy:SalesOrganizations

		// @@end
	}

	// @@begin others

	@StyleController
	public IDefaultStyleController styleController;

	// @@end
}
