import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class StationParser {
	
	String ServiceKey = "ssW23XVqj7zYinUM1xK3frFatigBeIMaYxmU1T8Av2R1%2FJI7TT%2F556f4nbWLVA4%2FslXiTzGaWK40yfkC%2FMLFXg%3D%3D";
	String staionApi = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList";

	public StationParser() {}
	
	public JSONArray getStationFromJSON(String location) {
		
		JSONArray list = null;
		
		try {
			
			String requestUrl = staionApi + "?addr=" + URLEncoder.encode(location, "UTF-8") + "&numOfRows=39&_returnType=json&ServiceKey=" + ServiceKey;
			
			URL url = new URL(requestUrl);
			
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			
			JSONObject jsonObject = (JSONObject)JSONValue.parseWithException(isr);
			
			list = (JSONArray)jsonObject.get("list");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Map<String, String>> getStation(String location) {
		
		List<Map<String, String>> result = new ArrayList<>();
		
		JSONArray list = getStationFromJSON(location);
		
		for(int i=0; i<list.size(); i++){
			
			Map<String, String> data = new HashMap<String, String>();
			JSONObject listObj = (JSONObject)list.get(i);
			String stationName = (String)listObj.get("stationName").toString();
			String dmX = (String)listObj.get("dmX").toString();
			String dmY = (String)listObj.get("dmY").toString();
			
			data.put("stationName", stationName);
			data.put("dmX", dmX);
			data.put("dmY", dmY);
			
			if(data != null){
				result.add(data);
			}
		}
		
		return result;
	}
	
}
