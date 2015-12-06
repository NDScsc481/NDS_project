import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class connectTest {
	connect con;
	
	@Before
	public void before(){
		con = new connect();
	}
	
	@After
	public void after(){
		con.disconnect();
	}

	@Test
	public void testGetConnection() {
		Connection c = con.getConnection();
		assertNotNull(c);
	}

	@Test
	public void testGetSubscriptionID() {
		int zero = con.getSubscriptionID(0);
		int valid = con.getSubscriptionID(1);
		int neg = con.getSubscriptionID(-2);
		
		assertSame(0, zero);
		assertNotSame(0, valid);
		assertSame(0, neg);
	}

	@Test
	public void testGetSubscriptions() {
		ResultSet valid = con.getSubscriptions(1);
		ResultSet neg = con.getSubscriptions(-1);
		ResultSet zero = con.getSubscriptions(0);
		
		assertNotNull(valid);
		assertNull(neg);
		assertNull(zero);
	}

	@Test
	public void testGetOneSubscription() {
		ResultSet valid = con.getOneSubscription(1);
		ResultSet neg = con.getOneSubscription(-1);
		ResultSet zero = con.getOneSubscription(0);
		
		assertNotNull(valid);
		assertNull(neg);
		assertNull(zero);
	}
	
	@Test
	public void testGetLatLngValues() {
		ResultSet valid = con.getLatLngValues(1);
		ResultSet neg = con.getLatLngValues(-1);
		ResultSet zero = con.getLatLngValues(0);
		
		assertNotNull(valid);
		assertNull(neg);
		assertNull(zero);
	}

	@Test
	public void testSearchCustomerCoordinates() {
		ResultSet valid = con.searchCustomerCoordinates(1);
		ResultSet neg = con.searchCustomerCoordinates(-1);
		ResultSet zero = con.searchCustomerCoordinates(0);
		
		assertNotNull(valid);
		assertNull(neg);
		assertNull(zero);
	}

	@Test
	public void testGetAll() {
		ResultSet valid = con.getAll();
		assertNotNull(valid);
	}

	@Test
	public void testSearchCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testModCustomerInfoIntStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testModCustomerInfoIntStringDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCustomerID() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPublication() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPublicationID() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchPublication() {
		fail("Not yet implemented");
	}

	@Test
	public void testModPublicationInfoIntDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testModPublicationInfoIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserSetProfile() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserGetProfile() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserGetfilePath() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserModData() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisconnect() {
		fail("Not yet implemented");
	}

}
