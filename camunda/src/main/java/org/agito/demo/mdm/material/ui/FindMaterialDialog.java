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
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.agito.cps.ui.vaadin.bpmo.styles.CssName;

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
	final Button buttonOK = new Button("OK") {

		private static final long serialVersionUID = -4682784358651527031L;

		{
			setEnabled(false);
			setData(ButtonAction.OK);
		}

	};

	public void init(final MaterialBPMOAccess bpmoAccess, final Button.ClickListener clickListener) {
		setCaption("Find Material");
		setModal(true);
		setCloseShortcut(KeyCode.ESCAPE);
		setWidth(505, Sizeable.UNITS_PIXELS);
		VerticalLayout bodyLayout = new VerticalLayout();
		setContent(bodyLayout);
		bodyLayout.setSizeFull();
		bodyLayout.setMargin(true);
		final TextField materialNumber = new TextField(bpmoAccess.getMaterialNumber().getContext().getDefinition()
				.getLabel().getText());
		materialNumber.setInputPrompt("# 1-19 available");
		materialNumber.setNullRepresentation("");
		final TextField materialName = new TextField(bpmoAccess.getName().getContext().getDefinition().getLabel()
				.getText());
		materialName.setWidth(300, UNITS_PIXELS);
		HorizontalLayout body = new HorizontalLayout();
		body.setWidth(500, UNITS_PIXELS);
		bodyLayout.addComponent(body);
		body.addComponent(materialNumber);
		body.addComponent(materialName);

		CssLayout buttonLayout = new CssLayout();
		buttonLayout.setWidth(100, UNITS_PERCENTAGE);
		buttonLayout.setMargin(false, true, true, true);
		buttonLayout.setStyleName(CssName.BUTTON_CONTAINER.getCssClass());
		bodyLayout.addComponent(buttonLayout);

		Button button = new Button("Search");
		button.setClickShortcut(KeyCode.ENTER);
		button.addListener(new ClickListener() {
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
					getWindow().showNotification("No entries found");
			}
		});
		buttonLayout.addComponent(button);

		table.setWidth(100, UNITS_PERCENTAGE);
		table.setPageLength(5);
		table.setImmediate(true);
		table.setSelectable(true);
		table.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = -4171877488661232263L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				buttonOK.setEnabled(table.getValue() != null);
			}
		});

		table.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 666727040165970080L;

			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					table.setValue(event.getItemId());
					buttonOK.click();
				}
			}
		});
		bodyLayout.addComponent(table);

		buttonLayout = new CssLayout();
		buttonLayout.setWidth(100, UNITS_PERCENTAGE);
		buttonLayout.setMargin(false, true, true, true);
		buttonLayout.setStyleName(CssName.BUTTON_CONTAINER.getCssClass());
		bodyLayout.addComponent(buttonLayout);

		buttonLayout.addComponent(new Button("Cancel") {
			private static final long serialVersionUID = -303529397058170688L;
			{
				addListener(clickListener);
				setData(ButtonAction.CANCEL);
				setClickShortcut(KeyCode.ESCAPE);
			}
		});

		buttonOK.addListener(clickListener);
		buttonLayout.addComponent(buttonOK);
	}

	public MaterialHeaderDTO getSelectedMaterial() {
		return (MaterialHeaderDTO) table.getValue();
	}

	public enum ButtonAction {
		OK,

		CANCEL;
	}

}
