package java;
import java.sql.*;
import java.util.LinkedList;
public class pullCustomersAndGenerateCoordinatesList {
	LinkedList<LatLng> latLngList = new LinkedList<LatLng>();
	LinkedList<Integer> cList = new LinkedList<Integer>();
	public LinkedList<LatLng> generateCoordinatesList(){
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
		for(int j =0; j<latLngList.size();j++){
			System.out.println(latLngList.get(j));
		}
		LinkedList<LatLng> sortedLatLng = DestinationRouter.distanceSort(latLngList);
		for(int j =0; j<sortedLatLng.size();j++){
			System.out.println(sortedLatLng.get(j));
		}
		return sortedLatLng;
	}

}
