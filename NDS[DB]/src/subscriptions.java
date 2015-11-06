import java.text.*;

public class subscriptions{
	private int CID;
	private int SID;
	private double totalPrice;
	NumberFormat fmatr = new DecimalFormat("#0.00"); 
	
	public subscriptions(connect cn, int CID){
		cn.addSubscritions(CID);
		SID = cn.getSubscriptionID(CID);
		totalPrice = 0;
	}
	
	public subscriptions(int ID, int SD, double tot){
		CID = ID;
		SID = SD;
		totalPrice = tot;
	}
	
	public double getTotal(){
		return totalPrice;
	}
	
	
}
