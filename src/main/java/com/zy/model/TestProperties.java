package com.zy.model;

import org.springframework.stereotype.Component;

@Component
public class TestProperties {

	private String name = "paul";
	
	private String type = "person";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
