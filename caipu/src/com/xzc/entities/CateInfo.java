package com.xzc.entities;

import java.util.List;

public class CateInfo {
	String resultcode;
	String reson;
	List<CateList> result;
	String error_code;

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

	public List<CateList> getResult() {
		return result;
	}

	public void setResult(List<CateList> result) {
		this.result = result;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

}
