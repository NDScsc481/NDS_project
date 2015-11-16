/**
 * 
 */
package test;

import static org.junit.Assert.*;
import java.sql.*;
import java.util.LinkedList;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.AfterClass;
import java.pullCustomersAndGenerateCoordinatesList;
import java.LatLng;
import java.DestinationRouter;
/**
 * @author Dianne
 * [CustomerID: 15 Latitude: 32.796622, Longitude: -117.255909]
[CustomerID: 29 Latitude: 32.803029, Longitude: -117.244285]
[CustomerID: 16 Latitude: 32.799168, Longitude: -117.24242]
[CustomerID: 19 Latitude: 32.808509, Longitude: -117.242921]
[CustomerID: 18 Latitude: 32.80885, Longitude: -117.225517]
[CustomerID: 21 Latitude: 32.812938, Longitude: -117.225351]
[CustomerID: 22 Latitude: 32.81334, Longitude: -117.225956]
[CustomerID: 32 Latitude: 32.813595, Longitude: -117.223459]
[CustomerID: 17 Latitude: 32.840694, Longitude: -117.186765]
[CustomerID: 30 Latitude: 32.847409, Longitude: -117.259591]
[CustomerID: 31 Latitude: 32.830509, Longitude: -117.277274]
[CustomerID: 28 Latitude: 32.831065, Longitude: -117.278809]
[CustomerID: 20 Latitude: 32.830877, Longitude: -117.280677]

 *
 */
public class DestinationRouterTest {
	
	
	
	/*
	 * @Test
	 * pulls all customers in DB
	 * */
	
	public void testDistanceBetweenPoints(){
		LatLng point1 = new LatLng(512, 32.831065,-117.278809);//339 1/2 Nautilus St La Jolla CA 92037
		LatLng point2 = new LatLng(513,32.840694,-117.186765);//5207 Diane Ave san diego CA 92117
		
	
	}
	


	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
