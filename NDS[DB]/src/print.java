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
				s = cn.getSubscriptions(r.getInt("CustomerID"));
				invoiceNum = DateTime.getDateNameFile()+tempc.getCID()+".txt";
				File f = new File(filePath, invoiceNum);
				BufferedWriter w = new BufferedWriter(new FileWriter(f));
				w.write(billHeader()+invoiceNum);
				w.write("\n\nBill/Ship To: \n" + tempc.getFullName() + "\n" + tempc.getAddress() + "\n\nPublication Information\t\t\tSubscription Period\t\t\tPrice\n");
				totalDue = 0;
				while(s.next()){
					subscriptions temps = new subscriptions(cn, s.getInt("SubscriptionID"));
					publication tempp = temps.getPubInfo();
					w.write(tempp.getBillTitle() + "\t\t\t" + temps.getPeriod() + "\t\t\t" + fmt.format(tempp.getPrice()) + "\n");
					totalDue +=tempp.getPrice();
				}
				w.write("\n\n\n\tTOTAL DUE: " + fmt.format(totalDue));
				w.close();
			}
		}
		catch(Exception e){
			
		}
	}
	
	private String billHeader(){
		u = new user(cn);
		return u.getCompanyName() + "\n" + u.getAddress() + "\nPhone: " + u.getCSPhone() + "\nE-mail: " +u.getCSEmail() + "\n\nINVOICE\t\t#";
	}
}
