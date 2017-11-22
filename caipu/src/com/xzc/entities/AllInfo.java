package com.xzc.entities;

import java.util.List;
/**
 * JsonÊý¾Ý
 * @author Administrator
 *
 */
public class AllInfo {
	String resultcode;
	String reson;
	MenuResult result;
	int error_code;

	public String getResultCode() {
		return resultcode;
	}

	public void setResultCode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}

	public MenuResult getResult() {
		return result;
	}

	public void setResult(MenuResult result) {
		this.result = result;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

}
