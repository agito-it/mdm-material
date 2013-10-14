package org.agito.demo.mdm.material.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.agito.demo.mdm.material.MaterialBPMOAccess;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
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

import de.agito.cps.core.utils.StringUtils;
import de.agito.cps.ui.vaadin.bpmo.styles.StyleName;

public class FindMaterialDialog extends Window {

	private static final long serialVersionUID = 879199253393682997L;

	final BeanItemContainer<FindMaterialDialog.Material> container = new BeanItemContainer<FindMaterialDialog.Material>(
			FindMaterialDialog.Material.class);
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
		buttonLayout.setStyleName(StyleName.BUTTON_CONTAINER.getCssClass());
		bodyLayout.addComponent(buttonLayout);

		Button button = new Button("Search");
		button.addListener(new ClickListener() {
			private static final long serialVersionUID = -7929426254254303014L;

			@Override
			public void buttonClick(ClickEvent event) {

			}
		});
		buttonLayout.addComponent(button);

		final MaterialListAccessor materialListDTO = new MaterialListAccessor();

		container.addAll(materialListDTO.getMaterialByName("Mat"));
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
		bodyLayout.addComponent(table);

		buttonLayout = new CssLayout();
		buttonLayout.setWidth(100, UNITS_PERCENTAGE);
		buttonLayout.setMargin(false, true, true, true);
		buttonLayout.setStyleName(StyleName.BUTTON_CONTAINER.getCssClass());
		bodyLayout.addComponent(buttonLayout);

		buttonLayout.addComponent(new Button("Cancel") {
			private static final long serialVersionUID = -303529397058170688L;
			{
				addListener(clickListener);
				setData(ButtonAction.CANCEL);
			}
		});

		buttonOK.addListener(clickListener);
		buttonLayout.addComponent(buttonOK);
	}

	public Material getSelectedMaterial() {
		return (Material) table.getValue();
	}

	public class MaterialListAccessor implements Serializable {

		private static final long serialVersionUID = 8084910359894112137L;

		private Map<String, Material> map = new TreeMap<String, Material>();

		private MaterialListAccessor() {
			for (int i = 1; i < 20; i++)
				map.put(StringUtils.leftPad(String.valueOf(i), 10),
						new Material(StringUtils.leftPad(String.valueOf(i), 10), String.format("Material %s", i)));
		}

		public List<Material> getMaterialByNumber(int number) {
			List<Material> list = new ArrayList<FindMaterialDialog.Material>();
			if (map.containsKey(StringUtils.leftPad(String.valueOf(number), 10)))
				list.add(map.get(StringUtils.leftPad(String.valueOf(number), 10)));
			return list;
		}

		public List<Material> getMaterialByName(String name) {
			List<Material> list = new ArrayList<FindMaterialDialog.Material>();
			if (StringUtils.trim(name) != null) {
				name = name.replace("*", "");
				for (Material material : map.values())
					if (material.getName().toLowerCase().startsWith(name.toLowerCase()))
						list.add(material);
			}
			return list;
		}
	}

	public class Material implements Serializable {
		private static final long serialVersionUID = 8335919741279724481L;
		String number;
		String name;

		private Material(String number, String name) {
			this.number = number;
			this.name = name;
		}

		public String getNumber() {
			return number;
		}

		public String getName() {
			return name;
		}
	}

	public enum ButtonAction {
		OK,

		CANCEL;
	}

}
