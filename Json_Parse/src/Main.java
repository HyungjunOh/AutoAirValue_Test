import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.json.simple.JSONArray;


public class Main {
	
	public static void main(String[] args) {
		
		//3. �ð����� �����Һ� ��� �������� �ڵ����� �޾ƺ���
		//API���� ���ÿ� ������Ʈ X (������Ʈ�� �ɶ����� ��ȸ�ϸ� ������Ʈ �ʰ���. ������Ʈ �ð��� Ȯ���غ���)
		//9:12�� üũ -> 9�� ������ ����.
		MyTimer myTimer = new MyTimer();
		Timer timer = new Timer();
		
		Calendar cal = Calendar.getInstance();
		
		int am_pm = cal.get(Calendar.AM_PM);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		
		//timeFlag�� �и��� ������Ʈ.
		int timeFlag = 12;
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
