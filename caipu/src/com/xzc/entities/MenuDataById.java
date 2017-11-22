package com.xzc.entities;

import java.util.List;

public class MenuDataById {
	List<MenuData> data;
	String totalNum;
	int pn;
	int rn;

	public List<MenuData> getData() {
		return data;
	}

	public void setData(List<MenuData> data) {
		this.data = data;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public int getPn() {
		return pn;
	}

	public void setPn(int pn) {
		this.pn = pn;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

}
