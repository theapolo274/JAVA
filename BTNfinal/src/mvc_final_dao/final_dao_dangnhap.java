package mvc_final_dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import mvc_final_model.final_congnhan;
import mvc_final_model.final_dangnhap;

import mvc_final_connect.connect_dangnhap;

public class final_dao_dangnhap extends connect_dangnhap {
	 public Vector<final_dangnhap> layDSTK(){
	        Vector<final_dangnhap> vecdangnhap = new Vector<>();
	        try{
	            String sql="Select * From tbldangnhap ";
	            Statement state = conn.createStatement();
	            ResultSet res = state.executeQuery(sql);
	            while(res.next()){
	                final_dangnhap tk = new final_dangnhap();
	                tk.setMatkhau(res.getNString(1));;
	                tk.setTaikhoan(res.getNString(2));
	             
	                vecdangnhap.add(tk);
	            }
	        }catch(Exception ex){
	            ex.printStackTrace();
	        }
	        return vecdangnhap;
	    }
	 
	 
	 public void themtaikhoan(final_dangnhap sv) {
		    try {
		        String sql = "INSERT INTO tbldangnhap (taikhoan, matkhau) VALUES ('" + sv.getTaikhoan() + "', '" + sv.getMatkhau() + "')";
		        Statement state = conn.createStatement();
		        state.executeUpdate(sql);
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
	 
	 
	   public boolean kiemTraTaiKhoanMatKhau(String taikhoan, String matkhau) {
	        boolean exists = false;
	        try {
	            String sql = "SELECT * FROM tbldangnhap WHERE taikhoan = '" + taikhoan + "' AND matkhau = '" + matkhau + "'";
	            Statement state = conn.createStatement();
	            ResultSet res = state.executeQuery(sql);
	            if (res.next()) {
	                exists = true;
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return exists;
	    }
	 
	 
}
