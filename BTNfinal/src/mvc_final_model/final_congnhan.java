package mvc_final_model;

public class final_congnhan {
	 private String manv;
	    private String name;
	    private String diachi;
	  

		

	   
	    public final_congnhan() {
			
		}

	    public String getManv() {
	        return manv;
	    }

	    public void setManv(String manv) {
	        this.manv = manv;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getDiachi() {
	        return diachi;
	    }

	    public void setDiachi(String diachi) {
	        this.diachi = diachi;
	    }

	    public final_congnhan(String manv, String name, String diachi) {
	        this.manv = manv;
	        this.name = name;
	        this.diachi = diachi;
	    }

	    @Override
	    public String toString() {
	        return "CongNhan2{" + "manv=" + manv + ", name=" + name + ", diachi=" + diachi + '}';
	    }
	    
}
