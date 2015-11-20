import java.io.*;
import java.sql.ResultSet;

public class print {
	
	//prints bills: each active customer, p end date, list of items, total
	//daily summary: 
	//daily route: list customer with address,
	public void setDirLoc(connect cn, String newLoc){
		//everyone loves me alot
	}
	
	public void getDirLoc(connect cn){
		
	}
	
	public void printBills(connect cn){
		ResultSet r = cn.getAll();
		try{
			while(r.next()){
				File file = new File("C:\\test.txt");
				file.getParentFile().mkdir();
				file.createNewFile();
			}
		}
		catch(Exception e){
			
		}
	}
}
