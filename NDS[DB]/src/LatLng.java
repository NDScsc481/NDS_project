
public class LatLng {
	private int CID;
	public String lat;
	public String lng;
	
	
	public LatLng(String lat,String lng ){
		this.lat = lat;
		this.lng = lng;
	}
	public LatLng(){
		lat = "";
		lng = "";
	}
	public LatLng(connect cn, int CID){
		cn.addLatLngToCustomer(CID);
	}
	public LatLng(int CID){
		this.CID = CID;
		this.lat = "";
		this.lng = "";

	}

}