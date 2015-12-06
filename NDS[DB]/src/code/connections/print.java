package connections;
import java.io.*;
import java.sql.ResultSet;
import java.nio.file.*;

public class print {
	private connect cn;
	private user u;
	private String filePath;
	private String billFilePath;
	private String billPeriod;
	private String header;
	private String today;
	//daily summary: 
	//daily route: list customer with address,
	public print(connect con){
		cn = con;
		u = new user(cn);
		header = String.format("%s%n%s%n%s%n%s%n%n",
				u.getCompanyName(), u.getAddress(),
				"Phone: " + u.getCSPhone(), "E-mail: " +u.getCSEmail());
		today = DateTime.getDateNameFile();
		filePath = u.getFilePath()+"/"+DateTime.getDayFilePath();
		if(!Files.exists(Paths.get(filePath))){
			try{
				Files.createDirectories(Paths.get(filePath));
			}
			catch(Exception e){
				filePath = u.getFilePath();
			}
		}
		billFilePath = filePath + "/bills";
	}
	
	//invoice number in name of text file
	public void printAllBills(){
		if(!Files.exists(Paths.get(billFilePath))){
			try{
				Files.createDirectories(Paths.get(billFilePath));
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		billPeriod = DateTime.getBillPeriod();
		String invoiceNum;
		int tempID;
		double totalDue;
		try{
			ResultSet allSubs = cn.getBillInfo();
			allSubs.next();
			while(!allSubs.isAfterLast()){
				tempID = allSubs.getInt("CustomerID");
				invoiceNum = today + "-" +tempID;
				File f = new File(billFilePath, invoiceNum + ".txt");
				BufferedWriter w = new BufferedWriter(new FileWriter(f));
				w.write(String.format("%s%42s%20s%n%35c%s%n%n",
						header, "INVOICE", "#" + invoiceNum,
						' ', "BILL PERIOD: " + billPeriod));
				w.write(String.format("%s%n%s %s%n%s%n%n%-35s%-30s%s%n",
						"BILL/SHIP TO:",
						allSubs.getString("FirstName"), 
						allSubs.getString("LastName"),
						customer.getAddress(allSubs.getString("Address"), 
								allSubs.getString("AddressLineTwo"), 
								allSubs.getString("City"), 
								allSubs.getString("State"), 
								allSubs.getString("Zip")),
						"PUBLICATION INFORMATION", "SUBSCRIPTION PERIOD", "PRICE"));
				totalDue = 0;
				do{
				w.write(String.format("%-35s%-30s%.2f%n" ,
						allSubs.getString("PublicationTitle"),
						allSubs.getString("StartDate") + " to " + allSubs.getString("EndDate"),
						allSubs.getDouble("Price")));
				totalDue +=allSubs.getDouble("Price");
				}while(allSubs.next()&&allSubs.getInt("CustomerID")==tempID);
				w.write(String.format("%n%n%-15s%.2f%38s %s", "TOTAL DUE:", totalDue, "Date Printed:", today));
				w.close();
			}
			allSubs.close();
			System.out.println("Success");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//unfinished
	public void printSummary(){
		try{
			System.out.println(customer.getAddress("A", "", "c", "s", "z"));
			File f = new File(filePath, "Summary.txt");
			BufferedWriter w = new BufferedWriter(new FileWriter(f));
			w.write(String.format("%s%42s %s%n%n%nPUBLICATIONS TOTALS%n%n%-35sCOUNT%n", header, "SUMMARY", today, "TITLE"));
			ResultSet pubList = cn.getSummaryPublicationInfo(DateTime.getWeekday(), DateTime.getMonthday());
			int total = 0;
			while(pubList.next()){
				total+=pubList.getInt("Count");
				w.write(String.format("%-35s%s%n", pubList.getString("PublicationTitle"), pubList.getInt("Count")));
			}
			pubList.close();
			w.write(String.format("%n%-20s%d", "PUBLICATIONS DUE:", total));
			w.close();
			System.out.println("Success");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}