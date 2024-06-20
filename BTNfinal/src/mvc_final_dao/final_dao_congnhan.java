package mvc_final_dao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import mvc_final_connect.connect_congnhan;
import mvc_final_model.final_congnhan;



public class final_dao_congnhan extends connect_congnhan {
	  public Vector<final_congnhan> layDSTK(){
	        Vector<final_congnhan> veccongnhan = new Vector<>();
	        try{
	            String sql="Select * From tblcongnhan ";
	            Statement state = conn.createStatement();
	            ResultSet res = state.executeQuery(sql);
	            while(res.next()){
	                final_congnhan tk = new final_congnhan();
	                tk.setManv(res.getNString(1));;
	                tk.setName(res.getNString(2));
	                tk.setDiachi(res.getNString(3));
	                veccongnhan.add(tk);
	            }
	        }catch(Exception ex){
	            ex.printStackTrace();
	        }
	        return veccongnhan;
	    }


public void themcongnhan(final_congnhan sv) {
    try {
        String sql = "INSERT INTO tblcongnhan (macn, name, diachi) VALUES ('" + sv.getManv() + "', '" + sv.getName() + "', '" + sv.getDiachi() + "')";
        Statement state = conn.createStatement();
        state.executeUpdate(sql);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


public void xoacongnhan(String macn) {
    try {
        String sql = "DELETE FROM tblcongnhan WHERE macn = '" +macn+ "'";
        Statement state = conn.createStatement();
        state.executeUpdate(sql);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
public void capnhatcongnhan(final_congnhan sv, String maMoi, String tenMoi, String diaChiMoi) {
    try {
        // Xây dựng câu lệnh SQL trực tiếp với Statement
        String sql = "UPDATE tblcongnhan SET " +
                     "macn = '" + maMoi + "', " +
                     "name = '" + tenMoi + "', " +
                     "diachi = '" + diaChiMoi + "' " +
                     "WHERE macn = '" + sv.getManv() + "' " +
                     "AND name = '" + sv.getName() + "' " +
                     "AND diachi = '" + sv.getDiachi() + "'";

        // Tạo một đối tượng Statement để thực thi câu lệnh
        Statement stmt = conn.createStatement();

        // Thực thi câu lệnh và lấy số lượng bản ghi bị ảnh hưởng
        int rowsAffected = stmt.executeUpdate(sql);

        // Kiểm tra kết quả cập nhật
        if (rowsAffected > 0) {
            System.out.println("Cập nhật thông tin công nhân thành công.");
        } else {
            System.out.println("Không tìm thấy thông tin công nhân để cập nhật.");
        	
        }

        // Đóng đối tượng Statement
        stmt.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}



}