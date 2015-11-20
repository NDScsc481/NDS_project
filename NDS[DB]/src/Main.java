
import java.sql.*;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate; 
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;


public class Main {


	public static void main(String[] args) {
		connect cn = new connect();
		Date newDate = new Date();
		String day = DateTime.getFirstInstanceOf(0);
		System.out.println("first monday of this month" + day);
		
	  /*  CODE FOR LAT LONG DRIVER PATH
	   * 
	   *   code for pulling ALL customers in DB and producing a list of customer coordinates with 
		  correlating CustomerIDs. The origin as of now is set to be the first person entered in your db and 
		  is the hard coded to be the first item on both the customer LinkedList and LatLng LinkedList.
		  
				LinkedList<LatLng> sortedLatLngList = new LinkedList<LatLng>();
				sortedLatLngList = pullCustomersAndGenerateCoordinatesList.generateCoordinatesList();
				for(int j =0; j<sortedLatLngList.size();j++){
					System.out.println(sortedLatLngList.get(j));
				}*/
				
		
		
		//MOCK PUBLICATION DATA		
//		LocalDate localDate = LocalDate.of(2011, 7, 1);
//		publication aPub = new publication("Eye Witness News", "News", 1.25, "daily", localDate.toString());
//		LocalDate localDate2 = LocalDate.of(2015, 8, 9);
//		publication aPub2 = new publication("people", "Entertainment", 5.25, "weekly", localDate2.toString());
//		LocalDate localDate3 = LocalDate.of(2015, 8, 1);
//		publication aPub3 = new publication("Mens Health", "Health", 6.25, "monthly", localDate3.toString());
//		LocalDate localDate4 = LocalDate.of(2015, 8, 14);
//		publication aPub4 = new publication("Time", "Current Events", 6.89, "monthly", localDate4.toString());
//		LocalDate localDate5 = LocalDate.of(2015, 8, 5);
//		publication aPub5 = new publication("Gardeners World", "Hobbies", 5.36, "weekly", localDate5.toString());
//		LocalDate localDate6 = LocalDate.of(2015, 8, 9);
//		publication aPub6 = new publication("Seventeen", "Fashion", 7.36, "monthly", localDate6.toString());
//		LocalDate localDate7 = LocalDate.of(2015, 8, 1);
//		publication aPub7 = new publication("ESPN", "Sports", 6.25, "monthly", localDate7.toString());
//		LocalDate localDate8 = LocalDate.of(2015, 8, 14);
//		publication aPub8 = new publication("Local News Paper", "news", 1.25, "daily", localDate8.toString());		

				
		 

		/*MOCK CUSTOMER INFO WITH ADDRESS REAL ADDRESSES
		
		customer organization = new customer("Organization","Profile", "4516 Mission Blvd", "", "San Diego", "CA", "92109", "8888888888", 4);
		customer mockCust1 = new customer("Sara","Leal", "1501 Garnet Ave", "", "San Diego", "CA", "92109", "6666666666",12);
		customer mockCust2 = new customer("Patrick","Griffen", "5207 Diane Ave", "", "San Diego", "CA", "92117", "5555555555",17);
		customer mockCust3 = new customer("Matt","Gerold", "2523 Beryl St", "", "San Diego", "CA", "92109", "4444444444",14);
		customer mockCust4 = new customer("Pierce","Bryce", "4954 Collingwood Dr", "", "San Diego", "CA", "92109", "3333333333",9);
		customer mockCust5 = new customer("Ashley","Dorris", "6611 Neptune Place", "", "La Jolla", "CA", "92037", "6199857129",22);
		customer mockCust6 = new customer("Brandon","Blumm", "5023 San Joaquin Dr", "", "San Diego", "CA", "92109", "6194227129",19);
		customer mockCust7 = new customer("Brandon","Blum", "5041 San Joaquin Dr", "", "San Diego", "CA", "92109", "2212222222",16);
		customer mockCust8 = new customer("Gaby","Bali", "419 Bonair St", "#1", "La Jolla", "CA", "92037", "2212222229",13);
		customer mockCust9 = new customer("Dru","Downy", "339 1/2 Nautilus St", "#2", "La Jolla", "CA", "92037", "2252222222",23);
		customer mockCust10 = new customer("Carly","Snyer", "1466 Missouri St", "Apt 4", "San Diego", "CA", "92109", "2242222222",20);
		customer mockCust11 = new customer("Krista","Gomez", "7713 Hillside Drive", "", "La Jolla", "CA", "92037", "2242222229",14); 
		customer mockCust12 = new customer("Dianne","Dorris", "5024 Pacifica Dr", "", "San Diego", "CA", "92109", "8877773636",18); 
		customer mockCust45 = new customer("Megan","Green", "607 robert ave", "", "Chula Vista", "CA", "91910", "8889996666",17); */



//	public static void addCustomer(String first, String last, String address, String address2, String city, String state, String zip, String phone){
//		try{
//			String add = "insert into CUSTOMER (FirstName, LastName, Address, City, State, Zip, Phone)"
//					+ " values (\"" + first + "\", \"" + last + "\", \"" + address +  "\", \"" + city + 
//					"\", \"" + state + "\", \"" + zip + "\", \"" + phone + "\")";
//			System.out.println(add);
//			stmt.executeUpdate(add);
//		}catch(Exception e){
//			
//		}
	}
	
	
	
	//	public static void modifyCustomer(){
//			
//	
//	}
}



		
