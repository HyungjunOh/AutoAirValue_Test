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

public class AirValueParser {

	String ServiceKey = "ssW23XVqj7zYinUM1xK3frFatigBeIMaYxmU1T8Av2R1%2FJI7TT%2F556f4nbWLVA4%2FslXiTzGaWK40yfkC%2FMLFXg%3D%3D";
	String AirValueApi = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty";

	public AirValueParser() {}
	
	public JSONArray getAirValueFromJSON(String station) {
		
		JSONArray list = null;
		
		try {
			
			String requestUrl = AirValueApi + "?stationName=" + URLEncoder.encode(station, "UTF-8") 
			+ "&dataTerm=month&pageNo=1&numOfRows=10&ver=1.3&_returnType=json&ServiceKey=" + ServiceKey;
			
			URL url = new URL(requestUrl);
			
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			
			JSONObject jsonObject = (JSONObject)JSONValue.parseWithException(isr);
			
			list = (JSONArray)jsonObject.get("list");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Map<String, String>> getValue(String location) {
		
		List<Map<String, String>> result = new ArrayList<>();
		
		JSONArray list = getAirValueFromJSON(location);
		
		for(int i=0; i<list.size(); i++){
			
			Map<String, String> data = new HashMap<String, String>();
			JSONObject listObj = (JSONObject)list.get(i);
			
			String dataTime = (String)listObj.get("dataTime").toString();
			String khaiValue = (String)listObj.get("khaiValue").toString();
			String khaiGrade = (String)listObj.get("khaiGrade").toString();
			
			data.put("dataTime", dataTime);
			data.put("khaiValue", khaiValue);
			data.put("khaiGrade", khaiGrade);
			
			if(data != null){
				result.add(data);
			}
		}
		
		return result;
	}
}
