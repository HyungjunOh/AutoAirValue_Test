import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;



public class DBConnection {
	
	private static Connection connection;
	private static PreparedStatement pstmt;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/airdata?useSSL=false&characterEncoding=utf8";
	
	static final String USERNAME = "root";
	static final String PASSWORD = "1111";

	public static Connection getConn(){
		
		if(connection == null){
			try{
				Class.forName(JDBC_DRIVER);
				connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				System.out.println("MySQL Connection");
				
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("select * from stationlist;");
				
				while (rs.next()){
					System.out.println(rs.getString("station_code") + "\t" + rs.getString(2) + "\t" + rs.getString(3)
					+ "\t" + rs.getString(4));
				}
				
			} catch (SQLException e){
				System.out.println("예외 : connection fail :" + e.getMessage());
				e.printStackTrace();
			} catch (ClassNotFoundException e){
				System.out.println("예외 : 드라이버로드 실패" + e.getMessage());
				e.printStackTrace();
			} 
		}
		return connection;
	}
	
	public static Connection insert_Station(int station_code, String station_name, 
			double station_x, double station_y){
		
		try{
				Class.forName(JDBC_DRIVER);
				connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

				String sql = "INSERT INTO stationlist VALUES (?, ?, ?, ?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, station_code);
				pstmt.setString(2, station_name);
				pstmt.setDouble(3, station_x);
				pstmt.setDouble(4, station_y);
				pstmt.executeUpdate();
				
				System.out.println("station list insert success");
				
			} catch (SQLException e){
				System.out.println("예외 : connection fail :" + e.getMessage());
				e.printStackTrace();
			} catch (ClassNotFoundException e){
				System.out.println("예외 : 드라이버로드 실패" + e.getMessage());
				e.printStackTrace();
			} 
		return connection;
	}
	
	// 최근의 대기 데이터 수집.
	public static Connection insert_AirValue(String stationName, String dateTime, 
			String rkhaiGrade, String rkhaiValue){
		
		try{
				Class.forName(JDBC_DRIVER);
				connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

				String sql = "INSERT INTO airvalue (station_name, dateTime, khaiGrade, khaiValue) VALUES (?, ?, ?, ?)";
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, stationName);
				pstmt.setString(2, dateTime);
				
				Integer khaiGrade;
				Integer khaiValue;
				
				System.out.println(rkhaiGrade+" // "+rkhaiValue);
				
				if(rkhaiGrade==null||rkhaiGrade.equals("")||rkhaiGrade.equals("-")) {
					rkhaiGrade = null;
					khaiGrade = null;
					pstmt.setNull(3, Types.INTEGER);
				} else {
					khaiGrade = Integer.parseInt(rkhaiGrade);
					pstmt.setInt(3, khaiGrade);
				}
					
				if(rkhaiValue==null||rkhaiValue.equals("")||rkhaiValue.equals("-")) {
					rkhaiValue = null;
					khaiValue = null;
					pstmt.setNull(4, Types.INTEGER);
				} else {
					khaiValue = Integer.parseInt(rkhaiValue);
					pstmt.setInt(4, khaiValue);
				}
				
				System.out.println(khaiGrade+" // "+khaiValue);
					
				pstmt.executeUpdate();
				
				System.out.println("air value insert success");
				
			} catch (SQLException e){
				System.out.println("예외 : connection fail :" + e.getMessage());
				e.printStackTrace();
			} catch (ClassNotFoundException e){
				System.out.println("예외 : 드라이버로드 실패" + e.getMessage());
				e.printStackTrace();
			} 
		return connection;
	}
	
	public static void close(){
		
		if(connection == null){
			try{
				if(!connection.isClosed()){
					connection.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		connection = null;
	}

}