package mvc_final_connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class connect_thongtinnopsanpham {
	 protected Connection conn=null;

	    public connect_thongtinnopsanpham(){
	        try{
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String url ="jdbc:sqlserver://ADMIN\\MSSQLSERVER02:1433;databaseName=luong;trustServerCertificate=true";
	            String user ="sa";
	            String pass = "1";
	            conn= DriverManager.getConnection(url, user, pass);
	            System.out.println("Kết nối thành công bảng lương!");
	        }catch(Exception ex){
	            ex.printStackTrace();
	        }
	    }
	    
	    

}
