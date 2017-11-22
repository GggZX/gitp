package com.xzc.entities;

public class Collections {
	int id;
	String menuId;
	String menuName;
	String img;
	String step;
	String tags;
	String name;

	public Collections() {

	}

	public Collections(int id, String menuId, String menuName, String img,
			String step, String name, String tags) {
		this.id = id;
		this.menuId = menuId;
		this.menuName = menuName;
		this.img = img;
		this.step = step;
		this.name = name;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
