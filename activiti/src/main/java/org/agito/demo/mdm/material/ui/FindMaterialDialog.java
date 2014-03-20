package org.agito.demo.mdm.material.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.agito.demo.mdm.material.MaterialBPMOAccess;
import org.agito.demo.mdm.material.MaterialBPMOAction;
import org.agito.demo.mdm.material.MaterialBPMOController.ActionParameter;
import org.agito.demo.mdm.material.dto.MaterialHeaderDTO;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayout.Colspan;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayout.ColumnWidth;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayout.MaxColums;
import de.agito.cps.ui.vaadin.bpmo.styles.Style;
import de.agito.cps.ui.vaadin.common.resources.UIDataTypeFactory;

/**
 * @author Jörg Burmeister
 * 
 *         As sample dialog to find a material.
 *         Use the business controller action implementation to access a simulated service interface
 * 
 */
public class FindMaterialDialog extends Window {

	private static final long serialVersionUID = 879199253393682997L;

	final BeanItemContainer<MaterialHeaderDTO> container = new BeanItemContainer<MaterialHeaderDTO>(
			MaterialHeaderDTO.class);
	final Table table = new Table("", container);

	public void init(final MaterialBPMOAccess bpmoAccess, final Button.ClickListener clickListener) {
		setCaption("Find Material");
		setModal(true);
		setWidth(530, Unit.PIXELS);
		setResizable(false);
		setCloseShortcut(KeyCode.ESCAPE);

		CssLayout bodyLayout = new CssLayout();
		// use the float layout approach
		bodyLayout.addStyleName(ColumnWidth.PIXEL_250.getStyle());
		bodyLayout.addStyleName(MaxColums.COL2.getStyle());
		bodyLayout.addStyleName(Style.MARGIN.getStyle());
		setContent(bodyLayout);

		final TextField materialNumber = new TextField(bpmoAccess.getMaterialNumber().getContext().getDefinition()
				.getLabel().getText());
		materialNumber.setInputPrompt("# 1-19 available");
		materialNumber.setNullRepresentation("");
		final TextField materialName = new TextField(bpmoAccess.getName().getContext().getDefinition().getLabel()
				.getText());

		bodyLayout.addComponent(UIDataTypeFactory.getInstance().createComponentWrapper(Colspan.DIMENSION_1,
				materialNumber));
		bodyLayout.addComponent(UIDataTypeFactory.getInstance().createComponentWrapper(Colspan.DIMENSION_1,
				materialName));

		Button button = new Button("Search");
		button.setClickShortcut(KeyCode.ENTER);
		button.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -7929426254254303014L;

			@Override
			public void buttonClick(ClickEvent event) {
				container.removeAllItems();
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put(ActionParameter.MATERIAL_HEADER_QUERY_ATTIBUTE_NAME.toString(), materialName.getValue());
				try {
					parameters.put(ActionParameter.MATERIAL_HEADER_QUERY_ATTIBUTE_NUMBER.toString(),
							Integer.valueOf((String) materialNumber.getValue()));
				} catch (Exception e) {
					materialNumber.setValue(null);
					parameters.put(ActionParameter.MATERIAL_HEADER_QUERY_ATTIBUTE_NUMBER.toString(), new Integer(0));
				}
				@SuppressWarnings("unchecked")
				List<MaterialHeaderDTO> list = (List<MaterialHeaderDTO>) bpmoAccess.getContext().getBPMOHeader()
						.getParent().execute(MaterialBPMOAction.FindMaterial, parameters);
				container.addAll(list);
				if (container.size() == 0)
					Notification.show("No entries found");
			}
		});
		bodyLayout.addComponent(UIDataTypeFactory.getInstance()
				.createComponentWrapper(Colspan.DIMENSION_FULL, button));

		table.setWidth(100, Unit.PERCENTAGE);
		table.setPageLength(5);
		table.setImmediate(true);
		table.setSelectable(true);

		bodyLayout
				.addComponent(UIDataTypeFactory.getInstance().createComponentWrapper(Colspan.DIMENSION_FULL, table));

		Button cancelButton = new Button("Cancel");
		cancelButton.addStyleName(Style.FLOAT_RIGHT.getStyle());
		cancelButton.addStyleName(Style.MARGIN.getStyle());
		cancelButton.addClickListener(clickListener);
		cancelButton.setData(ButtonAction.CANCEL);
		cancelButton.setClickShortcut(KeyCode.ESCAPE);

		final Button buttonOK = new Button("OK");
		buttonOK.addStyleName(Style.FLOAT_RIGHT.getStyle());
		buttonOK.addStyleName(Style.MARGIN.getStyle());
		buttonOK.setEnabled(false);
		buttonOK.setData(ButtonAction.OK);
		buttonOK.addClickListener(clickListener);

		bodyLayout.addComponent(UIDataTypeFactory.getInstance().createComponentWrapper(Colspan.DIMENSION_FULL,
				buttonOK, cancelButton));

		table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 666727040165970080L;

			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					table.setValue(event.getItemId());
					buttonOK.click();
				}
			}
		});

		table.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -4171877488661232263L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				buttonOK.setEnabled(table.getValue() != null);
			}
		});

	}

	public MaterialHeaderDTO getSelectedMaterial() {
		return (MaterialHeaderDTO) table.getValue();
	}

	public enum ButtonAction {
		OK,

		CANCEL;
	}

}
