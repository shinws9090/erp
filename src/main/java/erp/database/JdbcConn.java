package erp.database;

import java.io.IOException;
import java.io.InputStream; // java.io
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Properties;

public class JdbcConn {

	
	public static Connection getConnection() {
		String propertiesPath = "db.properties"; // 키 이름은 무조껀 정해진대로 해야됨  (url,user,password)
		Connection con = null;
		try (InputStream in = ClassLoader.getSystemResourceAsStream(propertiesPath)) {// 파일 내용 리소스 파일을 담는것
			
			Properties prop = new Properties(); //key = value 로 저장됨
			
			prop.load(in); // prop로 값을 가져오는것
			
			con = DriverManager.getConnection(prop.getProperty("url"), prop);
			
		
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;

	}

}
