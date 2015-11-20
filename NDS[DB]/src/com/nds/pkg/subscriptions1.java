package com.nds.pkg;
import java.sql.ResultSet;
import java.text.*;
import java.util.*;

/**
 * A subscription object holds an identifier that links all of a customer's subscriptions to the customer. 
 * It also contains the monthly total for all of a customer's subscriptions.
 *
 * @author Lee Katsumata
 * @version 1
 */
public class subscriptions1{
	private int CID;
	private int SID;
	private double totalPrice;
	NumberFormat fmatr = new DecimalFormat("#0.00"); 
	
	/**
	 * Constructs a subscription for the automatically generated (upon creation of a customer) subscription from the database.
	 * 
	 * @param cn	The current connection
	 * @param ID		The customer identifier
	 **/
	public subscriptions1(connect cn, int ID){
		CID = ID;
		SID = cn.getSubscriptionID(ID);
		totalPrice = 0;
	}
	
	/**
	 * Constructs a subscription for the automatically generated (upon creation of a customer) subscription from the database.
	 * This constructor is for when information on an already active customer is being accessed.
	 * 
	 * @param ID		The customer identifier
	 * @param SD		The subscription identifier
	 * @param tot		The customer's total price for subscriptions
	 **/
	public subscriptions1(int ID, int SD, double tot){
		CID = ID;
		SID = SD;
		totalPrice = tot;
	}
	
	/**
	 * Returns the total price for the customer's subscriptions
	 * 
	 * @return double
	 **/
	public double getTotal(){
		return totalPrice;
	}
	
	/**
	 * Adds a new publication to the customer's list of subscriptions
	 * 
	 * @param cn	The current connection
	 * @param p		The publication to be added
	 **/
	public void addSubItem(connect cn, publication1 p){
		cn.addSubItem(SID, p.getID());
	}
	
	public ArrayList<publication1> getSubItems(connect cn){
		ArrayList<publication1> pubs = new ArrayList<>();
		ResultSet r = cn.getSubItems(SID);
		try{
			while(r.next()){
				pubs.add(new publication1(cn, r.getInt("PublicationID")));
			}
			pubs.trimToSize();
			return pubs;
		}
		catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Provides a print-friendly String with information on the subscriptions
	 *
	 * @return String
	 **/
	public String toString(){
		return "Total Price: " + fmatr.format(totalPrice);
	}
}
