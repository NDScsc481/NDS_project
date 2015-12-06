package connections;


import java.util.LinkedList;
import java.sql.*;

public class DestinationRouter{		
	
	private static LatLng start; 
	
	public static LinkedList<Integer> distanceSort(LinkedList<LatLng> list){
		LinkedList<Integer> sortedList = new LinkedList<Integer>();
		connect cn = new connect();
		double startLat=0.0;
		double startLng =0.0;
		ResultSet rs = cn.userGetProfile();	
		try{
			startLat= rs.getDouble("Latitude");
			startLng = rs.getDouble("Longitude");
		}catch(Exception e){
			System.out.println("error in destination router");
		}
		start = new LatLng(startLat, startLng);
		double lowDist = 0.0;
		LatLng shortest;
		int SIZE=list.size();
		for(int i = 0; i < SIZE; i++){
			shortest = list.peekFirst();
			lowDist = CoordDistance(start,shortest);
			for(int j = 0; j< list.size() ; j++){
				LatLng pair = list.get(j);
				double dist = CoordDistance(start, pair);
				if(dist< lowDist){
					lowDist = dist;
					shortest = pair;
				}
			}
			sortedList.addLast(shortest.getCustomerID());
			list.remove(shortest);
			start = shortest;	
		}
		return sortedList;		
	}

	/**
	 * Distance between two points on a coordinate plane.
	 * @param a first LatLng
	 * @param b second LatLng
	 * @return distance
	 */
	private static double CoordDistance(LatLng a, LatLng b){
		double x1 = a.lat;
		double x2 = b.lat;
		
		double y1 = a.lng;
		double y2 = b.lng;
		
		double dist = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);

		return Math.sqrt(dist);
	}
}

