
import java.text.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.lang.Object;
import java.util.Calendar;
import java.sql.ResultSet;

import java.util.*;
public class subscriptions{
	private int CID;
	private int SID;
	private int PID;
	 String stDate;
	    String endDt;
	protected static Date startDate;
	protected static Date endDate;
	NumberFormat fmatr = new DecimalFormat("#0.00"); 
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
		endDate = end ;
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