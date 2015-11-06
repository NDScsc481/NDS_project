/**
 * Newspaper Deliver Service <p>
 * Team 1 <p>
 * Computer Science 481 - Software Engineering <p>
 * 
 * @version 1.0
 * @author Matthew Satow
 */
public class DestinationRouter{		
	
	
	/**
	 * Latitude
	 */
	private static double OriginX = 0;
	/**
	 * Longitude
	 */
	private static double OriginY = 0;
	
	/**
	 * Sets the latitude and longitude origin.
	 * 
	 * @param x latitude
	 * @param y longitude 
	 */	
	public static void OriginSet(double x, double y){
		OriginX = x;
		OriginY = y;
	}	
	
	/**
	 * The main method called from DestinationRouter.
	 * Takes latitude and longitude from an array of 
	 * Customer objects and sorts by distance.
	 * 
	 * @param list The unsorted customer list 	  
	 * @return The sorted customer list
	 */
	public static Customer[] Sorter(Customer[] list){
		
		/**
		 * Origin initially starts as from delivery origin, then from next closest destination
		 */		
		Customer Origin = new Customer (OriginX,OriginY);
		
		/**
		 * SortedList is the array of customers sorted for delivery
		 */
		Customer[] SortedList = new Customer [list.length];		
		
		/**
		 * pointer to the next shortest distance
		 */
		int low = 0;
		
		/**
		 * the next lowest distance 
		 */
		double lowDist = 0.0;
		
		/**
		 * finds the lowest distance in the array of customers,
		 * puts that customer into SortedArray
		 * @see CoordDistance
		 */
		for(int i = 0; i < SortedList.length; i++){
			
			for(int j = i; j < list.length; j++){
				if (i == j){
					lowDist = CoordDistance(Origin, list[j]);
					low = j;
				}
				
				else{
					if (CoordDistance(Origin,list[j]) < lowDist){
						lowDist = CoordDistance(Origin,list[j]);
						low = j;
					}
				}
			}
			SortedList[i] = list[low];
			list[low] = list[i];
			low = 0;
			lowDist = 0;
			Origin = SortedList[i];
		}	
		return SortedList;		
	}

	/**
	 * Distance between two points on a coordinate plane.
	 * @param a first customer
	 * @param b second customer
	 * @return distance
	 */
	private static double CoordDistance(Customer a, Customer b){ 
		double x1 = a.latitude;
		double x2 = b.latitude;
		
		double y1 = a.longitude;
		double y2 = b.longitude;
		
		double dist = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
		return Math.sqrt(dist);
	}
}