package org.agito.demo.mdm.material;

// @@begin imports

import java.util.HashMap;
import java.util.Map;

import org.agito.demo.mdm.material.MaterialBPMOController.ActionParameter;
import org.agito.demo.mdm.material.dto.MaterialHeaderDTO;
import org.agito.demo.mdm.material.ui.FindMaterialDialog;
import org.agito.demo.mdm.material.ui.FindMaterialDialog.ButtonAction;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

import de.agito.cps.core.bpmo.BPMOState;
import de.agito.cps.core.bpmo.ClientMode;
import de.agito.cps.ui.vaadin.bpmo.BPMOUIController;
import de.agito.cps.ui.vaadin.bpmo.BPMOUi;
import de.agito.cps.ui.vaadin.bpmo.IBPMOUIControllerContext;
import de.agito.cps.ui.vaadin.bpmo.annotation.Navigation;
import de.agito.cps.ui.vaadin.bpmo.annotation.StyleController;
import de.agito.cps.ui.vaadin.bpmo.enums.NavigationType;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayout.Colspan;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayout.MaxColums;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayoutManager;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowTabSheet;
import de.agito.cps.ui.vaadin.bpmo.navigation.IDefaultActionMenuBar;
import de.agito.cps.ui.vaadin.bpmo.styles.IFlowStyleController;

// @@end

// @@begin head:uicontroller
/**
 * Vaadin UI Controller for MaterialBPMO.
 * 
 * @author andreas.weise
 */
// @@end
public class MaterialBPMOUIController
		extends
		BPMOUIController<MaterialBPMOAccess, MaterialBPMOAction, MaterialBPMOLifecycle, MaterialBPMOLanguage, MaterialBPMOProcessActivity, MaterialBPMO> {

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
		layoutManager.setMaxCols(MaxColums.COL3);

		layoutManager
				.createAndAddBlockHeader()
				.setTitle(
						bpmoAccess.getMaterialNumber().getValue() == null ? "Material" : "Material ".concat(bpmoAccess
								.getMaterialNumber().getValue())).setColspan(Colspan.DIMENSION_FULL);

		layoutManager.createAndAddElements(MaterialBPMO.MaterialNumber, MaterialBPMO.Name, MaterialBPMO.MaterialType);

		layoutManager.newLine();

		IFlowTabSheet tabSheet = layoutManager.createAndAddTabSheet();
		tabSheet.setColspan(Colspan.DIMENSION_3);
		tabSheet.createAndAddTabContent("Dimension").createAndAddElements(MaterialBPMO.GrossWeight,
				MaterialBPMO.NetWeight, MaterialBPMO.AllowedPackagingWeight, MaterialBPMO.Volume,
				MaterialBPMO.AllowedPackagingVolume, MaterialBPMO.BaseUnitOfMeasure);
		tabSheet.createAndAddTabContent("Packaging").addRemainingElements(MaterialBPMO.AlternativeUnitOfMeasures);

		layoutManager.createAndAddTableContent(MaterialBPMO.AlternativeUnitOfMeasures).setColspan(
				Colspan.DIMENSION_FULL);

		if (bpmoAccess.getBPMOHeader().getBPMOState() == BPMOState.DRAFT
				&& (MaterialBPMOLifecycle.valueOf(bpmoAccess.getBPMOHeader().getLifecycle()) == MaterialBPMOLifecycle.UPDATE)) {
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
		layoutManager.setMaxCols(MaxColums.COL3);

		layoutManager.createAndAddBlockHeader().setTitle(bpmoAccess.getContext().getHumanizedPath())
				.setColspan(Colspan.DIMENSION_FULL);

		layoutManager.addRemainingElements(MaterialBPMO.Plants.MaximumLotSize, MaterialBPMO.Plants.MinimumLotSize,
				MaterialBPMO.Plants.FixedLotSize);

		layoutManager.newLine();

		layoutManager.createAndAddGroupContent().addRemainingElements().setCaption("Lot Sizes")
				.setColspan(Colspan.DIMENSION_1);

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
		layoutManager.setMaxCols(MaxColums.COL3);

		layoutManager.createAndAddBlockHeader().setTitle(bpmoAccess.getContext().getHumanizedPath())
				.setColspan(Colspan.DIMENSION_FULL);
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
		layoutManager.setMaxCols(MaxColums.COL3);
		layoutManager.createAndAddBlockHeader().setTitle(bpmoAccess.getContext().getHumanizedPath())
				.setColspan(Colspan.DIMENSION_FULL);
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
	public IFlowStyleController styleController;

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

	/**
	 * Open the dialog to find a specific material
	 * 
	 * @param bpmoAccess
	 */
	private void openFindMaterialDialog(final MaterialBPMOAccess bpmoAccess) {
		final FindMaterialDialog findMaterialDialog = new FindMaterialDialog();
		BPMOUi.getCurrent().addWindow(findMaterialDialog);
		findMaterialDialog.focus();
		findMaterialDialog.init(bpmoAccess, new ClickListener() {
			private static final long serialVersionUID = -5884817909375913870L;

			@Override
			public void buttonClick(ClickEvent event) {
				ButtonAction action = (ButtonAction) event.getButton().getData();
				switch (action) {
				case CANCEL:
					break;
				case OK:
					MaterialHeaderDTO material = findMaterialDialog.getSelectedMaterial();
					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put(ActionParameter.MATERIAL_HEADER_DTO.toString(), material);
					bpmoAccess.getBPMO().execute(MaterialBPMOAction.ReadMaterial, parameters);
					styleController.getActionMenu().getMenuItemById(MenutItem.FIND_MATERIAL).setVisible(false);
					break;
				}
				BPMOUi.getCurrent().removeWindow(findMaterialDialog);
			}
		});
	}

	// @@end
}
