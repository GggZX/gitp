package louis.fangwangyi.util;

public class CityList {
	private String areaId;
	private String 	areaName;
	
	public CityList() {
		
	}
	public CityList(String areaId, String areaName) {
	
		this.areaId = areaId;
		this.areaName = areaName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}
