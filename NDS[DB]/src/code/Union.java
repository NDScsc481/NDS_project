import java.sql.ResultSet;

public class Union extends customer {
	

		public LatLng points;
		public String pubName;
		
		connect cn = new connect();

		// create new customer AddTypeOne
		public Union(int cID, int pID, String fN, String lN, String addLn1, String c, String st, String z,boolean encap) {
			super(cID, pID, fN, lN, addLn1, c, st, z, encap);

		
			ResultSet r = cn.searchForCustomerInView(cID,pID);
			try {
				while (r.next()) {
					publicationName = r.getString("PublicationName");
				}
			} catch (Exception e) {

			}
			
		}
		private void setPubName(String name){
			pubName = name;
		}
		public String getPubName(){
			return pubName;
		}
		private void setPoints(){
			
		}
	
	

}
