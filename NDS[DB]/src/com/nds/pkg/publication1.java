package com.nds.pkg;
import java.sql.ResultSet;
import java.text.*;
import java.util.Arrays;

/**
 * A publication object contains information on a publication (magazine, newspaper, newsletter, etc.) that is offered to customers.
 *
 * @author Lee Katsumata
 * @version 1
 */
public class publication1 {
	private String title;
	private String genre;
	private double price;
	private boolean [] days = new boolean [7];
	private String frequency;
	private int PID;
	private String status;
	private connect cn;
	String [] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	NumberFormat fmatr = new DecimalFormat("#0.00"); 
	
	/**
	 * Constructs a publication that is delivered either monthly or weekly and adds it to the database.
	 * 
	 * @param t		The title of the publication
	 * @param g		The genre of the publication
	 * @param p		The price of the publication
	 * @param d		The array holding an indicator for whether the publication is delivered on each day of the week (Monday to Sunday)
	 * @param f		The frequency of the publication (Daily, Weekly, Monthly)
	 **/
	public publication1(String t, String g, double p, boolean d [], String f){
		cn = new connect();
		cn.addPublication(t, g, p, d, f);
		PID = cn.getPublicationID(t);
		status = "ACTIVE";
		title = t;
		genre = g;
		price = p;
		days = d.clone();
		frequency = f;
	}
	
	/**
	 * Constructs a publication that is delivered daily and adds it to the database.
	 * 
	 * @param t		The title of the publication
	 * @param g		The genre of the publication
	 * @param p		The price of the publication
	 **/
	public publication1(String t, String g, double p){
		cn = new connect();
		Arrays.fill(days, true);
		frequency = "Daily";
		cn.addPublication(t, g, p, days, frequency);
		PID = cn.getPublicationID(t);
		status = "ACTIVE";
		title = t;
		genre = g;
		price = p;
	}
	
	/**
	 * Constructs a publication based off of information received from the database.
	 * The ID is given and the publication is searched for in the database.
	 * PublicationID is set to 0 if the search fails.
	 * 
	 * @param co	The current connection
	 * @param ID	The publication identifier
	 **/
	public publication1(connect co, int ID){
		cn = co;
		ResultSet r = cn.searchPublication(ID, "");
		try{
			while(r.next()){
				PID = r.getInt("PublicationID");
				status = r.getString("Status");
				title = r.getString("PublicationName");
				price = r.getDouble("Price");
				frequency = r.getString("Frequency");
				genre = r.getString("Genre");
				for(int i=0; i<7; i++){
					days[i] = r.getBoolean(dayNames[i]);
				}
			}
		}
		catch(Exception e){
			PID = 0;
		}
	}
	
	/**
	 * Returns the Publication ID
	 *
	 * @return int
	 **/
	public int getID(){
		return PID;
	}
	
	/**
	 * Provides a print-friendly String with information on the publication
	 *
	 * @return String
	 **/
	public String toString(){
		String deliver = "";
		for(int i=0; i<7; i++){
			if(days[i])
				deliver+= "\n" + dayNames[i];
		}
		return "Publication ID: " + PID + "\nPublication Name: " + title + "\nGenre: " + genre + "\nPrice: $" + fmatr.format(price) + "\nFrequency: " + frequency +
				"\nDelivery Dates:" + deliver;
	}
	
	/**
	 * Modifies the price of the publication. Returns whether the modification was successful.
	 * Passes the modification to the database.
	 *
	 * @return boolean
	 * 
	 * @param nPrice	The new price of the publication
	 **/
	public boolean modPrice(double nPrice){
		price = nPrice;
		return cn.modPublicationInfo(PID, nPrice);
	}
	
	/**
	 * Modifies the status of the publication
	 *
	 * @return boolean
	 * 
	 * @param st	The new status of the publication
	 **/
	public boolean setStatus(String st){
		if(status.equals(st)){
			return false;
		}
		else{
			status = st;
			return cn.modPublicationInfo(PID, st);
		}
	}
}
