package java;
import java.sql.ResultSet;
import java.text.*;


public class publication {
	private String title;
	private String genre;
	private double price;
	private String frequency;
	private int PID;
	private String status;
	private connect cn = new connect();
	NumberFormat fmatr = new DecimalFormat("#0.00"); 
	
	public publication(String t, String g, double p, String f){
		cn.addPublication(t, g, p, f);
		PID = cn.getPublicationID(t);
		status = "ACTIVE";
		title = t;
		genre = g;
		price = p;
		frequency = f;
	}
	
	public publication(int ID){
		ResultSet r = cn.searchPublication(ID, "");
		try{
			while(r.next()){
				PID = r.getInt("PublicationID");
				status = r.getString("Status");
				title = r.getString("PublicationName");
				price = r.getDouble("Price");
				frequency = r.getString("Frequency");
				genre = r.getString("Genre");
			}
		}
		catch(Exception e){
			PID = 0;
		}
	}
	
	public String toString(){
		return "Publication: \nPublication Name: " + title + "\nGenre: " + genre + "\nPrice: $" + fmatr.format(price) + "\nFrequency: " + frequency;
	}
	
	public boolean modPrice(double nPrice){
		price = nPrice;
		return cn.modPublicationInfo(PID, nPrice);
	}
	
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
