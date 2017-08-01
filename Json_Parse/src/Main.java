import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;


public class Main {
	
	public static void main(String[] args) {
		
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
		
		//3. �ð����� �����Һ� ��� �������� �ڵ����� �޾ƺ���
		//API���� ���ÿ� ������Ʈ X (������Ʈ�� �ɶ����� ��ȸ�ϸ� ������Ʈ �ʰ���. ������Ʈ �ð��� Ȯ���غ���)
		//9:12�� üũ -> 9�� ������ ����.
		
	}
}
