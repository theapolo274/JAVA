package mvc_final_dao;
import mvc_final_view.final_thongtingiaodien;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import mvc_final_connect.connect_thongtinnopsanpham;
import mvc_final_model.final_congnhan;
import mvc_final_model.final_sanpham;
import mvc_final_model.final_thongtinnopsanpham;

public class final_dao_thongtinsanpham extends connect_thongtinnopsanpham {

	public Vector<final_thongtinnopsanpham> layDSTK() {
	    Vector<final_thongtinnopsanpham> vecthongtin = new Vector<>();
	    try {
	        String sql = "SELECT * FROM tblluong";
	        Statement state = conn.createStatement();
	        ResultSet res = state.executeQuery(sql);
	        while (res.next()) {
	            final_congnhan cn = new final_congnhan();
	            final_sanpham sp = new final_sanpham();
	            cn.setManv(res.getNString(1));
	            cn.setName(res.getNString(2));
	            sp.setMasp(res.getNString(3));
	            sp.setName(res.getNString(4));
	            long soluong = res.getLong(5); 
	            long luong = res.getLong(6);
	            
	            final_thongtinnopsanpham tk = new final_thongtinnopsanpham(cn, sp, soluong);
	            tk.setLuong(luong);
	            
	            vecthongtin.add(tk);
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return vecthongtin;
	}


	 
	  
	  public void addThongTinSanPham(final_thongtinnopsanpham ttsp) {
		    try {
		        String sql = "INSERT INTO tblluong (macn, namecn, masp, namesp, soluong, luong) VALUES ('" +
		                     ttsp.getCongnhan().getManv() + "', '" +
		                     ttsp.getCongnhan().getName() + "', '" +
		                     ttsp.getSanpham().getMasp() + "', '" +
		                     ttsp.getSanpham().getName() + "', " +
		                     ttsp.getSoluong() + ", " +
		                     ttsp.tinhluong() + ")";
		        Statement state = conn.createStatement();
		        state.executeUpdate(sql);
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
	  

	    public void xoaThongTinSanPham(String maCN, String maSP,long soluong , long luong) {
	        try {
	        	   String sql = "DELETE FROM tblluong WHERE macn = '" + maCN + "' AND masp = '" + maSP + "' AND soluong = '" + soluong + "' AND luong = '" + luong + "'";
	            Statement state = conn.createStatement();
	            int rowsAffected = state.executeUpdate(sql);
	            if (rowsAffected > 0) {
	                System.out.println("Xóa thông tin sản phẩm thành công.");
	               
	            } else {
	                System.out.println("Không tìm thấy thông tin sản phẩm để xóa.");
	                System.out.println(maCN +" "+ maSP);
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    

	    public void capnhatthongtin(final_thongtinnopsanpham sv, String macnmoi, String maspmoi, long soluongmoi) {
	        try {
	            // Tạo câu truy vấn SQL để cập nhật thông tin sản phẩm
	            String sql = "UPDATE tblluong SET " +
	                         "macn = '" + macnmoi + "', " +
	                         "masp = '" + maspmoi + "', " +
	                         "soluong = " + soluongmoi + ", " +
	                         "luong = " + sv.tinhluong() + " " +
	                         "WHERE macn = '" + sv.getCongnhan().getManv() + "' " +
	                         "AND masp = '" + sv.getSanpham().getMasp() + "' " +
	                         "AND soluong = " + sv.getSoluong() + " " +
	                         "AND luong = " + sv.getLuong();
	            
	            // Sử dụng Statement để thực thi câu truy vấn
	            Statement state = conn.createStatement();
	            int rowsAffected = state.executeUpdate(sql);
	            
	            // Kiểm tra kết quả cập nhật
	            if (rowsAffected > 0) {
	                System.out.println("Cập nhật thông tin sản phẩm thành công.");
	            } else {
	                System.out.println("Không tìm thấy thông tin sản phẩm để cập nhật.");
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }


}
