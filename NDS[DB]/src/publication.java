import java.sql.ResultSet;
import java.text.*;
import java.util.Date;

public class publication {
	private String title;
	private String genre;
	private double price;
	private String frequency;
	private int PID;
	private String status;
	protected String firstIssuedOn;
	private connect cn = new connect();
	NumberFormat fmatr = new DecimalFormat("$#.##"); 
	//added issuedOn to paramters which marks the date the publication was issued
	public publication(String tit, String gen, double prc, String freq, String issuedOn){
		cn.addPublication(tit, gen, prc, freq, issuedOn );
		firstIssuedOn = issuedOn;
		PID = cn.getPublicationID(title);
		status = "ACTIVE";
		title = tit;
		genre = gen;
		price = prc;
		frequency = freq;
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
				firstIssuedOn = r.getString("IssueDate");
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
	/*
	 * This function calculates the next issue date by using the date the publication was originally issued 
	 * on (firstIssuedOn) and adding whatever increment that correlates to it (e.g. Monthly, weekly, daily)
	 * enough times to get to the next issue date. 
	 * */
	public String getNextIssueDate(){
		Date today = new Date();
		Date nextDate = DateTime.strToDate(firstIssuedOn);
		 if(this.frequency == "daily"){
				do{
				    nextDate = DateTime.addOneDay(nextDate);
				}while(nextDate.before(today));
			}
		 else if(this.frequency == "weekly"){
			do{
			    nextDate = DateTime.addOneWeek(nextDate);
			}while(nextDate.before(today));
		}
		 /*else then frequency = monthly*/
		 else{
			 do{
				nextDate = DateTime.addOneMonth(nextDate);
				}while(nextDate.before(today));
			 
		 }
		 return DateTime.dateToStr(nextDate);
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
