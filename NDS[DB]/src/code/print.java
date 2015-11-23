

import java.io.*;
import java.sql.ResultSet;
import java.nio.file.*;

public class print {
	private connect cn;
	private user u;
	private String filePath;
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
	}
	
	//invoice number in name of text file
	public void printAllBills(){
		billPeriod = DateTime.getBillPeriod();
		String invoiceNum;
		double totalDue;
		try{
			ResultSet allSubs = cn.getBillInfo();
			allSubs.next();
			while(!allSubs.isAfterLast()){
				customer tempc = new customer
						(allSubs.getInt("CustomerID"), 
						allSubs.getString("FirstName"), 
						allSubs.getString("LastName"), 
						allSubs.getString("Address"), 
						allSubs.getString("AddressLineTwo"), 
						allSubs.getString("City"), 
						allSubs.getString("State"), 
						allSubs.getString("Zip"));
				invoiceNum = today + "-" +tempc.getCID();
				File f = new File(filePath, invoiceNum + ".txt");
				BufferedWriter w = new BufferedWriter(new FileWriter(f));
				w.write(String.format("%s%42s%20s%n%35c%s%n%n",
						header, "INVOICE", "#" + invoiceNum,
						' ', "BILL PERIOD: " + billPeriod));
				w.write(String.format("%s%n%s%n%s%n%n%-35s%-30s%s%n",
						"BILL/SHIP TO:",
						tempc.getFullName(),
						tempc.getAddress(),
						"PUBLICATION INFORMATION", "SUBSCRIPTION PERIOD", "PRICE"));
				totalDue = 0;
				do{
				w.write(String.format("%-35s%-30s%.2f%n" ,
						allSubs.getString("PublicationTitle"),
						allSubs.getString("StartDate") + " to " + allSubs.getString("EndDate"),
						allSubs.getDouble("Price")));
				totalDue +=allSubs.getDouble("Price");
				}while(allSubs.next()&&allSubs.getInt("CustomerID")==tempc.getCID());
				w.write(String.format("%n%-15s $%.2f", "TOTAL DUE:", totalDue));
				w.close();
			}
			allSubs.close();
			System.out.println("Success");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}