package connections;
import java.sql.ResultSet;

public class LatLng {
	public int CID;
	public double lat;
	public double lng;
	private double angle;
	private double distance;
	//private double userLat = ;
	private double userLong;
	
	public LatLng(double lat,double lng ){
		this.lat = lat;
		this.lng = lng;
		//angle = Math.toDegrees(Math.atan())
		CID=0;
	}
	
	public LatLng(){
		lat = 0.0;
		lng = 0.0;
		CID =0;
	}
	
	public LatLng(connect cn, int CID, String z, String addr, String s){
		cn.addLatLngToCustomer(CID, z, addr, s);	
	}
	
	public LatLng(connect cn, int CID){
		cn.addLatLngToCustomer(CID);
	}
	
	public LatLng(int CID, double lat, double lng){
		this.CID = CID;
		this.lat = lat;
		this.lng = lng;

	}
	
	public String toString(){
		return "[CustomerID: "+ CID+ " Latitude: " + lat + ", Longitude: " + lng + "]";
	}
	
	public int getCustomerID(){
		return CID;
	}
}