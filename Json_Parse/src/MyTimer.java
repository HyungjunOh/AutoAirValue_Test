import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class MyTimer extends TimerTask{

	@Override
	public void run() {
		System.out.println(new Date());
		StationParser stationParser = new StationParser();
		AirValueParser airValueParser = new AirValueParser();
		
		
		//1. 측정소 목록과 좌표
		List<Map<String, String>> station = stationParser.getStation("서울");

		for(int i=0; i<station.size(); i++) {
			System.out.println( station.get(i).get("stationName").toString() + " / " 
					+ station.get(i).get("dmX").toString() + " / " 
					+ station.get(i).get("dmY").toString() );
		}
		
		
		//2. 측정소의 대기 측정값
		// 여기서 getAirValueFromJSON을 직접 써도 괜찮다. 위에 측정소 목록을 요청하는 것과는 달리 파싱받은 값들이 많이 필요한 경우에는 그냥 JSONArray를 써보자.
		// 혹은 Bean을 써서 직접 붙여보는 것도 좋을 것 같다. (TODO)
		List<Map<String, String>> airValue = airValueParser.getValue(station.get(0).get("stationName").toString());
		
		for(int i=0; i<airValue.size(); i++) {
			System.out.println(airValue.get(i).toString());
		}
		//측정소별 최근 데이터.
		for(int i=0; i<station.size(); i++) {
			System.out.println(station.get(i).get("stationName").toString() + " = " + airValueParser.getValue(station.get(i).get("stationName")).get(0).toString() );
		}
		
	}
	
}
