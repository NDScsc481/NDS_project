import java.sql.*;
import java.util.LinkedList;
import java.util.ListIterator;
public class pullCustomersAndGenerateCoordinatesList {
	static LinkedList<LatLng> latLngList = new LinkedList<LatLng>();
	static LinkedList<Integer> cList = new LinkedList<Integer>();
	public static LinkedList<LatLng> generateCoordinatesList(){
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
	
		
		LinkedList<LatLng> sortedLatLng = DestinationRouter.distanceSort(latLngList);
		return sortedLatLng;
	}

}
