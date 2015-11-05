

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
	
	public customer(String fN, String lN, String pN, String addrLineOne, String c, String st, String z){
		firstName = fN;
		lastName = lN;
		phoneNum = pN;
	}
}
