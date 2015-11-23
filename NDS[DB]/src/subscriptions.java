<<<<<<< HEAD
import java.text.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.lang.Object;
import java.util.Calendar;
import java.util.*;
import java.sql.ResultSet;
public class subscriptions{
	private int CID;
	private int SID;
<<<<<<< HEAD
	private int PID;
	public double totalPrice;
=======
	private int PID;//=0;
>>>>>>> feb08a6a33c4a50fc4c53017c00c3cc054a73235
	protected static Date startDate;
	protected static Date endDate; 
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private connect cn;
  
	public subscriptions(connect con,int CID, int PID){
		cn = con;
		startDate=	DateTime.getTimeNow();
		setStartDate(startDate);
		endDate= DateTime.addOneYear(startDate);
		setEndDate(endDate);
		cn.addSubscriptions(CID,PID, DateTime.dateToStr(startDate), DateTime.dateToStr(endDate));
		SID = cn.getSubscriptionID(CID);
	}
	
	public subscriptions(connect con, int ID){
		cn = con;
		ResultSet r = cn.getOneSubscription(ID);
		try{
			while(r.next()){
				CID = r.getInt("CustomerID");
				SID = r.getInt("ItenID");
				PID = r.getInt("PublicationID");
				startDate = DateTime.strToDate("StartDate");
				endDate = DateTime.strToDate("EndDate");
			}
		}
		catch(Exception e){
			SID = 0;
		}
	}
	
	public publication getPubInfo(){
		return new publication(cn, PID);
	}
	
	public static void setStartDate(Date start){
		startDate = start ;
	}
	public static void setEndDate(Date end){
		endDate = end;
	}
	
	public String getStartDate(){
		return DateTime.dateToStr(startDate);
	}
	public String getEndDate(){
		return DateTime.dateToStr(endDate);
	}
	
	public String getPeriod(){
		return getStartDate() + " - " + getEndDate();
	}
}

=======
import java.text.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.lang.Object;
import java.util.Calendar;
import java.util.*;
import java.sql.ResultSet;
public class subscriptions{
	private int CID;
	private int SID;
	private int PID;//=0;
	String stDate;
	String endDt;
	protected static Date startDate;
	protected static Date endDate; 
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private connect cn;
  
	public subscriptions(connect con,int CID, int PID){
		cn = con;
		startDate=	DateTime.getTimeNow();
		setStartDate(startDate);
		endDate= DateTime.addOneYear(startDate);
		setEndDate(endDate);
		cn.addSubscriptions(CID,PID, DateTime.dateToStr(startDate), DateTime.dateToStr(endDate) );
		SID = cn.getSubscriptionID(CID);
	}
	
	public subscriptions(connect con, int ID){
		cn = con;
		ResultSet r = cn.getOneSubscription(ID);
		try{
			while(r.next()){
				CID = r.getInt("CustomerID");
				SID = r.getInt("ItemID");
				PID = r.getInt("PublicationID");
				startDate = DateTime.strToDate(r.getString("StartDate"));
				endDate = DateTime.strToDate(r.getString("EndDate"));
			}
			r.close();
		}
		catch(Exception e){
			SID = 0;
		}
	}
	
	public publication getPubInfo(){
		return new publication(cn, PID);
	}
	
	public static void setStartDate(Date start){
		startDate = start ;
	}
	public static void setEndDate(Date end){
		endDate = end;
	}
	
	public String getStartDate(){
		return DateTime.dateToStr(startDate);
	}
	public String getEndDate(){
		return DateTime.dateToStr(endDate);
	}
	
	public String getPeriod(){
		return getStartDate() + " to " + getEndDate();
	}
}

>>>>>>> 8aff7a63358e5271b9aa473133e9eafb536cd17b
