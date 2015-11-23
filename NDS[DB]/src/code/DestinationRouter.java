

import java.util.LinkedList;
import java.sql.*;

public class DestinationRouter{		
	
	
	private static LatLng start; 
	
	
	public static LinkedList<Union> distanceSort(LinkedList<Union> list){
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
	
		
		LinkedList<Union> sortedList = new LinkedList<Union>();
		//sortedList.addFirst(start);			
		list.removeFirst();
		double lowDist = 0.0;
		Union shortest;
		int SIZE=list.size();
		for(int i = 0; i < SIZE; i++){
			
			shortest = list.peekFirst();
			lowDist = CoordDistance(start,shortest.getPoints());

			for(int j = 0; j< list.size() ; j++){
				Union un = list.get(j);
				double dist = CoordDistance(start, un.getPoints());
				if(dist< lowDist){
					lowDist = dist;
					shortest = un;

				}
			}
			sortedList.addLast(shortest);
			list.remove(shortest);
			start = shortest.getPoints();	
		}
		return list;		
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

