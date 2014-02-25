package org.agito.demo.mdm.material.dto;

import java.io.Serializable;

public class MaterialHeaderDTO implements Serializable {
	private static final long serialVersionUID = 4694107177418513855L;
	public String number;
	public String name;

	public MaterialHeaderDTO(String number, String name) {
		this.number = number;
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}
}