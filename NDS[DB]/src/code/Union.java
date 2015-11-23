import java.sql.ResultSet;

public class Union{
	private String firstName;
	private String lastName;
	private String addrLineOne;
	private String addrLineTwo;
	private String city;
	private String state;
	private String zip;
	protected int CID;
	protected int PID;
	protected String publicationName;
	private LatLng myPoints;
	private connect cn = new connect();

		public LatLng points;
		public String pubName;
		
		
		// create new customer AddTypeOne
		public Union(connect con, int cID, int pID, String fN, String lN, String addLn1, String c, String st, String z) {
			cn = con;
			CID = cID;
			PID = pID;
			firstName = fN;
			lastName = lN;
			addrLineOne= addLn1;
			city= c;
			state = st;
			zip=z;
			

		
			ResultSet r = cn.searchForCustomerInView(cID,pID);
			try {
				while (r.next()) {
					pubName = r.getString("PublicationName");
					setPubName(pubName);
					double lat = r.getDouble("Latitude");
					double lng = r.getDouble("Longitude");
					setPoints(lat,lng);
				}
			} catch (Exception e) {

			}
			
		}
		public Union(connect con, int cID, int pID, String fN, String lN, String addLn1, String addLn2, String c, String st, String z) {
			cn = con;
			CID = cID;
			PID = pID;
			firstName = fN;
			lastName = lN;
			addrLineOne= addLn1;
			addrLineTwo = addLn2;
			city= c;
			state = st;
			zip=z;
			

			ResultSet r = cn.searchForCustomerInView(cID,pID);
			try {
				while (r.next()) {
					pubName = r.getString("PublicationName");
					setPubName(pubName);
					double lat = r.getDouble("Latitude");
					double lng = r.getDouble("Longitude");
					setPoints(lat,lng);
				}
			} catch (Exception e) {

			}
			
			
		}
		public Union(connect con, int cID, int pID){
		cn = con;
		ResultSet r = cn.searchForCustomerInView(cID, pID);
		try {
			while (r.next()) {
				CID = r.getInt("CustomerID");
				firstName = r.getString("FirstName");
				lastName = r.getString("LastName");
				addrLineOne = r.getString("Address");
				if ((addrLineTwo = r.getString("AddressLineTwo")).length() == 0)
					addrLineTwo = null;
				city = r.getString("City");
				state = r.getString("State");
				zip = r.getString("Zip");
				pubName = r.getString("PublicationName");
				setPubName(pubName);
				double lat = r.getDouble("Latitude");
				double lng = r.getDouble("Longitude");
				setPoints(lat,lng);
				}
			}catch (Exception e) {
			}
			
	}

		private void setPubName(String name){
			pubName = name;
		}
		public String getPubName(){
			return pubName;
		}
		private void setPoints(double latitude, double longitude){
			points = new LatLng(latitude, longitude);
			
		}
		public LatLng getPoints(){
			return points;
		}
		 public String toString(){
		if ( addrLineTwo != null) {
				return "Customer ID: " + CID + " Name: " + firstName + " " + lastName + "\nAddress: " + addrLineOne + " " + addrLineTwo + " "
						+ city + ", " + state + " " + zip + "\n Publication ID: " + PID + " Publication Name: " + publicationName;
						
			} else {
				return  "Customer ID: " + CID + " Name: " + firstName + " " + lastName + "\nAddress: " + addrLineOne  + " "
						+ city + ", " + state + " " + zip + "\n Publication ID: " + PID + " Publication Name: " + publicationName;
			}
		}
		  public String getFullName(){
		        return firstName + " " + lastName;
		    }
		    
		    public String getAddress(){
		        if(addrLineTwo!=null){
		            return addrLineOne + "\r\n" + addrLineTwo + "\r\n" + city + ", " + state + " " + zip;
		        }
		        else{
		            return addrLineOne + "\r\n" + city + ", " + state + " " + zip;
		        }
		    }
		    
		    public int getCID(){
		        return CID;
		    }
	
	

}
