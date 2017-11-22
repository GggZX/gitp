package com.xzc.entities;

import java.util.List;

public class CateList {
	String parentId;
	String name;
	List<Category> list;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getList() {
		return list;
	}

	public void setList(List<Category> list) {
		this.list = list;
	}

}
