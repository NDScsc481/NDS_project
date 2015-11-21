import java.io.*;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.nio.file.*;

public class print {
	private connect cn;
	private user u;
	private String filePath;
	private String billMonth;
	private NumberFormat fmt = new DecimalFormat("#0.00"); 
	//prints bills: each active customer, p end date, list of items, total
	//daily summary: 
	//daily route: list customer with address,
	public print(connect con){
		cn = con;
		ResultSet r = cn.userGetfilePath();
		try{
			while(r.next()){
				filePath = r.getString("FilePath");
			}
		}
		catch(Exception e){
			filePath = null;
		}
	}
	
	//invoice number in name of text file
	public void printAllBills(){
		billMonth = DateTime.getLastMonth();
		ResultSet r = cn.getAll(), s;
		String invoiceNum;
		double totalDue;
		try{
			while(r.next()){
				customer tempc = new customer(cn, r.getInt("CustomerID"));
				s = cn.getSubscriptions(tempc.getCID());
				invoiceNum = DateTime.getDateNameFile()+String.valueOf(tempc.getCID());
				File f = new File(filePath, invoiceNum + ".txt");
				BufferedWriter w = new BufferedWriter(new FileWriter(f));
				w.write(billHeader()+invoiceNum);
				w.write("\r\n\r\nBill/Ship To: \r\n" + tempc.getFullName() + "\r\n" + tempc.getAddress() + "\r\n\r\nPublication Information\t\t\tSubscription Period\t\t\tPrice\r\n");
				totalDue = 0;
//				while(s.next()){
//					subscriptions temps = new subscriptions(cn, s.getInt("SubscriptionID"));
//					publication tempp = temps.getPubInfo();
//					w.write(tempp.getBillTitle() + "\t\t\t" + temps.getPeriod() + "\t\t\t" + fmt.format(tempp.getPrice()) + "\r\n");
//					totalDue +=tempp.getPrice();
//				}
				w.write("\r\n\r\n\r\n\tTOTAL DUE: " + fmt.format(totalDue));
				w.close();
			}
		}
		catch(Exception e){
			
		}
	}
	
	public void tester(){
		File f = new File(filePath, "1234.txt");
		try{
			BufferedWriter w = new BufferedWriter(new FileWriter(f));
			w.write(billHeader());
			w.close();
		}
		catch(Exception e){
			System.out.println("FAIL");
		}
	}
	
	private String billHeader(){
		u = new user(cn);
		return u.getCompanyName() + "\r\n" + u.getAddress() + "\r\nPhone: " + u.getCSPhone() + "\r\nE-mail: " +u.getCSEmail() + "\r\n\r\nINVOICE\t\t#";
	}
}
