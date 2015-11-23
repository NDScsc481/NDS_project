
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
public class TodaysCustomerDeliveries {
	
	static LinkedList<publication> todaysPubList = new LinkedList<publication>();
	static LinkedList<customer> cList = new LinkedList<customer>();
	
	public static LinkedList<customer> generateTodaysCustDeliveries(){
		publication todaysPub;
		connect cn = new connect();
		ResultSet rs = cn.getAllPublications();
		
		try{
			while(rs.next()){

				//String issueDate = rsPub.getString("IssueDate");
				//String freq = rsPub.getString("Frequency");
				todaysPub = new publication(cn,rs.getInt("PublicationID"));
				String nextDate = todaysPub.getNextIssueDate(rs.getString("IssueDate"), rs.getString("Frequency"));
				Date today = DateTime.getTimeNow();
				//Date date = DateTime.strToDate(nextDate);
				String todayStr = DateTime.dateToStr(today);
				
				if(nextDate.equals(todayStr)){
					//todaysPub = new publication(rsPub.getInt("PublicationID"));
					//System.out.println( "line 32 : "+todaysPub);
					System.out.println("IssueDate: "+ rs.getString("IssueDate") + " NextIssueDate: "+ nextDate+  " of pub ID: "+ rs.getInt("PublicationID"));

					todaysPubList.add(todaysPub);
				}
				//cn.disconnect();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	
	customer cust;
	//ResultSet rsSub;
	

	for(int i = 0; i<todaysPubList.size(); i++){
		rs = cn.searchCustomerWhoSubscribeTo(todaysPubList.get(i).PID);
		int pubID = todaysPubList.get(i).PID;
		System.out.println("pub Id: " + todaysPubList.get(i).PID);
		
		try{
			if(rs.next()){
				//int CID = rsSub.getInt("CustomerID");
				rs = cn.searchForCustomerInView(rs.getInt("CustomerID"),0);
				try{
					if(rs.next()){
						System.out.println("custID from result set: " + rs.getString("FirstName"));

						int id = rs.getInt("CustomerID");
						String name = rs.getString("FirstName");
						String lName = rs.getString("LastName");								
						String address = rs.getString("Address");
						String city = rs.getString("City");
						String state = rs.getString("State");
						String zip = rs.getString("Zip");
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
		
	cn.disconnect();

	return cList;	
	}
	
}
