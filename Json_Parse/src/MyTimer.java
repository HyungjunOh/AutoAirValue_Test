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
		
		
		//1. ������ ��ϰ� ��ǥ
		List<Map<String, String>> station = stationParser.getStation("����");

		for(int i=0; i<station.size(); i++) {
			System.out.println( station.get(i).get("stationName").toString() + " / " 
					+ station.get(i).get("dmX").toString() + " / " 
					+ station.get(i).get("dmY").toString() );
		}
		
		
		//2. �������� ��� ������
		// ���⼭ getAirValueFromJSON�� ���� �ᵵ ������. ���� ������ ����� ��û�ϴ� �Ͱ��� �޸� �Ľ̹��� ������ ���� �ʿ��� ��쿡�� �׳� JSONArray�� �Ẹ��.
		// Ȥ�� Bean�� �Ἥ ���� �ٿ����� �͵� ���� �� ����. (TODO)
		List<Map<String, String>> airValue = airValueParser.getValue(station.get(0).get("stationName").toString());
		
		for(int i=0; i<airValue.size(); i++) {
			System.out.println(airValue.get(i).toString());
		}
		//�����Һ� �ֱ� ������.
		for(int i=0; i<station.size(); i++) {
			System.out.println(station.get(i).get("stationName").toString() + " = " + airValueParser.getValue(station.get(i).get("stationName")).get(0).toString() );
		}
		
	}
	
}
