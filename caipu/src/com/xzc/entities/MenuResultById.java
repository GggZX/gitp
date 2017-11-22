package com.xzc.entities;

public class MenuResultById {
	String resultcode;
	String reson;
	MenuDataById result;
	int error_code;

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}

	public MenuDataById getResult() {
		return result;
	}

	public void setResult(MenuDataById result) {
		this.result = result;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

}
