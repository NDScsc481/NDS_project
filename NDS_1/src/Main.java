import java.sql.*;
public class Main {

	static Connection con;
	static Statement stmt;
	
	public static void main(String[] args) {
		
		try{//kevin made a comment vffuyk
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ndsdb", "root", "12345");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from CUSTOMER");
			System.out.println("Database before insert");
			while(rs.next()){
				System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
			}
			
			addCustomer("Jane", "T", "11111 S Rd", "", "City", "State", "91919", "7654321");
			
			ResultSet rs2 = stmt .executeQuery("select * from CUSTOMER");
			System.out.println("Database after insert");
			while(rs2.next()){
				System.out.println(rs2.getString("FirstName") + " " + rs2.getString("LastName"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void addCustomer(String first, String last, String address, String address2, String city, String state, String zip, String phone){
		try{
			String add = "insert into CUSTOMER (FirstName, LastName, Address, City, State, Zip, Phone)"
					+ " values (\"" + first + "\", \"" + last + "\", \"" + address +  "\", \"" + city + 
					"\", \"" + state + "\", \"" + zip + "\", \"" + phone + "\")";
			System.out.println(add);
			stmt.executeUpdate(add);
		}catch(Exception e){
			
		}
	}
	public static void modifyCustomer(){
			
	
	}
}
