package mvc_final_model;

public class final_sanpham {
	 private String masp;
	    private String name;
	    private long gia;

	    

	    
	    public final_sanpham(String masp, String name, long gia) {
	        this.masp = masp;
	        this.name = name;
	        this.gia = gia;
	    }

	    public final_sanpham() {
			
		}

		public String getMasp() {
	        return masp;
	    }

	    public void setMasp(String masp) {
	        this.masp = masp;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public long getGia() {
	        return gia;
	    }

	    public void setGia(long gia) {
	        this.gia = gia;
	    }

	  

	    @Override
	    public String toString() {
	        return "Sản phẩm: mã sản phẩm: "+masp+" :"+" Tên sản phẩm: "+name+" : "+"giá sản phẩm: "+gia;
	}
}
