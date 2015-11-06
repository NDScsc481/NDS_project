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
	
	public void addSubscritions(int CID){
		try{
			stmt.executeUpdate("insert into SUBSCRIPTIONS (CustomerID) values (\"" + CID + "\")");
		}
		catch(Exception e){}
	}
	
	public int getSubscriptionID(int CID){
		try{
			ResultSet rs = stmt.executeQuery("select * from subscriptions where CustomerID = " + CID);
			if(rs.next()){
				return rs.getInt("SubscriptionID");
			}
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public ResultSet getSubscriptions(int CID){
		ResultSet rs;
		try{
			rs = stmt.executeQuery("select * from subscriptions where CustomerID = " + CID);
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
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
		catch(Exception e){}
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
	
	//get ID based off of either the customer's phone number
	public int getCustomerID(String pN){
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
	
	public void addPublication(String t, String g, double p, String f){
		try{
			stmt.executeUpdate("insert into CUSTOMER (PublicationName, Description, Price, Frequency) values (\"" + t + "\", \"" + g + "\", " + p + ", \"" + f + "\")");
		}
		catch(Exception e){}
	}
	
	public int getPublicationID(String t){
		try{
			ResultSet rs = stmt.executeQuery("select * from publications where PublicationName = \"" + t + "\"");
			if(rs.next()){
				return rs.getInt("PublicationID");
			}
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public ResultSet searchPublication(int PID, String t){
		ResultSet rs;
		try{
			if(PID!=0){
				rs = stmt.executeQuery("select * from publication where PublicationID = " + PID);
			}
			else{
				rs = stmt.executeQuery("select * from publication where PublicationName = \"" + t + "\"");
			}
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean modPublicationInfo(int PID, double newPrice){
		try{
			stmt.executeUpdate("update publications set Price = " + newPrice + " where PublicationID = " + PID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean modPublicationInfo(int PID, String st){
		try{
			stmt.executeUpdate("update publications set Status = \"" + st + "\" where PublicationID = " + PID);
			return true;
		}
		catch(Exception e){
			return false;
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
