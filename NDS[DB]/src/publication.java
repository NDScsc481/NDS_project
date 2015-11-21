import java.sql.ResultSet;
import java.text.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.lang.Object;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

public class publication {
	private String title;
	private String genre;
	private double price;
	private String frequency;
	private int PID;
	private String status;
	protected String firstIssuedOn;
	private connect cn;
	NumberFormat fmatr = new DecimalFormat("$#.##"); 
	public publication(connect con, String tit, String gen, double prc, String freq, boolean[] weekdays){
		cn = con;
		int intWeekday =0;
		if(freq =="daily")
			firstIssuedOn = DateTime.getFirstInstanceOf(intWeekday);
		else{
			for(int i =0; i< weekdays.length; i++){
				if(weekdays[i])
					intWeekday = i;
			}
			firstIssuedOn = DateTime.getFirstInstanceOf(intWeekday);
		}
		
		//cn.addPublication(tit, gen, prc, freq, issuedOn );
		//firstIssuedOn = issuedOn;
		PID = cn.getPublicationID(title);
		status = "ACTIVE";
		title = tit;
		genre = gen;
		price = prc;
		frequency = freq;
	}
	
	public publication(connect con, int ID){
		cn = con;
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
			r.close();
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
	
	public double getPrice(){
		return price;
	}
	
	public String getBillTitle(){
		return title + " - " +frequency;
	}
}
