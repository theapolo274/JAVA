package mvc_final_model;

import mvc_final_model.final_sanpham;
import mvc_final_model.final_congnhan;

public class final_thongtinnopsanpham {
	  private final_congnhan congnhan;
	    private final_sanpham sanpham;
	    private  long soluong;
	    public long luong;
	

	  

	    public final_thongtinnopsanpham(final_congnhan congnhan, final_sanpham sanpham, long soluong) {
	    	  this.congnhan = congnhan;
	          this.sanpham = sanpham;
	          this.soluong = soluong;
	         
	    }
	    
		

		public final_thongtinnopsanpham() {
		
		}

		public final_congnhan getCongnhan() {
			return congnhan;
		}
		public void setCongnhan(final_congnhan congnhan) {
			this.congnhan = congnhan;
		}
		public final_sanpham getSanpham() {
			return sanpham;
		}
		public void setSanpham(final_sanpham sanpham) {
			this.sanpham = sanpham;
		}
		public long getSoluong() {
			return soluong;
		}
		public void setSoluong(long luong) {
			this.soluong = luong;
		}
		
		

		    public long tinhluong() {
		        return luong = soluong * sanpham.getGia();
		    }
		
		   

			 public long getLuong() {
			        return luong;
			    }

			    public void setLuong(long luong) {
			        this.luong = luong;
			    }
		
		@Override
		public String toString() {
			return "final_thongtinnopsanpham [congnhan=" + congnhan + ", sanpham=" + sanpham + ", soluong=" + luong
					+ "]";
		}

	   
}
