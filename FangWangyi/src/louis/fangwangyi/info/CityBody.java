package louis.fangwangyi.info;

import java.util.List;

import louis.fangwangyi.util.CityList;

public class CityBody {
	private List<CityList> cityList;
	private int ret_code;
	private int totalNum;
	public List<CityList> getCityList() {
		return cityList;
	}
	public void setCityList(List<CityList> cityList) {
		this.cityList = cityList;
	}
	public int getRet_code() {
		return ret_code;
	}
	public void setRet_code(int ret_code) {
		this.ret_code = ret_code;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public CityBody(List<CityList> cityList, int ret_code, int totalNum) {
		super();
		this.cityList = cityList;
		this.ret_code = ret_code;
		this.totalNum = totalNum;
	}
	public CityBody() {
		super();
	}
	
}
