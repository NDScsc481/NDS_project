import java.sql.*;

public class customer extends connect{
	
	private String firstName;
	private String lastName;
	private String addrLineOne;
	private String addrLineTwo;
	private String city;
	private String state;
	private String zip;
	private String phoneNum;
	private int CID;
	private boolean isActive;
	private connect cn = new connect();
	
	
	//create new customer AddTypeOne
	public customer(String fN, String lN, String pN, String addLn1, String c, String st, String z){
		cn.addCustomer(fN, lN, pN, addLn1, "", c, st, z);
		CID = cn.getID(pN);
		isActive = cn.getStat(pN);
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = "";
		city = c;
		state = st;
		zip=z;
		phoneNum = pN;
	}
	
	//create new customer AddTypeTwo
	public customer(String fN, String lN, String pN, String addLn1, String addLn2, String c, String st, String z){
		cn.addCustomer(fN, lN, pN, addLn1, addLn2, c, st, z);
		CID = cn.getID(pN);
		isActive = cn.getStat(pN);
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = addLn2;
		city = c;
		state = st;
		zip=z;
		phoneNum = pN;
	}
	
	public customer (int CID){
		ResultSet r = searchCustomer(CID, "", "");
		try{
			CID = r.getInt("CustomerID");
			isActive = r.getBoolean("Status");
			firstName = r.getString("FirstName");
			lastName = r.getString("LastName");
			addrLineOne = r.getString("Address");
			addrLineTwo = r.getString("AddressLineTwo");
			city = r.getString("City");
			state = r.getString("State");
			zip = r.getString("zip");
			phoneNum = r.getString("Phone");
		}
		catch(Exception e){
			CID = 0;
		}
	}
	
	public customer (String fN, String lN){
		
	}
}
