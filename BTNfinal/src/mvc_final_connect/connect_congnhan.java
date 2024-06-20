package mvc_final_connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class connect_congnhan {
	 protected Connection conn=null;
	 public connect_congnhan() {
	        try{
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String url ="jdbc:sqlserver://ADMIN\\MSSQLSERVER02:1433;databaseName=congnhan;trustServerCertificate=true";
	            String user ="sa";
	            String pass = "1";
	            conn= DriverManager.getConnection(url, user, pass);
	            System.out.println("Kết nối thành công!");
	        }catch(Exception ex){
	            ex.printStackTrace();
	        }
	    }
}
