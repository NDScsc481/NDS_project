import java.sql.*;

public class connect {
	static Connection con;
	static Statement stmt;
	
	public connect(){
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ndsdb", "root", "12345");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
