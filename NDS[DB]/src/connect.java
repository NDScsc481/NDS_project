import java.sql.*;

public class connect{
	private Connection con;
	private Statement stmt;
	
	public connect(){
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ndsdb", "root", "12345");
			stmt = con.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return con;
	}
	
	public void addCustomer(String fN, String lN, String pN, String addLn1, String addLn2, String c, String st, String z){
		String add;
		try{
			if(addLn2.length()>0){
				add = "insert into CUSTOMER (FirstName, LastName, Address, AddressLineTwo, City, State, Zip, Phone)" + " values (\"" + fN + "\", \"" + lN + "\", \"" + addLn1 +  "\", \"" + addLn2 + "\", \"" + c + "\", \"" + st + "\", \"" + z + "\", \"" + pN +"\")";
			}
			else{
				add = "insert into CUSTOMER (FirstName, LastName, Address, City, State, Zip, Phone)" + " values (\"" + fN + "\", \"" + lN + "\", \"" + addLn1 +  "\", \"" + c + "\", \"" + st + "\", \"" + z + "\", \"" + pN +"\")";
			}
			System.out.println(add);
			stmt.executeUpdate(add);
		}
		catch(Exception e){
			
		}
	}
	
	public ResultSet searchCustomer(int CID, String fN, String lN){
		ResultSet rs;
		try{
			if(CID!=0){
				rs = stmt.executeQuery("select * from customer where CustomerID = " + CID);
			}
			else{
				if(fN.length()==0)
					rs = stmt.executeQuery("select * from customer where LastName = \"" + lN + "\"");
				else{
					if(lN.length()>0)
						rs = stmt.executeQuery("select * from customer where FirstName = \"" + fN + "\" and LastName = \"" + lN + "\"");
					else
						rs = stmt.executeQuery("select * from customer where FirstName = \"" + fN + "\"");
				}
			}
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean modCustomerInfo(int CID, String type, String to){
		try{
			stmt.executeUpdate("update customer set " + type + " = \"" + to + "\" where CustomerID = " + CID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public int getID(String pN){
		try{
			ResultSet rs = stmt.executeQuery("select * from customer where Phone = \"" + pN + "\"");
			if(rs.next()){
				return rs.getInt("CustomerID");
			}
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public String getStat(String pN){
		try{
			ResultSet rs = stmt.executeQuery("select * from customer where Phone = \"" + pN + "\"");
			if(rs.next()){
				return rs.getString("Status");
			}
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void disconnect(){
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
