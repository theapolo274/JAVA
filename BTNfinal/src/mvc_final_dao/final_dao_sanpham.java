package mvc_final_dao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


import mvc_final_connect.connect_sanpham;
import mvc_final_model.final_congnhan;
import mvc_final_model.final_sanpham;




public class final_dao_sanpham extends connect_sanpham {
	  public Vector<final_sanpham> layDSTK(){
	        Vector<final_sanpham> vecsanpham = new Vector<>();
	        try{
	            String sql="Select * From tblsanpham ";
	            Statement state = conn.createStatement();
	            ResultSet res = state.executeQuery(sql);
	            while(res.next()){
	                final_sanpham tk = new final_sanpham();
	                tk.setMasp(res.getNString(1));;
	                tk.setName(res.getNString(2));
	                tk.setGia(res.getInt(3));
	                vecsanpham.add(tk);
	            }
	        }catch(Exception ex){
	            ex.printStackTrace();
	        }
	        return vecsanpham;
	  }
	  
	  
	  

public void themsanpham(final_sanpham sv) {
    try {
        String sql = "INSERT INTO tblsanpham (masp, name, gia) VALUES ('" + sv.getMasp() + "', '" + sv.getName() + "', '" + sv.getGia() + "')";
        Statement state = conn.createStatement();
        state.executeUpdate(sql);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


public void xoasanpham(String macn) {
    try {
        String sql = "DELETE FROM tblsanpham WHERE masp = '" +macn+ "'";
        Statement state = conn.createStatement();
        state.executeUpdate(sql);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

public void capnhatsanpham(final_sanpham sv, String maMoi, String tenMoi, Long soluongMoi) {
    try {
        // Xây dựng câu lệnh SQL trực tiếp với Statement
        String sql = "UPDATE tblsanpham SET " +
                     "masp = '" + maMoi + "', " +
                     "name = '" + tenMoi + "', " +
                     "gia = " + soluongMoi + " " +
                     "WHERE masp = '" + sv.getMasp() + "' " +
                     "AND name = '" + sv.getName() + "' " +
                     "AND gia = " + sv.getGia();

        // Tạo một đối tượng Statement để thực thi câu lệnh
        Statement stmt = conn.createStatement();

        // Thực thi câu lệnh và lấy số lượng bản ghi bị ảnh hưởng
        int rowsAffected = stmt.executeUpdate(sql);

        // Kiểm tra kết quả cập nhật
        if (rowsAffected > 0) {
            System.out.println("Cập nhật thông tin sản phẩm thành công.");
        } else {
            System.out.println("Không tìm thấy thông tin sản phẩm để cập nhật.");
        }

        // Đóng đối tượng Statement
        stmt.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

	  
	  
	  
	  
	  
	  
	  
	  
}


