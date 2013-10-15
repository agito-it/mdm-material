package org.agito.demo.mdm.material;

// @@begin imports

import org.agito.demo.mdm.material.MaterialBPMOAccess.AlternativeUnitOfMeasures.Row;
import org.agito.demo.mdm.material.ui.FindMaterialDialog;
import org.agito.demo.mdm.material.ui.FindMaterialDialog.ButtonAction;
import org.agito.demo.mdm.material.ui.FindMaterialDialog.Material;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

import de.agito.cps.core.bpmo.BPMOState;
import de.agito.cps.core.bpmo.ClientMode;
import de.agito.cps.core.logger.Logger;
import de.agito.cps.ui.vaadin.bpmo.BPMOUIController;
import de.agito.cps.ui.vaadin.bpmo.IBPMOUIControllerContext;
import de.agito.cps.ui.vaadin.bpmo.annotation.Navigation;
import de.agito.cps.ui.vaadin.bpmo.annotation.StyleController;
import de.agito.cps.ui.vaadin.bpmo.enums.NavigationType;
import de.agito.cps.ui.vaadin.bpmo.enums.SeparatorStyle;
import de.agito.cps.ui.vaadin.bpmo.enums.UNIT;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayoutManager;
import de.agito.cps.ui.vaadin.bpmo.navigation.IDefaultActionMenuBar;
import de.agito.cps.ui.vaadin.bpmo.styles.IDefaultStyleController;

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

	public MaterialBPMOUIController(final IBPMOUIControllerContext context) {
		super(context);
	}

	// @@begin head:init:Header
	/**
	 * Hook for node element init of Header
	 */
	// @@end
	@Navigation(artifact = "Header", type = NavigationType.NODE_ELEMENT_INIT)
	public void cpsInitHeader(final MaterialBPMOAccess bpmoAccess) {
		// @@begin body:init:Header
		IFlowLayoutManager layoutManager = styleController.getLayoutManager();
		layoutManager
				.createAndAddSeparator()
				.setTitle(
						bpmoAccess.getMaterialNumber().getValue() == null ? "Material" : "Material ".concat(bpmoAccess
								.getMaterialNumber().getValue())).setTitleStyleName(SeparatorStyle.H1)
				.addTitleStyleName(SeparatorStyle.HR).setContentWidth(90, UNIT.PERCENTAGE);
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
		layoutManager.addLineBreak();
		layoutManager.createAndAddTableContent(MaterialBPMO.AlternativeUnitOfMeasures).setDimension(4);

		if (getBPMO().getBPMOHeader().getBPMOState() == BPMOState.DRAFT
				&& (MaterialBPMOLifecycle.valueOf(getBPMO().getBPMOHeader().getLifecycle()) == MaterialBPMOLifecycle.UPDATE)) {
			if (bpmoAccess.getMaterialNumber().getOriginalValue() == null) {
				final IDefaultActionMenuBar menuBar = styleController.getActionMenu();
				menuBar.add(MenutItem.FIND_MATERIAL, MenutItem.FIND_MATERIAL.getLabel(), new Command() {
					private static final long serialVersionUID = 200736023610368808L;

					public void menuSelected(MenuItem selectedItem) {
						openFindMaterialDialog(bpmoAccess);
					}
				}, null, true, ClientMode.EDIT);
				openFindMaterialDialog(bpmoAccess);
			}
		}

		// @@end
	}

	// @@begin head:destroy:Header
	/**
	 * Hook for node element destroy of Header
	 */
	// @@end
	@Navigation(artifact = "Header", type = NavigationType.NODE_ELEMENT_DESTROY)
	public void cpsDestroyHeader(final MaterialBPMOAccess bpmoAccess) {
		// @@begin body:destroy:Header
		IDefaultActionMenuBar menuBar = styleController.getActionMenu();
		MenuItem menuItem = menuBar.getMenuItemById(MenutItem.FIND_MATERIAL);
		if (menuItem != null)
			menuBar.removeMenuItem(menuItem);
		// @@end
	}

	// @@begin head:init:Plants
	/**
	 * Hook for node element init of Plants
	 */
	// @@end
	@Navigation(artifact = "Header.Plants", type = NavigationType.NODE_ELEMENT_INIT)
	public void cpsInitPlants(final MaterialBPMOAccess bpmoAccess) {
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
	public void cpsDestroyPlants(final MaterialBPMOAccess bpmoAccess) {
		// @@begin body:destroy:Plants

		// @@end
	}

	// @@begin head:init:Plants$StorageLocations
	/**
	 * Hook for node element init of Plants$StorageLocations
	 */
	// @@end
	@Navigation(artifact = "Header.Plants.StorageLocations", type = NavigationType.NODE_ELEMENT_INIT)
	public void cpsInitPlants$StorageLocations(final MaterialBPMOAccess bpmoAccess) {
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
	public void cpsDestroyPlants$StorageLocations(final MaterialBPMOAccess bpmoAccess) {
		// @@begin body:destroy:Plants$StorageLocations

		// @@end
	}

	// @@begin head:init:SalesOrganizations
	/**
	 * Hook for node element init of SalesOrganizations
	 */
	// @@end
	@Navigation(artifact = "Header.SalesOrganizations", type = NavigationType.NODE_ELEMENT_INIT)
	public void cpsInitSalesOrganizations(final MaterialBPMOAccess bpmoAccess) {
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
	public void cpsDestroySalesOrganizations(final MaterialBPMOAccess bpmoAccess) {
		// @@begin body:destroy:SalesOrganizations

		// @@end
	}

	// @@begin others

	@StyleController
	public IDefaultStyleController styleController;

	public enum MenutItem {
		FIND_MATERIAL("Find Material");

		private String label;

		private MenutItem(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	private void openFindMaterialDialog(final MaterialBPMOAccess bpmoAccess) {
		final FindMaterialDialog findMaterialDialog = new FindMaterialDialog();
		styleController.getActionMenu().getWindow().addWindow(findMaterialDialog);
		findMaterialDialog.init(bpmoAccess, new ClickListener() {
			private static final long serialVersionUID = -5884817909375913870L;

			@Override
			public void buttonClick(ClickEvent event) {
				ButtonAction action = (ButtonAction) event.getButton().getData();
				switch (action) {
				case CANCEL:
					break;
				case OK:
					Material material = findMaterialDialog.getSelectedMaterial();
					bpmoAccess.getName().setOriginalValue(material.getName());
					bpmoAccess.getMaterialNumber().setOriginalValue(material.getNumber());
					bpmoAccess.getMaterialType().setOriginalValue("1");
					bpmoAccess.getGrossWeight().setOriginalValue("10");
					bpmoAccess.getNetWeight().setOriginalValue("12");
					bpmoAccess.getAllowedPackagingWeight().setOriginalValue("15");
					bpmoAccess.getAllowedPackagingVolume().setOriginalValue("60");
					bpmoAccess.getVolume().setOriginalValue("14");
					bpmoAccess.getBaseUnitOfMeasure().setOriginalValue("part");

					Row row = bpmoAccess.getAlternativeUnitOfMeasures().createOriginalRow();
					row.getAlternativeUnitOfMeasure().setValue(bpmoAccess.getBaseUnitOfMeasure().getOriginalValue());
					row.getDenominatorConversion().setValue("1");
					row.getNumeratorConversion().setValue("5");
					bpmoAccess.getAlternativeUnitOfMeasures().addOriginalRow(row);
					styleController.getActionMenu().getMenuItemById(MenutItem.FIND_MATERIAL).setVisible(false);
					break;
				}
				styleController.getActionMenu().getWindow().removeWindow(findMaterialDialog);

			}
		});
	}

	// @@end
}
