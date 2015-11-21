import java.sql.ResultSet;

public class user {
	private String name;
	private String pass;
	private String addr;
	private String city;
	private String state;
	private String zip;
	private String email;
	private String filePath;
	private connect cn;
	
	//add user data
	public user(connect con, String n, String p, String a, String c, String s, String z, String e, String fP){
		cn = con;
		name = n;
		pass = p;
		addr = a;
		city = c;
		state = s;
		zip = z;
		email = e;
		filePath = fP;
	}
	
	//get user data; sets name to null if no data exists
	public user(connect con){
		cn = con;
		ResultSet r = cn.userGetProfile();
		try{
			while(r.next()){
				name = r.getString("Name");
				pass = r.getString("Password");
				addr = r.getString("Address");
				city = r.getString("City");
				state = r.getString("State");
				zip = r.getString("Zip");
				email = r.getString("Email");
				filePath = r.getString("filePath");
			}
		}
		catch(Exception e){
			name = null;
		}
	}
	
	public String toString(){
		return "Username: " + name + "\nPassword: " + pass + "\nAddress: " + addr + "\n" + city + ", " + state + " " + zip + "\nE-mail: " + email + "\nSave Location: " + filePath;
	}
	
	public boolean modUserName(String n){
		name = n;
		return cn.userModData("Name", n);
	}
	
	public boolean modUserPassword(String p){
		pass = p;
		return cn.userModData("Password", p);
	}
	
	public boolean modUserAddress(String a, String c, String s, String z){
		addr = a;
		city = c;
		state = s;
		zip = z;
		return cn.userModData("Address", a)&&cn.userModData("City", c)&&cn.userModData("State", s)&&cn.userModData("Zip", z);
	}
	
	public boolean modUserEmail(String e){
		email = e;
		return cn.userModData("Email", e);
	}
	
	public boolean modUserFilePath(String fP){
		filePath = fP;
		return cn.userModData("FilePath", fP);
	}
	
	public String getUserName(){
		return name;
	}
	
	public String getPassword(){
		return pass;
	}
	
	public String getAddress(){
		return addr + "\n" + city + ", " + state + " " + zip;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getFilePath(){
		return filePath;
	}
}
