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
public class DateTime {
	public static Date strToDate(String strDate){
		Date result;
		try{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			result = df.parse(strDate);
			
		}catch (ParseException pe){
			pe.printStackTrace();
			result = null;
		}
		return result;
		
	}
	public static String dateToStr(Date date){
		
		String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);
		System.out.println(newstring); // 2011-01-18
		return newstring;
	}
	public static Date getTimeNow(){
		Date now = new Date();
		System.out.println("DateTime getTimenow: " + now);

		return now;
	}
	public static Date addOneYear(Date start){
		 Calendar myCal = Calendar.getInstance();
			myCal.setTime(start);
			myCal.add(Calendar.YEAR, 1);
			
			System.out.println("DateTime addOneYear: " + myCal.getTime());
			return myCal.getTime();
	}
	public static Date addOneWeek(Date fromHere){
		Calendar myCal = Calendar.getInstance();
		myCal.setTime(fromHere);
		myCal.add(Calendar.WEEK_OF_MONTH, 1);
		System.out.println("adding one week: " + myCal.getTime());
		return myCal.getTime();
	}
	public static Date addOneDay(Date fromHere){
		Calendar myCal = Calendar.getInstance();
		myCal.setTime(fromHere);
		myCal.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println("adding one week: " + myCal.getTime());
		return myCal.getTime();
	}
	public static Date addOneMonth(Date fromHere){
		Calendar myCal = Calendar.getInstance();
		myCal.setTime(fromHere);
		myCal.add(Calendar.MONTH, 1);
		
		//System.out.println("adding one week: " + myCal.getTime());
		return myCal.getTime();
	}
	public static int getDayOfTheWeek(Date fromHere){
		Calendar myCal = Calendar.getInstance();
		myCal.setTime(fromHere);
		return myCal.DAY_OF_WEEK;
	}
//	public Calendar getDayFromIntValue(int dayNum){
//		Calendar weekDay = Calendar.getInstance();
//		switch(dayNum){
//		case 1: weekDay.set 
//		}
	}


