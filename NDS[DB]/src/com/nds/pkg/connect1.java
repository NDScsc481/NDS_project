package com.nds.pkg;
import java.sql.*;

/**
 * Establishes connection between the database and classes. May add, view, and modify fields in the database.
 *
 * @author Lee Katsumata
 * @version 1
 */
public class connect1{
	private Connection con;
	private Statement stmt;
	
	/**
	 * Constructs the connection to the database.
	 **/
	public connect1(){
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ndsnew", "root", "12345");
			stmt = con.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the connection.
	 *
	 * @return Connection
	 **/
	public Connection getConnection(){
		return con;
	}
	
	/**
	 * Returns the SubscriptionID that matches the given CustomerID in the database.
	 *
	 * @return int
	 * 
	 * @param CID	The int that identifies the customer who the caller would like to find the corresponding SubscriptionID to
	 **/
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
	
	
	/**
	 * Returns the ResultSet containing the subscription information for the given CustomerID.
	 *
	 * @return ResultSet
	 * 
	 * @param CID	The int that identifies the customer who the caller would like to get the subscription information for
	 **/
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
	
	
	/**
	 * Adds a new subscription to a given customerID's list of subscriptions.
	 * 
	 * @param SID	The int that identifies the set of subscriptions to add to
	 * @param PID	The int that identifies the publication that should be added to the subscriptions
	 **/
	public void addSubItem(int SID, int PID){
		try{
			stmt.executeUpdate("insert into SUBSCRIPTION_ITEMS (SubscriptionID, PublicationID) values (" + SID + ", " + PID + ")");
		}
		catch(Exception e){}
	}
	
	
	/**
	 * Returns the ResultSet containing the subscription items for a customer.
	 *
	 * @return ResultSet
	 * 
	 * @param SID	The int that identifies the set of subscriptions to provide
	 **/
	public ResultSet getSubItems(int SID){
		ResultSet rs;
		try{
			rs = stmt.executeQuery("select * from subscription_items where SubscriptionID = " + SID);
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Adds a new customer with the given descriptive information.
	 * 
	 * @param fN		The customer's first name
	 * @param lN		The customer's last name
	 * @param pN		The customer's phone number
	 * @param addLn1	The customer's address
	 * @param addLn2	The customer's address line two (optional)
	 * @param c			The customer's city
	 * @param st		The customer's state
	 * @param z			The customer's zip code
	 **/
	public void addCustomer(String fN, String lN, String pN, String addLn1, String addLn2, String c, String st, String z){
		String add;
		try{
			if(addLn2.length()>0){
				add = "insert into CUSTOMER (FirstName, LastName, Address, AddressLineTwo, City, State, Zip, Phone) values (\"" + fN + "\", \"" + lN + "\", \"" + addLn1 +  "\", \"" + addLn2 + "\", \"" + c + "\", \"" + st + "\", \"" + z + "\", \"" + pN +"\")";
			}
			else{
				add = "insert into CUSTOMER (FirstName, LastName, Address, City, State, Zip, Phone) values (\"" + fN + "\", \"" + lN + "\", \"" + addLn1 +  "\", \"" + c + "\", \"" + st + "\", \"" + z + "\", \"" + pN +"\")";
			}
			System.out.println(add);
			stmt.executeUpdate(add);
		}
		catch(Exception e){}
	}
	
	/**
	 * Returns the ResultSet containing the set of customers that match the given information. This method is to accept either a CustomerID or a name. 
	 * If the CustomerID is provided, one customer with the given ID will be in the ResultSet. 
	 * If a name (first, last, or both) is provided, all customers that match the given information will be in the ResultSet.
	 *
	 * @return ResultSet
	 * 
	 * @param CID	The int that identifies the customer to search for
	 * @param fN	The first name of the customer to search for
	 * @param lN	The last name of the customer to search for
	 **/
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
	
	
	/**
	 * Modifies a specified field of the customer's information. Returns whether the modification was successfully made.
	 *
	 * @return boolean
	 * 
	 * @param CID	The int that identifies the customer to be modified
	 * @param type	The type of information to be modified (FirstName, LastName, Address, etc.)
	 * @param to	The string that the specified information will be modified to
	 **/
	public boolean modCustomerInfo(int CID, String type, String to){
		try{
			stmt.executeUpdate("update customer set " + type + " = \"" + to + "\" where CustomerID = " + CID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Modifies all of the customer's information at once in the database. Returns whether the modification was successfully made.
	 * Although it is not likely that all fields will be modified, any combination of them may be modified using this method.
	 * Does not modify customer status.
	 *
	 * @return boolean
	 * 
	 * @param CID		The int that identifies the customer to be modified
	 * @param fN		The customer's new first name
	 * @param lN		The customer's new last name
	 * @param addLn1	The customer's new address
	 * @param addLn2	The customer's new address line two (optional)
	 * @param c			The customer's new city
	 * @param s			The customer's new state
	 * @param z			The customer's new zip code
	 * @param pN		The customer's new phone number
	 **/
	public boolean modCustomerInfo(int CID, String fN, String lN, String addLn1, String addLn2, String c, String s, String z, String pN){
		try{
			if(addLn2.length()>0){
				stmt.executeUpdate("update customer set FirstName = \"" + fN + "\", LastName = \"" + lN + "\", Address = \"" + addLn1 + "\", AddressLineTwo = \"" + addLn2 + "\", City = \"" + c + "\", State = \"" + s + "\", Zip = \"" + z + "\", Phone = \"" + pN + "\" where CustomerID = " + CID);
			}
			else{
				stmt.executeUpdate("update customer set FirstName = \"" + fN + "\", LastName = \"" + lN + "\", Address = \"" + addLn1 + "\", City = \"" + c + "\", State = \"" + s + "\", Zip = \"" + z + "\", Phone = \"" + pN + "\" where CustomerID = " + CID);
			}
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Returns the customer's ID based off of the phone number (unique).
	 * This is necessary because the customer ID is automatically generated within the database.
	 *
	 * @return int
	 * 
	 * @param pN	The phone number to search for the customer ID by
	 **/
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
	
	
	/**
	 * Adds a new publication to the database.
	 * 
	 * @param t		The title of the publication
	 * @param g		The genre of the publication
	 * @param p		The price of the publication
	 * @param d		The array holding an indicator for whether the publication is delivered on each day of the week (Monday to Sunday)
	 * @param f		The frequency of the publication (Daily, Weekly, Monthly)
	 **/
	public void addPublication(String t, String g, double p, boolean d [], String f){
		try{
			stmt.executeUpdate("insert into PUBLICATIONS (PublicationName, Description, Price, Frequency, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday) "
					+ "values (\"" + t + "\", \"" + g + "\", " + p + ", \"" + f + "\", " + d[0] + ", " + d[1] + ", " + d[2] + ", " + d[3] + ", " + d[4] + ", " + d[5] + ", " + d[6] + ")");
		}
		catch(Exception e){}
	}
	
	
	/**
	 * Returns the publication ID based off of the title (unique).
	 * This is necessary because the PunlicationID is automatically generated in the database.
	 *
	 * @return int
	 * 
	 * @param t		The title of the publication to get the ID for
	 **/
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
	
	
	/**
	 * Returns the ResultSet containing publications that fall under the criteria searched for.
	 * If the caller provides a PublicationID, one publication will be in the ResultSet.
	 * If the caller provides a title, all publications that contain the given title will be in the ResultSet
	 *
	 * @return ResultSet
	 * 
	 * @param PID	The int that identifies the publication to search for
	 * @param t		The title of the publication to search for
	 **/
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
	
	/**
	 * Modifies publication price. Returns whether the modification was successfully made.
	 *
	 * @return boolean
	 * 
	 * @param PID		The int that identifies the publication to modify
	 * @param newPrice	The publication's new price 
	 **/
	public boolean modPublicationInfo(int PID, double newPrice){
		try{
			stmt.executeUpdate("update publications set Price = " + newPrice + " where PublicationID = " + PID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Modifies the publication status. Returns whether the modification was successfully made.
	 *
	 * @return boolean
	 * 
	 * @param PID	The in that identifies the publication to modify
	 * @param st	The new status of the publication
	 **/
	public boolean modPublicationInfo(int PID, String st){
		try{
			stmt.executeUpdate("update publications set Status = \"" + st + "\" where PublicationID = " + PID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Closes the connection created in this class.
	 *
	 **/
	public void disconnect(){
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
