package com.nds.pkg;
import java.sql.*;
import java.util.*;

/**
 * A customer object contains necessary information on the customer for deliveries and subscriptions.
 *
 * @author Lee Katsumata
 * @version 1
 */
public class customer1{
	
	private String firstName;
	private String lastName;
	private String addrLineOne;
	private String addrLineTwo;
	private String city;
	private String state;
	private String zip;
	private String phoneNum;
	private int CID;
	private String status;
	private subscriptions1 mySubs;
	private connect cn;
	
	
	/**
	 * Constructs a new customer with the given descriptive information and adds it to the database.
	 * The address is one line for this constructor.
	 * 
	 * @param fN		The customer's first name
	 * @param lN		The customer's last name
	 * @param pN		The customer's phone number
	 * @param addLn1	The customer's address
	 * @param c			The customer's city
	 * @param st		The customer's state
	 * @param z			The customer's zip code
	 **/
	public customer1(String fN, String lN, String pN, String addLn1, String c, String st, String z){
		cn = new connect();
		cn.addCustomer(fN, lN, pN, addLn1, "", c, st, z);
		CID = cn.getCustomerID(pN);
		mySubs = new subscriptions1 (cn, CID);
		status = "ACTIVE";
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = null;
		city = c;
		state = st;
		zip=z;
		phoneNum = pN;
	}
	
	/**
	 * Constructs a new customer with the given descriptive information and adds it to the database.
	 * The address is two lines for this constructor.
	 * 
	 * @param fN		The customer's first name
	 * @param lN		The customer's last name
	 * @param pN		The customer's phone number
	 * @param addLn1	The customer's address
	 * @param addLn2	The customer's address line two
	 * @param c			The customer's city
	 * @param st		The customer's state
	 * @param z			The customer's zip code
	 **/
	public customer1(String fN, String lN, String pN, String addLn1, String addLn2, String c, String st, String z){
		cn = new connect();
		cn.addCustomer(fN, lN, pN, addLn1, addLn2, c, st, z);
		CID = cn.getCustomerID(pN);
		mySubs = new subscriptions1 (cn, CID);
		status = "ACTIVE";
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = null;
		city = c;
		state = st;
		zip=z;
		phoneNum = pN;
	}
	
	/**
	 * Constructs a customer based off of information received from the database.
	 * The CustomerID is provided and the database is searched for the customer.
	 * The CustomerID is set to 0 if the search fails.
	 * 
	 * @param co	The current connection
	 * @param ID	The customer identifier
	 **/
	public customer1 (connect co, int ID){
		cn = co;
		ResultSet r = cn.searchCustomer(ID, "", "");
		try{
			while(r.next()){
				CID = r.getInt("CustomerID");
				status = r.getString("Status");
				firstName = r.getString("FirstName");
				lastName = r.getString("LastName");
				addrLineOne = r.getString("Address");
				addrLineTwo = r.getString("AddressLineTwo");
				city = r.getString("City");
				state = r.getString("State");
				zip = r.getString("Zip");
				phoneNum = r.getString("Phone");
			}
			ResultSet subs = cn.getSubscriptions(ID);
			while(subs.next()){
				mySubs = new subscriptions1(ID, subs.getInt("SubscriptionID"), subs.getDouble("TotalAmount"));
			}
		}
		catch(Exception e){
			CID = 0;
		}
	}
	
	/**
	 * Gets a list of the user's subscriptions.
	 *
	 **/
	public void getMySubscriptions(){
		ArrayList<publication1> pubs = mySubs.getSubItems(cn);
		for(int i=0; i<pubs.size(); i++){
			System.out.println((pubs.get(i)).toString());
		}
	}
	
	/**
	 * Provides a print-friendly String with information on the customer
	 *
	 * @return String
	 **/
	public String toString(){
		if(addrLineTwo!=null){
			return "Customer ID: " + CID + "\nName: " + firstName + " " + lastName + 
					"\nAddress: " + addrLineOne + "\n" + addrLineTwo + "\n" + city + ", " + state + " " + zip +
					"\nPhone Number: " + phoneNum + "\nStatus: " + status;
		}
		else{
			return "Customer ID: " + CID + "\nName: " + firstName + " " + lastName + 
					"\nAddress: " + addrLineOne + "\n" + city + ", " + state + " " + zip +
					"\nPhone Number: " + phoneNum + "\nStatus: " + status;
		}
	}
	
	/**
	 * Modifies all of the customer's information at once and sends the modifications to the database. Returns whether the modification was successfully made.
	 * Although it is not likely that all fields will be modified, any combination of them may be modified using this method.
	 * Does not modify customer status.
	 *
	 * @return boolean
	 * 
	 * @param fN		The customer's new first name
	 * @param lN		The customer's new last name
	 * @param addLn1	The customer's new address
	 * @param addLn2	The customer's new address line two (optional)
	 * @param c			The customer's new city
	 * @param s			The customer's new state
	 * @param z			The customer's new zip code
	 * @param pN		The customer's new phone number
	 **/
	public boolean modInfo(String fN, String lN, String addLn1, String addLn2, String c, String s, String z, String pN){
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		if(addLn2.length()>0)
			addrLineTwo = addLn2;
		else
			addrLineTwo = null;
		city = c;
		state = s;
		zip = z;
		phoneNum = pN;
		return cn.modCustomerInfo(CID, fN, lN, addLn1, addLn2, c, s, z, pN);
	}

	/**
	 * Modifies the status.
	 * Returns false if the modification fails or if modification to the same status is attempted.
	 *
	 * @return boolean
	 * 
	 * @param st The customer's new status
	 **/
	public boolean setStatus(String st){
		if(status.equals(st)){
			return false;
		}
		else{
			status = st;
			return cn.modCustomerInfo(CID, "Status", st);
		}
	}
}
