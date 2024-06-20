package mvc_final_view;

import mvc_final_view.final_sanphamgiaodien;
import mvc_final_view.final_congnhangiaodien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import mvc_final_dao.final_dao_sanpham;
import mvc_final_dao.final_dao_thongtinsanpham;
import mvc_final_model.final_congnhan;
import mvc_final_model.final_sanpham;
import mvc_final_model.final_thongtinnopsanpham;
import mvc_final_view.final_congnhangiaodien;
import mvc_final_view.final_sanphamgiaodien;
/**
 *
 * @author Admin
 */
public class final_thongtingiaodien extends JDialog{
    JTable tblSinhVien;
    DefaultTableModel dtmSinhVien;
    JTextField   txtCN, txtSP, txtSL,txttimkiemcongnhan,txtnhamcn,txttimkiemsanpham;
    JButton btnThem, btnXoa, btnSua,btntimkiemcongnhan,btntimkiemsanpham,btnincongnhan,btninsanpham,btninthong;
    JMenuBar menuBar;
    JMenu menucongnhan, menusanpham, menuthongtinsanpham;
    private  Vector<final_thongtinnopsanpham> listTKthongtin;
    ArrayList<final_thongtinnopsanpham>danhsachnopthongtinsanpham = new ArrayList<>();
    ArrayList<final_sanpham>danhsachsanpham= new ArrayList<>();
    ArrayList<final_congnhan>danhsachcongnhan= new ArrayList<>();
    
      public final_thongtingiaodien(String title) throws HeadlessException {
        addControl2();
        addEvent2();
        setSize(800, 600);
        
    }
     
    
      
