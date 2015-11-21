package code;
import java.util.LinkedList;
import java.sql.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.lang.Object;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

import java.time.LocalDate; 
import java.time.LocalDateTime;

import java.util.*;
public class PullTodaysCustomerDeliveries {
	static LinkedList<publication> todaysPubList = new LinkedList<publication>();
	static LinkedList<customer> cList = new LinkedList<customer>();
	public static LinkedList<customer> generateTodaysPublicationList(){
		publication todaysPub;
		connect cn = new connect();
		ResultSet rsPub = cn.getAllPublications();
		try{
			while(rsPub.next()){

				String issueDate = rsPub.getString("IssueDate");
				String freq = rsPub.getString("Frequency");
				todaysPub = new publication(rsPub.getInt("PublicationID"));
				String nextDate = todaysPub.getNextIssueDate(issueDate, freq);
			//	System.out.println("IssueDate: "+ issueDate + " NextIssueDate: "+ nextDate+  " of pub ID: "+ rsPub.getInt("PublicationID"));
				Date today = DateTime.getTimeNow();
				//Date date = DateTime.strToDate(nextDate);
				String todayStr = DateTime.dateToStr(today);
				if(nextDate.equals(todayStr)){
					todaysPub = new publication(rsPub.getInt("PublicationID"));
			System.out.println( "line 32 : "+todaysPub);
					todaysPubList.add(todaysPub);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		//return todaysPubList;
	//for(int i = 0; i< todaysPubList.size(); i++){
		//System.out.println(todaysPubList.get(i));
	//}
	customer cust;
	subscriptions subs;
	ResultSet rsSub;// =;
	ResultSet rsCust;

	for(int i = 0; i<todaysPubList.size(); i++){//getCustIDFromPubID(int PID){
		rsSub = cn.searchCustomerWhoSubscribeTo(todaysPubList.get(i).PID);
		int pubID = todaysPubList.get(i).PID;
		System.out.println("pub Id: " + todaysPubList.get(i).PID);
		
		try{
			if(rsSub.next()){
				//int CID = rsSub.getInt("CustomerID");
				System.out.println("custID from result set: " + rsSub.getInt("CustomerID"));
				rsCust = cn.searchCustomer(rsSub.getInt("CustomerID"), "", "");
				try{
					if(rsCust.next()){
						int id = rsCust.getInt("CustomerID");
						String name = rsCust.getString("FirstName");
						String lName = rsCust.getString("LastName");								
						String address = rsCust.getString("Address");
						String city = rsCust.getString("City");
						String state = rsCust.getString("State");
						String zip = rsCust.getString("Zip");
					    cust = new customer(id, pubID, name, lName, address, city, state, zip, true);
					    cList.add(cust);

					}
				}catch(Exception e){
					e.printStackTrace();
				}		
			}
				
			}
		catch(Exception e){
			e.printStackTrace();
		}		
	   		
		}
		
	
	return cList;	
	}
}
