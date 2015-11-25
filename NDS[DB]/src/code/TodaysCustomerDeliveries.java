
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
	
	static LinkedList<LatLng> todaysPubList = new LinkedList<LatLng>();
	static LinkedList<Integer> IDList = new LinkedList<Integer>();

	public static LinkedList<Integer> generateTodaysCustDeliveries(){
		LatLng latLngPair;
		connect cn = new connect();
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int date = cal.get(Calendar.DAY_OF_MONTH);
		ResultSet rs = cn.getAllPublications(day,date);
		try{
			while(rs.next()){
				latLngPair = new LatLng(rs.getInt("CustomerID"), rs.getDouble("Latitude"),rs.getDouble("Longitude"));
				todaysPubList.add(latLngPair);
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		IDList = DestinationRouter.distanceSort(todaysPubList);
	return IDList;

}}
