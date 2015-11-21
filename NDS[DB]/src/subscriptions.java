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
	public double totalPrice;
	protected static Date startDate;
	protected static Date endDate;
	NumberFormat fmatr = new DecimalFormat("#0.00"); 
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private publication p;
    private connect cn;
  
	public subscriptions(connect con,int CID, int PID){
		cn = con;
		startDate=	DateTime.getTimeNow();
		setStartDate(startDate);
		endDate= DateTime.addOneYear(startDate);
		setEndDate(endDate);
		cn.addSubscriptions(CID,PID, DateTime.dateToStr(startDate), DateTime.dateToStr(endDate) );
		SID = cn.getSubscriptionID(CID);
		totalPrice = 0;
		
	}
	
	public subscriptions(connect con, int ID){
		cn = con;
		ResultSet r = cn.getOneSubscription(ID);
		try{
			while(r.next()){
				CID = r.getInt("CustomerID");
				SID = r.getInt("SubscriptionID");
				PID = r.getInt("PublicationID");
				totalPrice = r.getDouble("total");
				startDate = 
			}
		}
		catch(Exception e){
			SID = 0;
		}
	}
	
	public publication getPubInfo(){
		return new publication(cn, PID);
	}
	
	public double getTotal(){
		return totalPrice;
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