      public void addControl2(){
    	  
    	  
    	  System.out.println("Danh sách công nhân:");
    	  for (final_congnhan congnhan : danhsachcongnhan) {
    	      System.out.println(congnhan.toString()); // Sử dụng phương thức toString() của final_congnhan
    	  }

    	  // In ra danh sách sản phẩm
    	  System.out.println("Danh sách sản phẩm:");
    	  for (final_sanpham sanpham : danhsachsanpham) {
    	      System.out.println(sanpham.toString()); // Sử dụng phương thức toString() của final_sanpham
    	  }
    	  
    	  
           Container con = getContentPane();
        JPanel pnLeft = new JPanel();
        pnLeft.setLayout(new BorderLayout());
        pnLeft.setPreferredSize(new Dimension(500, 0));
        JPanel pnRight = new JPanel();
        pnRight.setLayout(new BorderLayout());
        JSplitPane spMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnRight);
        spMain.setOneTouchExpandable(true);
        //Border boderthongtin = BorderFactory.createLineBorder(Color.blue);
        //spMain.setBorder(boderthongtin);
        Font rbItalic = new Font("Times New Roman", Font.CENTER_BASELINE, 20);
        Border blueBorder = new LineBorder(Color.BLACK, 2);

        
        // hai phần trái và phải
        JPanel pnRightofTop = new JPanel();
        JPanel pnRightofBotton = new JPanel();
        JSplitPane spRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnRightofTop, pnRightofBotton);
        pnRightofTop.setPreferredSize(new Dimension(0, 350));
        pnRight.add(spRight, BorderLayout.CENTER);
        con.add(spMain);
        
        JPanel pnLefttoTop = new JPanel();
        JPanel pnLefttoBottom = new JPanel();
        JSplitPane spLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnLefttoTop, pnLefttoBottom);
        pnLefttoTop.setPreferredSize(new Dimension(0, 350));
        pnLeft.add(spLeft,BorderLayout.CENTER);
       
        // phần menu
        menuBar = new JMenuBar();
        menucongnhan = new JMenu("Công Nhân ");
        menusanpham = new JMenu("Sản Phẩm"); 
        menuthongtinsanpham = new JMenu("Thông Tin Nộp Sản Phẩm");
        menuBar.add(menucongnhan);
        menuBar.add(menusanpham);
        menuBar.add(menuthongtinsanpham);
       
          setJMenuBar(menuBar); 
        
        //Tạo JTable
        
        dtmSinhVien = new DefaultTableModel();
        dtmSinhVien.addColumn("Mã CN");
        dtmSinhVien.addColumn("Tên CN");
        dtmSinhVien.addColumn("Mã SP");
        dtmSinhVien.addColumn("Tên SP");
        dtmSinhVien.addColumn("Số Lượng");
        dtmSinhVien.addColumn("Lương");
       
        final_dao_thongtinsanpham tk = new final_dao_thongtinsanpham();
        listTKthongtin = tk.layDSTK();
        for (final_thongtinnopsanpham sv : listTKthongtin) {
            Vector<Object> rowData = new Vector<>();  // Tạo vectơ hàng mới
            rowData.add(sv.getCongnhan().getManv());
            rowData.add(sv.getCongnhan().getName());
            rowData.add(sv.getSanpham().getMasp());
            rowData.add(sv.getSanpham().getName());
            rowData.add(sv.getSoluong());
            rowData.add(sv.getLuong());
            danhsachnopthongtinsanpham.add(sv);
            dtmSinhVien.addRow(rowData);  // Thêm dữ liệu hàng vào mô hình bảng
        }
       
        
       
      
        tblSinhVien = new JTable(dtmSinhVien);
       
        // phần cuộn chuột của bảng
        JScrollPane scSinhVien = new JScrollPane(tblSinhVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnLefttoTop.setLayout(new BorderLayout());
        pnLefttoTop.add(scSinhVien, BorderLayout.CENTER);
        JPanel pnTieuDeDS = new JPanel();
        JLabel lblTDDS = new JLabel("DANH SÁCH THÔNG TIN LƯƠNG");
        lblTDDS.setFont(rbItalic);
        lblTDDS.setForeground(Color.DARK_GRAY);
        pnTieuDeDS.add(lblTDDS);
        pnLefttoTop.add(pnTieuDeDS, BorderLayout.NORTH);
        // phần top bên phải
        pnRightofTop.setLayout(new BoxLayout(pnRightofTop, BoxLayout.Y_AXIS));
       
        JPanel pnTTTT = new JPanel();
        JLabel lblTT = new JLabel("Thông tin nộp sản phẩm");
        lblTT.setFont(rbItalic);
        lblTT.setForeground(Color.DARK_GRAY); 
        pnTTTT.add(lblTT);
        pnRightofTop.add(pnTTTT);
        JPanel pnMa = new JPanel();
        JLabel lblMa = new JLabel("Mã CN");
        txtCN = new JTextField(15);
        pnMa.add(lblMa);
        pnMa.add(txtCN);
        pnRightofTop.add(pnMa);
        JPanel pnTen = new JPanel();
        JLabel lblTen = new JLabel("Mã CN");
        txtSP = new JTextField(15);
        pnTen.add(lblTen);
        pnTen.add(txtSP);
        pnRightofTop.add(pnTen);
        JPanel pnDC = new JPanel();
        JLabel lblDC = new JLabel("SL");
        txtSL = new JTextField(15);
        pnDC.add(lblDC);
        pnDC.add(txtSL);
        pnRightofTop.add(pnDC);
        JPanel pnXuLy = new JPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        pnXuLy.add(btnThem);
        pnXuLy.add(btnSua);
        pnXuLy.add(btnXoa);
        JPanel pndong5 = new JPanel();
        btnincongnhan = new JButton("In CN");
       
        btninsanpham = new JButton("In SP");
        btninthong = new JButton("in Thông tin");
        pndong5.add(btnincongnhan);
        pndong5.add(btninsanpham);
        pndong5.add(btninthong);
        pnRightofTop.add(pnXuLy);
        pnRightofTop.add(pndong5);
        
        Dimension buttonSize = new Dimension(70, 30);
        
        btnThem.setBackground(Color.LIGHT_GRAY);
        btnThem.setPreferredSize(buttonSize);
         btnXoa.setBackground(Color.LIGHT_GRAY);
        btnXoa.setPreferredSize(buttonSize);
         btnSua.setBackground(Color.LIGHT_GRAY);
        btnSua.setPreferredSize(buttonSize);
        btnincongnhan.setBackground(Color.LIGHT_GRAY);
        btnincongnhan.setPreferredSize(buttonSize);
        btninsanpham.setBackground(Color.LIGHT_GRAY);
        btninsanpham.setPreferredSize(buttonSize);
        btninthong.setBackground(Color.LIGHT_GRAY);
        btninthong.setPreferredSize(buttonSize);
        
        

    
        // phần dưới bên phải 
        pnLefttoTop.setBorder(blueBorder);
        pnLefttoBottom.setBorder(blueBorder);
        pnRightofTop.setBorder(blueBorder);
        pnRightofBotton.setBorder(blueBorder);
        
        
pnRightofBotton.setLayout(new BoxLayout(pnRightofBotton, BoxLayout.Y_AXIS));
JPanel pnTTTTbotton = new JPanel();
JLabel lblTTbotton = new JLabel("Tìm kiếm thông tin nộp ");
lblTTbotton.setForeground(Color.DARK_GRAY); 

lblTTbotton.setFont(rbItalic);
pnTTTTbotton.add(lblTTbotton);
pnRightofBotton.add(pnTTTTbotton);
JPanel pnMabotton = new JPanel();
JLabel lblMabotton = new JLabel("Mã");
txtnhamcn = new JTextField(15);
txtnhamcn.setPreferredSize(new Dimension(50, 40));
txtnhamcn.setBorder(blueBorder);
pnMabotton.add(lblMabotton);
pnMabotton.setBorder(new EmptyBorder(10, 0,0, 0));
pnMabotton.add(txtnhamcn);
pnRightofBotton.add(pnMabotton);

JPanel pnXuLybotton = new JPanel();
btntimkiemcongnhan = new JButton("Tìm Kiếm");
Dimension buttonSize1 = new Dimension(90, 30);
  btntimkiemcongnhan.setBackground(Color.LIGHT_GRAY);
        btntimkiemcongnhan.setPreferredSize(buttonSize1);
pnXuLybotton.add(btntimkiemcongnhan);
pnRightofBotton.add(pnXuLybotton);

// phần trái dưới 
pnLefttoBottom.setLayout(new BoxLayout(pnLefttoBottom, BoxLayout.Y_AXIS));
JPanel first = new JPanel();
JLabel lblfirst = new JLabel("Thông tin nộp sản phẩm tìm kiếm ");
lblfirst.setFont(rbItalic);
lblfirst.setForeground(Color.DARK_GRAY);
first.add(lblfirst);
JPanel second = new JPanel();
txttimkiemcongnhan = new JTextField(32);
txttimkiemcongnhan.setPreferredSize(new Dimension(150, 40));
txttimkiemcongnhan.setBorder(blueBorder);
txtCN.setBorder(blueBorder);
txtSP.setBorder(blueBorder);
txtSL.setBorder(BorderFactory.createLineBorder(Color.RED));
second.add(txttimkiemcongnhan);
 second.setBorder(new EmptyBorder(0, 0,30, 0));
pnLefttoBottom.add(first);
pnLefttoBottom.add(second);
          
    // hàm tinh lương
  
        
    
      }
      public long tinhluong(int soluong , int gia){
                long luong  = soluong*gia;
          return luong;
      }
      
      
      //kiem tra cong nhan
      public boolean kiemtracongnhan(String macn) {
    	  boolean check = false;
    	  for(final_congnhan cn : danhsachcongnhan) {
    		  if(cn.getManv().equals(macn)) {
    			 check = true;
    			 return check;
    		  }
    	  }
    	  return check;
      }
      
      //kiem tra san pham
      
      public boolean kiemtrasanpham(String masp) {
    	  boolean check = false;
    	  for(final_sanpham sp : danhsachsanpham) {
    		  if(sp.getMasp().equals(masp)) {
    			  check=true;
    			  return check;
    		  }
    	  }
    	  return check;
      }
      
      
      // tra ve cong nhan

      public final_congnhan travecongnhan(String macn) {
          for (final_congnhan cn : danhsachcongnhan) {
              if (cn.getManv().equals(macn)) {
                  return cn;
              }
          }
          return null; // Return null if no match is found
      }
      
      
      public final_sanpham travesanpham(String masp) {
    	  for(final_sanpham sp : danhsachsanpham) {
    		  if(sp.getMasp().equals(masp)) {
    			  return sp;
    		  }
    	  }
    	  return null;
      }
      
      
      
      public void addEvent2(){
    	  
    	  
    	  
    	  final_congnhangiaodien ds = new final_congnhangiaodien(getTitle());
    	  danhsachcongnhan=ds.layDanhsachCongnhan();
    	  
    	  
    	  final_sanphamgiaodien tao = new final_sanphamgiaodien(getTitle());
    	  danhsachsanpham=tao.layDanhsachSanpham();
    	  
    	  
    	  
            btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String macn= txtCN.getText();
                String masp= txtSP.getText();
                String soluongstr = txtSL.getText();

             
             if(txtCN.getText().isEmpty() || txtSL.getText().isEmpty() || txtSP.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Hãy nhập đủ thông tin");
             }

             else {
            	 if(!kiemtracongnhan(macn)) {
            	     JOptionPane.showMessageDialog(null, "Mã công nhân không  tồn tại ! ");
            	 }
            	 else if(!kiemtrasanpham(masp)) {
            		 JOptionPane.showMessageDialog(null, "Mã sản phẩm không  tồn tại ! ");
            	 }
            	 else if (!txtSL.getText().matches("\\d+") || Integer.parseInt(txtSL.getText()) <= 0) {
            		    JOptionPane.showMessageDialog(null, "Giá trị không hợp lệ ! vui lòng nhập lại !");
            		}
            	 else if(kiemtracongnhan(macn)&&kiemtrasanpham(masp)) {
            		 final_congnhan cn = new final_congnhan();
            		 final_sanpham sp = new final_sanpham();
            		
            		 cn=travecongnhan(macn);
            		 sp=travesanpham(masp);
            		 long soluong = Long.parseLong(soluongstr);
            		 
            		 final_thongtinnopsanpham ttsp = new final_thongtinnopsanpham(cn, sp, soluong);
            		 danhsachnopthongtinsanpham.add(ttsp);
            		 final_dao_thongtinsanpham test = new final_dao_thongtinsanpham();
            		 test.addThongTinSanPham(ttsp);
            		 
            		 Object[] row1 = {
            				    ttsp.getCongnhan().getManv(),
            				    ttsp.getCongnhan().getName(),
            				    ttsp.getSanpham().getMasp(),
            				    ttsp.getSanpham().getName(),
            				    ttsp.getSoluong(),
            				    ttsp.tinhluong()
            				};
            				dtmSinhVien.addRow(row1);
            		 
            	 }
            	 
            	
            	 
            	 
                
             }
             
            
            
             
            }
        });
    
            
            btnXoa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tblSinhVien.getSelectedRow();
                    if (selectedRow != -1) {
                        // Lấy mã công nhân và mã sản phẩm từ bảng (tblSinhVien)
                        String maCN = (String) tblSinhVien.getValueAt(selectedRow, 0);
                        String maSP = (String) tblSinhVien.getValueAt(selectedRow, 2);
                        long luong =(long) tblSinhVien.getValueAt( selectedRow, 5);
                        long soluong=(long) tblSinhVien.getValueAt(selectedRow, 4);
                        
                        // Xác nhận xóa
                        int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                        	final_dao_thongtinsanpham dao = new final_dao_thongtinsanpham();
                            dao.xoaThongTinSanPham(maCN, maSP,soluong,luong);
                           
                            danhsachnopthongtinsanpham.remove(selectedRow);
                            // Cập nhật lại giao diện hiển thị
                            dtmSinhVien.removeRow(selectedRow);
                            for(final_thongtinnopsanpham tt : danhsachnopthongtinsanpham) {
       	                	 System.out.println(tt.getLuong());
                           }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Hãy chọn dòng cần xoá");
                    }
                }
            });

    
    
            btnSua.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tblSinhVien.getSelectedRow();
                    if (selectedRow != -1) { 
                        String maCNCu = (String) tblSinhVien.getValueAt(selectedRow, 0); // Lấy mã công nhân cũ từ bảng
                        String maSPCu = (String) tblSinhVien.getValueAt(selectedRow, 2); // Lấy mã sản phẩm cũ từ bảng
                        long soluongCU = (long) tblSinhVien.getValueAt(selectedRow, 4); // Lấy mã công nhân cũ từ bảng
                        long luongCu = (long) tblSinhVien.getValueAt(selectedRow, 5);
                        // Lấy các giá trị mới từ JTextField (txtCN, txtSP, txtSL, txtLuong)
                       
                        //ma moi
                        
                        String macnmoi = txtCN.getText().trim();
                        String maspmoi = txtSP.getText().trim();
                        String soluongstr = txtSL.getText().trim();
                        
                        if(!macnmoi.isEmpty()&&!maspmoi.isEmpty()&&!soluongstr.isEmpty()) {
                        
                
                        	try {
                        		long soluong = Long.parseLong(soluongstr);
                        		final_thongtinnopsanpham sv = danhsachnopthongtinsanpham.get(selectedRow);
                        		if(travecongnhan(macnmoi)==null) {
                        			System.out.print(macnmoi);
                        			JOptionPane.showMessageDialog(null, "không tồn tại mã công nhân này !");
                        			
                        		}
                        		else if(travesanpham(maspmoi)==null) {
                        			System.out.print(maspmoi);
                        			JOptionPane.showMessageDialog(null, "không tồn tại mã sản phẩm này !");
                        		}
                        		else {
                        			sv.setCongnhan(travecongnhan(macnmoi));
                        			sv.setSanpham(travesanpham(maspmoi));
                        			sv.setSoluong(soluong);
                        			
                        		    dtmSinhVien.setValueAt(macnmoi, selectedRow, 0);
                        		    dtmSinhVien.setValueAt(sv.getCongnhan().getName(), selectedRow, 1);
                                    dtmSinhVien.setValueAt(maspmoi, selectedRow, 2);
                            	    dtmSinhVien.setValueAt(sv.getSanpham().getName(), selectedRow, 3);
                                    dtmSinhVien.setValueAt(soluong, selectedRow, 4);
                                    dtmSinhVien.setValueAt(sv.tinhluong(), selectedRow, 5);
                                    final_dao_thongtinsanpham tk = new final_dao_thongtinsanpham();
                                    tk.capnhatthongtin(sv,macnmoi,maspmoi,soluong);
                                    danhsachnopthongtinsanpham.set(selectedRow, sv);
                        		}
                        	}catch(NumberFormatException ex){
                        		JOptionPane.showMessageDialog(null, "lỗi rồi hahahah !");
                        	}
                        
                        
                        
                        }
                        else {
                        	JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin !");
                        }
                        
                    }
                 
                else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa.");
                    }
                }
            });

    
            

    tblSinhVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = tblSinhVien.getSelectedRow();

            if (selectedRow != -1) {
                txtCN.setText(tblSinhVien.getValueAt(selectedRow, 0).toString());
                txtSP.setText(tblSinhVien.getValueAt(selectedRow, 2).toString());
                txtSL.setText(tblSinhVien.getValueAt(selectedRow, 4).toString());
            }
        }
    }
});
        
        
    
    btntimkiemcongnhan.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                    String ma = txtnhamcn.getText();
                    boolean check = false;
                    for( CongNhan2 cn : danhsachcongnhan){
                        if(ma.equals(cn.getManv())){
                            txttimkiemcongnhan.setText("  MNV: "+cn.getManv()+" - Tên: "+cn.getName()+" - Địa chỉ : "+ cn.getDiachi());
                            check = true;
                            break;
                        }
                    }
                    if(!check){
                        JOptionPane.showMessageDialog(null, "Công nhân không tồn tại ! ");
                    }
                 }
             });

             btninsanpham.addActionListener(new ActionListener() {
    		    @Override
    		    public void actionPerformed(ActionEvent e) {
    		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sanpham.txt"))) {
    		            for (final_sanpham sp : danhsachsanpham) {  
    		                writer.write("Mã SP: " + sp.getMasp() + ", Tên SP: " + sp.getName() + ", Giá: " + sp.getGia() + "\n");
    		            }
    		            JOptionPane.showMessageDialog(null, "Thông tin sản phẩm đã được in ra file sanpham.txt");
    		        } catch (IOException ex) {
    		            JOptionPane.showMessageDialog(null, "Lỗi khi in thông tin sản phẩm: " + ex.getMessage());
    		        }
    		    }
    		});

    		btninthong.addActionListener(new ActionListener() {
    		    @Override
    		    public void actionPerformed(ActionEvent e) {
    		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("thongtin.txt"))) {
    		            for (final_thongtinnopsanpham ttsp : danhsachnopthongtinsanpham) {  
    		                writer.write("Mã CN: " + ttsp.getCongnhan().getManv() + ", Tên CN: " + ttsp.getCongnhan().getName() +
    		                             ", Mã SP: " + ttsp.getSanpham().getMasp() + ", Tên SP: " + ttsp.getSanpham().getName() +
    		                             ", Số lượng: " + ttsp.getSoluong() + ", Lương: " + ttsp.getLuong() + "\n");
    		            }
    		            JOptionPane.showMessageDialog(null, "Thông tin chi tiết đã được in ra file thongtin.txt");
    		        } catch (IOException ex) {
    		            JOptionPane.showMessageDialog(null, "Lỗi khi in thông tin chi tiết: " + ex.getMessage());
    		        }
    		    }
    		});

    		btnincongnhan.addActionListener(new ActionListener() {
    		    @Override
    		    public void actionPerformed(ActionEvent e) {
    		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("congnhan.txt"))) {
    		            for (final_congnhan cn : danhsachcongnhan) {  
    		                writer.write("Mã CN: " + cn.getManv() + ", Tên CN: " + cn.getName() + ", Địa Chỉ: " + cn.getDiachi() + "\n");
    		            }
    		            JOptionPane.showMessageDialog(null, "Thông tin công nhân đã được in ra file congnhan.txt");
    		        } catch (IOException ex) {
    		            JOptionPane.showMessageDialog(null, "Lỗi khi in thông tin công nhân: " + ex.getMessage());
    		        }
    		    }
    		});
        
   
    
     menusanpham.addMouseListener(new MouseAdapter() {
         @Override
            public void mouseClicked(MouseEvent e) {
               openform();
            }
         
});
     menuthongtinsanpham.addMouseListener(new MouseAdapter() {
         @Override
            public void mouseClicked(MouseEvent e) {
               openform1();
            }
         
});
    
    
    }

   public void openform(){
       SanPhamGiaoDien2 tao = new SanPhamGiaoDien2("dj");
       tao.setVisible(true);
     
   }
   public void openform1(){
   final_thongtingiaodien tao = new final_thongtingiaodien("th");
   tao.setVisible(true);
   
      }
    
}


