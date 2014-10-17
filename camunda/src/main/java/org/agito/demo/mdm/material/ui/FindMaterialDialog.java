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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

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

		VerticalLayout bodyLayout = new VerticalLayout();
		bodyLayout.setSpacing(true);
		bodyLayout.setMargin(true);
		setContent(bodyLayout);

		HorizontalLayout fieldLayout = new HorizontalLayout();
		fieldLayout.setSpacing(true);
		fieldLayout.setWidth(100, Unit.PERCENTAGE);
		bodyLayout.addComponent(fieldLayout);
		final TextField materialNumber = new TextField(bpmoAccess.getMaterialNumber().getContext().getDefinition()
				.getLabel().getText());
		materialNumber.setInputPrompt("# 1-19 available");
		materialNumber.setNullRepresentation("");
		materialNumber.setWidth(100, Unit.PERCENTAGE);
		final TextField materialName = new TextField(bpmoAccess.getName().getContext().getDefinition().getLabel()
				.getText());
		materialName.setWidth(100, Unit.PERCENTAGE);
		fieldLayout.addComponents(materialNumber, materialName);

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
							Integer.valueOf(materialNumber.getValue()));
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
		bodyLayout.addComponent(button);
		bodyLayout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);

		table.setWidth(100, Unit.PERCENTAGE);
		table.setPageLength(5);
		table.setImmediate(true);
		table.setSelectable(true);

		bodyLayout.addComponent(table);
		Button cancelButton = new Button("Cancel");
		cancelButton.addClickListener(clickListener);
		cancelButton.setData(ButtonAction.CANCEL);
		cancelButton.setClickShortcut(KeyCode.ESCAPE);

		final Button buttonOK = new Button("OK");
		buttonOK.setEnabled(false);
		buttonOK.setData(ButtonAction.OK);
		buttonOK.addClickListener(clickListener);

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		bodyLayout.setWidth(100, Unit.PERCENTAGE);
		buttonLayout.addComponents(cancelButton, buttonOK);
		bodyLayout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
		bodyLayout.addComponent(buttonLayout);

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
