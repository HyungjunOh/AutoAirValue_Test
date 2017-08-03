import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.json.simple.JSONArray;


public class Main {
	
	public static void main(String[] args) {
		
		//DBConnection
		Connection conn = DBConnection.getConn();
		System.out.println(conn);
//		System.out.println("닫힘상태 : " + conn.isClosed());
//		DBConnection.close();
//		System.out.println("닫힘상태 : " + conn.isClosed());
//
//		conn = DBConnection.getConn();
//		System.out.println("닫힘상태 : " + conn.isClosed());
		
		
//		//4. 측정소 mysql에 저장 한번만 할것!!
//		StationParser stationParser = new StationParser();		
//		List<Map<String, String>> station = stationParser.getStation("서울");
//		System.out.println("Station Update");
//		for(int i=0; i<station.size(); i++) {
//			System.out.println( station.get(i).get("stationName").toString() + " / " 
//					+ station.get(i).get("dmX").toString() + " / " 
//					+ station.get(i).get("dmY").toString() );
//			DBConnection.insert_Station(i+1, station.get(i).get("stationName").toString(), 
//					Double.parseDouble(station.get(i).get("dmX")), Double.parseDouble(station.get(i).get("dmY")));
//		}
		
		
		//3. 시간마다 측정소별 대기 측정값을 자동으로 받아보자
		//API에서 정시에 업데이트 X (업데이트가 될때까지 조회하면 리퀘스트 초과함. 업데이트 시간을 확인해보자)
		//9:12분 체크 -> 9시 데이터 들어옴.
		MyTimer myTimer = new MyTimer();
		Timer timer = new Timer();
		
		Calendar cal = Calendar.getInstance();
		
		int am_pm = cal.get(Calendar.AM_PM);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		
		//timeFlag의 분마다 리퀘스트.
		int timeFlag = 25;
		int waitTime = 0;
		
		if( minute > timeFlag) {
			waitTime = (60+timeFlag-minute)*60*1000;
		} else if(minute < timeFlag) {
			waitTime = (timeFlag-minute)*60*1000;
		}
		
		timer.scheduleAtFixedRate(myTimer, waitTime, 60*60*1000);
		try {
			Thread.sleep(20000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
