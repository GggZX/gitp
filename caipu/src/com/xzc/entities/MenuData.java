package com.xzc.entities;

import java.util.List;

/**
 * ²éÑ¯µ½µÄ²ËÆ×
 * @author Administrator
 *
 */
public class MenuData {
	String id;
	String title;
	String tags;
	String imtro;
	String ingredients;
	String burden;
	List<String> albums;
	List<MenuSteps> steps;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getImtro() {
		return imtro;
	}

	public void setImtro(String imtro) {
		this.imtro = imtro;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getBurden() {
		return burden;
	}

	public void setBurden(String burden) {
		this.burden = burden;
	}

	public List<String> getAlbums() {
		return albums;
	}

	public void setAlbums(List<String> albums) {
		this.albums = albums;
	}

	public List<MenuSteps> getSteps() {
		return steps;
	}

	public void setSteps(List<MenuSteps> steps) {
		this.steps = steps;
	}

}
