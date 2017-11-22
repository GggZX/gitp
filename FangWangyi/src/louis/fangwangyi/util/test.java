package louis.fangwangyi.util;

import java.util.ArrayList;
import java.util.List;

import louis.fangwangyi.info.CityInfo;

import com.google.gson.Gson;

public class test {
  public List<CityList> getCity(String res) {
	  List<CityList> list=new ArrayList<CityList>();
	  Gson gson=new Gson();
	  CityInfo info=gson.fromJson(res, CityInfo.class);
	  list=info.getShowapi_res_body().getCityList();
	return list;
	// TODO Auto-generated method stub

}
}
