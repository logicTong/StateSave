package com.tianhe.statesave.sample;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Obj implements Serializable {
	
	private int id;
	private String name;
	
	public Obj() {}
	
	public Obj(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
