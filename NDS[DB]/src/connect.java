import java.sql.*;
import java.util.Date;
public class connect{
	private Connection con;
	private Statement stmt;
	
	public connect(){
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3308/saturdays_db", "root", "12345");
			stmt = con.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return con;
	}
	/*
	 * add subscriptions now additionally needs two more parameters String start and String end (strings in form "yyyy-MM-dd")
	 * */
	public void addSubscriptions(int CID, int PID,String start ,String end){
		try{
			stmt.executeUpdate("insert into SUBSCRIPTIONS (CustomerID, PublicationID, StartDate, EndDate) values (\"" + CID + "\",\"" + PID+"\",  \"" + start+ "\", \"" + end + "\")");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getSubscriptionID(int CID){
		try{
			ResultSet rs = stmt.executeQuery("select * from subscriptions where CustomerID = " + CID);
			if(rs.next()){
				return rs.getInt("ItemID");
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
	
	public void addCustomer(String fN, String lN,  String addLn1, String addLn2, String c, String st, String z,String pN){
		String add;
		try{
			if(addLn2.length()>0){
				add = "insert into CUSTOMERS (FirstName, LastName, Address, AddressLineTwo, City, State, Zip, Phone)" + " values (\"" + fN + "\", \"" + lN + "\", \"" + addLn1 +  "\", \"" + addLn2 + "\", \"" + c + "\", \"" + st + "\", \"" + z + "\", \"" + pN +"\")";
			}
			else{
				add = "insert into CUSTOMERS (FirstName, LastName, Address, City, State, Zip, Phone)" + " values (\"" + fN + "\", \"" + lN + "\", \"" + addLn1 +  "\", \"" + c + "\", \"" + st + "\", \"" + z + "\", \"" + pN +"\")";
			}
			System.out.println(add);
			stmt.executeUpdate(add);
			
		}
		catch(Exception e){}
	}
	/**
	 * function populates the coordinates database with generated CID from customer database
	 * lat and long.
	 */
	public void addLatLngToCustomer( int CID){
		ResultSet rs;
		String addCoordinates;
		String upsformattedAdd = "";
		try{
			if(CID != 0){
				rs = stmt.executeQuery("select * from customers where CustomerID = " + CID);
					while(rs.next()){
						upsformattedAdd = rs.getString("Zip") +", "+ rs.getString("Address") + " " + rs.getString("State");
				}

			LatLng points = computeLatLng.getLatLongPositions(upsformattedAdd);
	     	addCoordinates = "insert into COORDINATES (CustomerID, Latitude, Longitude)" + " values (\"" + CID + "\",\"" + points.lat + "\", \"" + points.lng + "\")";
			System.out.println(addCoordinates);
			stmt.executeUpdate(addCoordinates);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();

		}
	}
	
	public ResultSet getLatLngValues(int CID){
		ResultSet rs;
		try{
		    rs = stmt.executeQuery("select * from coordinates where CustomerID = \"" + CID + "\"");
		    return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
		public ResultSet searchCustomerCoordinates(int CID){
			ResultSet rs;
			try{
				if(CID!=0){
					rs = stmt.executeQuery("select * from coordinates where CustomerID = " + CID);
				}
				
				else {
					rs = null;
				}
				return rs;
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		/*
		 * Retrieves all customers who have the status == "ACTIVE"
		 */
		public ResultSet getAll(){
			ResultSet rs;
			try{
				rs = stmt.executeQuery("select * from customers where Status =\"" + "ACTIVE" + "\""  );
				return rs;
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		public ResultSet searchCustomer(int CID, String fN, String lN){
		ResultSet rs;
		try{
			if(CID!=0){
				rs = stmt.executeQuery("select * from customers where CustomerID = " + CID);
			}
			else{
				if(fN.length()==0)
					rs = stmt.executeQuery("select * from customers where LastName = \"" + lN + "\"");
				else{
					if(lN.length()>0)
						rs = stmt.executeQuery("select * from customers where FirstName = \"" + fN + "\" and LastName = \"" + lN + "\"");
					else
						rs = stmt.executeQuery("select * from customers where FirstName = \"" + fN + "\"");
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
			stmt.executeUpdate("update customers set " + type + " = \"" + to + "\" where CustomerID = " + CID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	public boolean modCustomerInfo(int CID, String type, Date to){
		try{
			stmt.executeUpdate("update customers set " + type + " = \"" + to + "\" where CustomerID = " + CID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	//get ID based off of either the customer's phone number
	public int getCustomerID(String pN){
		try{
			ResultSet rs = stmt.executeQuery("select * from customers where Phone = \"" + pN + "\"");
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
	
	public void addPublication(String title, String genre, double price, String frequency, String issuedOn){
		try{
			System.out.println("in connect add pub");

			stmt.executeUpdate("insert into publications (PublicationName, Genre, Price, Frequency, IssueDate) values (\"" + title + "\", \"" + genre + "\", \"" + price + "\", \"" + frequency + "\", \"" + issuedOn + "\")");

		}
		catch(Exception e){
			System.out.println("error in adding pub");
			e.printStackTrace();

			
		}
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
				rs = stmt.executeQuery("select * from publications where PublicationID = " + PID);
			}
			else{
				rs = stmt.executeQuery("select * from publications where PublicationName = \"" + t + "\"");
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
