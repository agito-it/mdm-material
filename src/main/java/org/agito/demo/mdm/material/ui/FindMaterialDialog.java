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
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.agito.cps.core.utils.StringUtils;
import de.agito.cps.ui.vaadin.bpmo.styles.StyleName;

public class FindMaterialDialog extends VerticalLayout {

	private static final long serialVersionUID = 879199253393682997L;

	public FindMaterialDialog(final MaterialBPMOAccess bpmoAccess) {
		setSizeFull();
		setMargin(true);
		final TextField materialNumber = new TextField(bpmoAccess.getMaterialNumber().getContext().getDefinition()
				.getLabel().getText());
		final TextField materialName = new TextField(bpmoAccess.getName().getContext().getDefinition().getLabel()
				.getText());
		materialName.setWidth(300, UNITS_PIXELS);
		HorizontalLayout body = new HorizontalLayout();
		body.setWidth(500, UNITS_PIXELS);
		addComponent(body);
		body.addComponent(materialNumber);
		body.addComponent(materialName);

		Button button = new Button("Search");
		button.setStyleName(StyleName.FLOAT_RIGHT.getCssClass());
		addComponent(button);

		final MaterialListDTO materialListDTO = new MaterialListDTO();

		BeanItemContainer<Material> container = new BeanItemContainer<FindMaterialDialog.Material>(
				FindMaterialDialog.Material.class);
		container.addAll(materialListDTO.getMaterialByName("Mat"));
		final Table table = new Table("", container);
		table.setWidth(100, UNITS_PERCENTAGE);
		table.setPageLength(5);
		table.setImmediate(true);
		table.setSelectable(true);
		table.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = -4171877488661232263L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (table.getValue() != null) {
					Material material = (Material) table.getValue();
					bpmoAccess.getName().setOriginalValue(material.getName());
					bpmoAccess.getMaterialNumber().setOriginalValue(material.getNumber());
					bpmoAccess.getMaterialType().setOriginalValue("1");
					bpmoAccess.getGrossWeight().setOriginalValue("10");
					bpmoAccess.getNetWeight().setOriginalValue("12");
					bpmoAccess.getAllowedPackagingWeight().setOriginalValue("15");
					bpmoAccess.getAllowedPackagingVolume().setOriginalValue("60");
					bpmoAccess.getVolume().setOriginalValue("14");
					bpmoAccess.getBaseUnitOfMeasure().setOriginalValue("part");
					//bpmoAccess.getAlternativeUnitOfMeasures().createOriginalRow().
					//getWindow().getWindow().removeWindow(getWindow());
				}
			}
		});
		addComponent(table);
	}

	public class MaterialListDTO implements Serializable {

		private static final long serialVersionUID = 8084910359894112137L;

		private Map<String, Material> map = new TreeMap<String, Material>();

		private MaterialListDTO() {
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

}
