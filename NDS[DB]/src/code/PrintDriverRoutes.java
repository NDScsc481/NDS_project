//import java.io.*;
//import java.sql.ResultSet;
//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.nio.file.*;
//import java.util.LinkedList;
//
//
//public class PrintDriverRoutes {
//	private connect cn;
//	private user u;
//	private String filePath;
//	private String billMonth;
//	private NumberFormat fmt = new DecimalFormat("#0.00");
//	
//	public PrintDriverRoutes(connect con){
//		cn = con;
//		ResultSet r = cn.userGetfilePath();
//			try{
//				while(r.next()){
//					filePath = r.getString("FilePath");
//				}
//			}
//			catch(Exception e){
//				filePath = null;
//			}
//	}
//	public void printRoutes(int numDrivers, LinkedList<customer> sortedCustList ){
//		String invoiceNum;
//		double totalDue;
//		ResultSet rs;
//		try{
//			for(int i =0; i< sortedCustList.size(); i++){
//				//rs = cn.searchForCustomerInView(sortedCustList.get(i).CID,sortedCustList.get(i).PID);
//				while(rs.next()){
//					File f = new File(filePath, invoiceNum + ".txt");
//					BufferedWriter w = new BufferedWriter(new FileWriter(f));
//					w.write("Driver Delivery Route");
//					w.write("\r\nCustomer: " + rs.getString("FirstName")+ " " +rs.getString("LastName") 
//					+ "\r\nAddress:\r\t");
//					if(rs.getString("AddressLineTwo")!= null){
//						w.write(rs.getString("Address") + " " + rs.getString("AddressLineTwo") + " " + rs.getString("City")  + ",\r\n" 
//					+ rs.getString("State")+  " " + rs.getString("Zip")+"\r\n");
//					}else{
//						w.write(rs.getString("Address") + " " + rs.getString("City")  + ",\r\n" 
//								+ rs.getString("State")+  " " + rs.getString("Zip")+"\r\n");
//					}
//					w.write("Publications");
//					
//
//					}
//					w.write(rs.getString("Address") + "\r\t\r\t" +  + "\r\t\r\t" + fmt.format(tempp.getPrice()) + "\r\n");
//
//				}
//			}
//			ResultSet allActive = cn.getAllCustomers(),s;
//			while(allActive.next()){
//				customer tempc = new customer(cn, allActive.getInt("CustomerID"));
//				invoiceNum = DateTime.getDateNameFile() + "-" +String.valueOf(tempc.getCID());
//				File f = new File(filePath, invoiceNum + ".txt");
//				BufferedWriter w = new BufferedWriter(new FileWriter(f));
//				w.write("Driver Delivery Route");
//				w.write("\r\n\r\nCustomer: \r\n" + tempc. + "\r\n" + tempc.getAddress() + "\r\n\r\nPublication Information\r\t\r\tSubscription Period\r\t\r\tPrice\r\n");
//				totalDue = 0;
//				s = cn.getSubscriptions(tempc.getCID());
//				while(s.next()){
//					subscriptions temps = new subscriptions(cn, s.getInt("ItemID"));
//					publication tempp = temps.getPubInfo();
//					w.write(tempp.getBillTitle() + "\r\t\r\t" + temps.getPeriod() + "\r\t\r\t" + fmt.format(tempp.getPrice()) + "\r\n");
//					totalDue +=tempp.getPrice();
//				}
//				w.write("\r\n\r\n\r\n\tTOTAL DUE: " + fmt.format(totalDue));
//				w.close();
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	private String billHeader(){
//		u = new user(cn);
//		return u.getCompanyName() + "\r\n" + u.getAddress() + "\r\nPhone: " + u.getCSPhone() + "\r\nE-mail: " +u.getCSEmail() + "\r\n\r\nINVOICE\t\t#";
//	}
//}
//
//		
//	}
//
//
