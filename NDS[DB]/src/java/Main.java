package java;
import java.sql.*;
import java.util.LinkedList;
public class Main {


	public static void main(String[] args) {
	

		LinkedList<LatLng> latLngList = new LinkedList<LatLng>();
		LinkedList<Integer> cList = new LinkedList<Integer>();

		LatLng coordinates;
		connect cn = new connect();
		ResultSet r = cn.getAll();
		ResultSet rsCoor;
		int custID;
		try{
			while(r.next()){
				custID = r.getInt("CustomerID");
				System.out.println("CustomerId: "+ custID + ", "+r.getString("FirstName") + " " + r.getString("LastName"));
				cList.add(custID);		
				}
			 }
		catch(Exception e){
			e.printStackTrace();
		}		
		for(int i =0; i<cList.size(); i++){
		   	rsCoor = cn.searchCustomerCoordinates(cList.get(i));
		   	try{
		   		if(rsCoor.next()){
					double latitude = rsCoor.getDouble("Latitude");
					double longitude = rsCoor.getDouble("Longitude");
					coordinates = new LatLng(cList.get(i), latitude, longitude);
					latLngList.add(coordinates);
				}
		   	}catch(Exception e){
				e.printStackTrace();
			}		
		   	
			
		}
		for(int j =0; j<latLngList.size();j++){
			System.out.println(latLngList.get(j));
		}
		LinkedList<LatLng> sortedLatLng = DestinationRouter.distanceSort(latLngList);
		for(int j =0; j<sortedLatLng.size();j++){
			System.out.println(sortedLatLng.get(j));
		}
//		ListIterator<Integer> iterator = cList.listIterator();
//		 while (iterator.hasNext()){
//		   	rsCoor = cn.searchCustomerCoordinates(iterator.next());
//	     	 System.out.println(iterator.next());
//				while(rsCoor.next()){
//					double latitude = rsCoor.getDouble("Latitude");
//					double longitude = rsCoor.getDouble("Longitude");
//					coordinates = new LatLng(latitude, longitude);
//					latLngList.add(coordinates);
//				}
//				ListIterator<LatLng> iteratorFun =latLngList.listIterator();
//				 while (iteratorFun.hasNext())
//					 System.out.println(iterator.next());
//				

//		try{
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ndsdb", "root", "12345");
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from CUSTOMER");
//			System.out.println("Database before insert");
//			while(rs.next()){
//				System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
//			}
//			
//			addCustomer("Jane", "T", "11111 S Rd", "", "City", "State", "91919", "7654321");
//			
//			ResultSet rs2 = stmt .executeQuery("select * from CUSTOMER");
//			System.out.println("Database after insert");
//			while(rs2.next()){
//				System.out.println(rs2.getString("FirstName") + " " + rs2.getString("LastName"));
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		
//	}
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


//customer d = new customer(9);
		
//customer organization = new customer("Organization","Profile", "4516 Mission Blvd", "", "San Diego", "CA", "92109", "8888888888");
//customer mockCust1 = new customer("Sara","Leal", "1501 Garnet Ave", "", "San Diego", "CA", "92109", "6666666666");
//customer mockCust2 = new customer("Patrick","Griffen", "5207 Diane Ave", "", "San Diego", "CA", "92117", "5555555555");
//customer mockCust3 = new customer("Matt","Gerold", "2523 Beryl St", "", "San Diego", "CA", "92109", "4444444444");
//customer mockCust4 = new customer("Pierce","Bryce", "4954 Collingwood Dr", "", "San Diego", "CA", "92109", "3333333333");
//customer mockCust5 = new customer("Ashley","Dorris", "6611 Neptune Place", "", "La Jolla", "CA", "92037", "6199857129");
//customer mockCust6 = new customer("Brandon","Blumm", "5023 San Joaquin Dr", "", "San Diego", "CA", "92109", "6194227129");
//customer mockCust7 = new customer("Brandon","Blum", "5041 San Joaquin Dr", "", "San Diego", "CA", "92109", "2212222222");
//customer mockCust8 = new customer("Gaby","Bali", "419 Bonair St", "#1", "La Jolla", "CA", "92037", "2212222229");
//customer mockCust9 = new customer("Dru","Downy", "339 1/2 Nautilus St", "#2", "La Jolla", "CA", "92037", "2252222222");
//customer mockCust10 = new customer("Carly","Snyer", "1466 Missouri St", "Apt 4", "San Diego", "CA", "92109", "2242222222");
//customer mockCust11 = new customer("Krista","Gomez", "7713 Hillside Drive", "", "La Jolla", "CA", "92037", "2242222229"); 
//customer organization = new customer("Dianne","Dorris", "5024 Pacifica Dr", "", "San Diego", "CA", "92109", "8877773636");