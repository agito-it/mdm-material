package org.agito.demo.mdm.material.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.agito.cps.core.utils.StringUtils;

public class MaterialHeaderList implements Serializable {
	private static final long serialVersionUID = 8084910359894112137L;

	private static Map<String, MaterialHeaderDTO> map = new TreeMap<String, MaterialHeaderDTO>();

	{
		for (int i = 1; i < 20; i++)
			map.put(StringUtils.leftPad(String.valueOf(i), 10, "0"),
					new MaterialHeaderDTO(StringUtils.leftPad(String.valueOf(i), 10, "0"), String.format("Material %s",
							i)));

	}

	private List<MaterialHeaderDTO> getMaterialByNumber(int number) {
		List<MaterialHeaderDTO> list = new ArrayList<MaterialHeaderDTO>();
		String key = StringUtils.leftPad(String.valueOf(number), 10, "0");
		if (map.containsKey(key))
			list.add(map.get(key));
		return list;
	}

	private List<MaterialHeaderDTO> getMaterialByName(String name) {
		List<MaterialHeaderDTO> list = new ArrayList<MaterialHeaderDTO>();
		if (StringUtils.trim(name) != null) {
			name = name.replace("*", "");
			for (MaterialHeaderDTO material : map.values())
				if (material.getName().toLowerCase().startsWith(name.toLowerCase())
						|| material.getName().toLowerCase().endsWith(name.toLowerCase()))
					list.add(material);
		}
		return list;
	}

	public List<MaterialHeaderDTO> findMaterialByElements(String name, int number) {
		if (StringUtils.trim(name) != null && number == 0)
			return getMaterialByName(name);
		if (StringUtils.trim(name) == null && number != 0)
			return getMaterialByNumber(number);

		List<MaterialHeaderDTO> list = getMaterialByName(name);
		Iterator<MaterialHeaderDTO> iterator = list.iterator();
		while (iterator.hasNext()) {
			MaterialHeaderDTO materialHeaderDTO = (MaterialHeaderDTO) iterator.next();
			if (!materialHeaderDTO.getNumber().equals(StringUtils.leftPad(String.valueOf(number), 10, "0")))
				iterator.remove();
		}
		return list;
	}
}
