package mvc_final_view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
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

import mvc_final_dao.final_dao_congnhan;
import mvc_final_dao.final_dao_sanpham;
import mvc_final_dao.final_dao_thongtinsanpham;
import mvc_final_model.final_congnhan;
import mvc_final_model.final_sanpham;
import mvc_final_model.final_thongtinnopsanpham;

/**
 *
 * @author Admin
 */
public class final_sanphamgiaodien extends JDialog{
    
    JTable tblSinhVien;
    DefaultTableModel dtmSinhVien;
    JTextField txtMa, txtTen, txtDC,txttimkiemcongnhan,txtnhamcn,txttimkiemsanpham;
    JButton btnThem, btnXoa, btnSua,btntimkiemcongnhan,btntimkiemsanpham;
    JMenuBar menuBar;
    JMenu menucongnhan, menusanpham, menuthongtinsanpham;
    private  Vector<final_sanpham> listTKsanpham;
    
    
      ArrayList<final_sanpham>danhsachsanpham= new ArrayList<>();
     
    

    public final_sanphamgiaodien(String title) throws HeadlessException {
        
        addControl1();
        addEvent1();
         setSize(700, 600);
        

    }
    
   
    
    
    public void addControl1(){
         Container con = getContentPane();
        JPanel pnLeft = new JPanel();
        pnLeft.setLayout(new BorderLayout());
        pnLeft.setPreferredSize(new Dimension(400, 0));
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
        dtmSinhVien.addColumn("Mã SP");
        dtmSinhVien.addColumn("Tên SP");
        dtmSinhVien.addColumn("Giá Tiền");
        final_dao_sanpham tk = new final_dao_sanpham();
        listTKsanpham = tk.layDSTK();
        for (final_sanpham sv : listTKsanpham) {
            Vector<Object> rowData = new Vector<>();  // Tạo vectơ hàng mới
            rowData.add(sv.getMasp());
            rowData.add(sv.getName());
            rowData.add(sv.getGia());
            danhsachsanpham.add(sv);
            dtmSinhVien.addRow(rowData);  // Thêm dữ liệu hàng vào mô hình bảng
        }
       
        tblSinhVien = new JTable(dtmSinhVien);
       
        // phần cuộn chuột của bảng
        JScrollPane scSinhVien = new JScrollPane(tblSinhVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnLefttoTop.setLayout(new BorderLayout());
        pnLefttoTop.add(scSinhVien, BorderLayout.CENTER);
        JPanel pnTieuDeDS = new JPanel();
        JLabel lblTDDS = new JLabel("DANH SÁCH SẢN PHẨM");
        lblTDDS.setFont(rbItalic);
        lblTDDS.setForeground(Color.DARK_GRAY);
        pnTieuDeDS.add(lblTDDS);
        pnLefttoTop.add(pnTieuDeDS, BorderLayout.NORTH);
        // phần top bên phải
        pnRightofTop.setLayout(new BoxLayout(pnRightofTop, BoxLayout.Y_AXIS));
       
        JPanel pnTTTT = new JPanel();
        JLabel lblTT = new JLabel("Thông tin sản phẩm");
        lblTT.setFont(rbItalic);
        lblTT.setForeground(Color.DARK_GRAY); 
        pnTTTT.add(lblTT);
        pnRightofTop.add(pnTTTT);
        JPanel pnMa = new JPanel();
        JLabel lblMa = new JLabel("Mã");
        txtMa = new JTextField(15);
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        pnRightofTop.add(pnMa);
        JPanel pnTen = new JPanel();
        JLabel lblTen = new JLabel("Tên");
        txtTen = new JTextField(15);
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnRightofTop.add(pnTen);
        JPanel pnDC = new JPanel();
        JLabel lblDC = new JLabel("Giá");
        txtDC = new JTextField(15);
        pnDC.add(lblDC);
        pnDC.add(txtDC);
        pnRightofTop.add(pnDC);
        JPanel pnXuLy = new JPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        pnXuLy.add(btnThem);
        pnXuLy.add(btnSua);
        pnXuLy.add(btnXoa);
        pnRightofTop.add(pnXuLy);
        
        Dimension buttonSize = new Dimension(70, 30);
        
        btnThem.setBackground(Color.LIGHT_GRAY);
        btnThem.setPreferredSize(buttonSize);
         btnXoa.setBackground(Color.LIGHT_GRAY);
        btnXoa.setPreferredSize(buttonSize);
         btnSua.setBackground(Color.LIGHT_GRAY);
        btnSua.setPreferredSize(buttonSize);
      
        
        

    
        // phần dưới bên phải 
        pnLefttoTop.setBorder(blueBorder);
        pnLefttoBottom.setBorder(blueBorder);
        pnRightofTop.setBorder(blueBorder);
        pnRightofBotton.setBorder(blueBorder);
        
        
pnRightofBotton.setLayout(new BoxLayout(pnRightofBotton, BoxLayout.Y_AXIS));
JPanel pnTTTTbotton = new JPanel();
JLabel lblTTbotton = new JLabel("Tìm kiếm sản phẩm ");
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
JLabel lblfirst = new JLabel("Thông tin sản phẩm tìm kiếm ");
lblfirst.setFont(rbItalic);
lblfirst.setForeground(Color.DARK_GRAY);
first.add(lblfirst);
JPanel second = new JPanel();
txttimkiemcongnhan = new JTextField(32);
txttimkiemcongnhan.setPreferredSize(new Dimension(150, 40));
txttimkiemcongnhan.setBorder(blueBorder);
txtDC.setBorder(blueBorder);
txtMa.setBorder(blueBorder);
txtTen.setBorder(blueBorder);
second.add(txttimkiemcongnhan);
 second.setBorder(new EmptyBorder(0, 0,30, 0));
pnLefttoBottom.add(first);
pnLefttoBottom.add(second);

    }
    
    public final_sanpham travesanpham(String masp) {
  	  for(final_sanpham sp : danhsachsanpham) {
  		  if(sp.getMasp().equals(masp)) {
  			  return sp;
  		  }
  	  }
  	  return null;
    }
    
    
    public void addEvent1(){
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ma= txtMa.getText();
             boolean check = false ;
             for(int  i = 0;i<tblSinhVien.getRowCount();i++){
                 if(tblSinhVien.getValueAt(i, 0).equals(ma)){
                     check = true;
                     break;
                 }
             }
             if(txtDC.getText().isEmpty() || txtMa.getText().isEmpty() || txtTen.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Hãy nhập đủ thông tin");
             }

             else if(!check){
                 Vector vec = new Vector();
                 vec.add(txtMa.getText());
                 vec.add(txtTen.getText());
                 vec.add(Integer.parseInt(txtDC.getText())); 
                 int gia = Integer.parseInt(txtDC.getText());
                 dtmSinhVien.addRow(vec);
                 danhsachsanpham.add( new final_sanpham(txtMa.getText(),txtTen.getText(),gia));
              
                 final_sanpham sv = new final_sanpham();
                 sv.setMasp(ma);
                 sv.setName(txtTen.getText());
                 sv.setGia(gia);

                 final_dao_sanpham tk = new final_dao_sanpham();
                  tk.themsanpham(sv);
                 refresh();
             }
             
             else{
                 JOptionPane.showMessageDialog(null, "Mã công nhân đã tồn tại ! ");
             }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSV = txtMa.getText().trim(); 

                if (!maSV.isEmpty()) {
                  
                    for (int i = 0; i < tblSinhVien.getRowCount(); i++) {
                        if (tblSinhVien.getValueAt(i, 0).toString().equals(maSV)) {
                            dtmSinhVien.removeRow(i);
                            danhsachsanpham.remove(i);
                            break; 
                        }
                    }

                    
                    final_dao_sanpham tk = new final_dao_sanpham();
                    tk.xoasanpham(maSV);
                     refresh();
                    JOptionPane.showMessageDialog(null, "Xóa sinh viên thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sinh viên cần xoá.");
                }
            }
        });
    
    
   
        btnSua.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        int selectedRow = tblSinhVien.getSelectedRow();
    	        if (selectedRow != -1) { 
    	            // Lấy mã công nhân cũ từ bảng
    	            String maSPCu = (String) tblSinhVien.getValueAt(selectedRow, 0); 
    	            String nameSPCu = (String) tblSinhVien.getValueAt(selectedRow, 1);
    	            Long soluongCu = (Long) tblSinhVien.getValueAt(selectedRow, 2); 
    	            // Lấy các giá trị mới từ JTextField (txtMa, txtTen, txtDC)
    	            String maMoi = txtMa.getText().trim();
    	            String tenMoi = txtTen.getText().trim();
    	            String soluongstr = txtDC.getText().trim();

    	            // Kiểm tra các giá trị đầu vào
    	           
    	            if (!maMoi.isEmpty() && !tenMoi.isEmpty() && !soluongstr.isEmpty()) {
    	                try {
    	                	long soluongmoi = Long.parseLong(soluongstr);
    	                    final_sanpham sv = danhsachsanpham.get(selectedRow);
    	  
    	                    if(travesanpham(maMoi)==null) {
    	                    	sv.setMasp(maSPCu);
    	                    	sv.setName(nameSPCu);
    	                    	sv.setGia(soluongCu);;
    	                    	dtmSinhVien.setValueAt(maMoi, selectedRow, 0);
    	                     	dtmSinhVien.setValueAt(tenMoi, selectedRow, 1);
    	                     	dtmSinhVien.setValueAt(soluongmoi, selectedRow, 2);
    	                     	final_dao_sanpham tk = new final_dao_sanpham();
    	                     	tk.capnhatsanpham(sv, maMoi,tenMoi,soluongmoi);
    	                     	 System.out.print(sv);
    	                     	 final_sanpham test = new final_sanpham();
    	                     	 test = sv;
    	                     	danhsachsanpham.set(selectedRow,test );
    	                     	for(final_sanpham sp : danhsachsanpham) {
    	                     		System.out.print(sp.getGia()+" ");
										
									
    	                     	}
    	                    	 JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thành công!");
    	                    }
    	                    else {
    	                    	JOptionPane.showMessageDialog(null, "Mã sản phẩm  đã tồn tại !");
    	                    }
    	                 

    	                   
    	                } catch (Exception ex) {
    	                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage());
    	                }
    	            } else {
    	                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin để cập nhật.");
    	            }
    	        } else {
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
                txtMa.setText(tblSinhVien.getValueAt(selectedRow, 0).toString());
                txtTen.setText(tblSinhVien.getValueAt(selectedRow, 1).toString());
                txtDC.setText(tblSinhVien.getValueAt(selectedRow, 2).toString());
            }
        }
    }
});
        
    

    
    
    btntimkiemcongnhan.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                    String ma = txtnhamcn.getText();
                    boolean check = false;
                    for( final_sanpham cn : danhsachsanpham){
                        if(ma.equals(cn.getMasp())){
                            txttimkiemcongnhan.setText("  MSP: "+cn.getMasp()+" - Tên: "+cn.getName()+" - Địa chỉ : "+ cn.getGia());
                            check = true;
                            break;
                        }
                    }
                    if(!check){
                        JOptionPane.showMessageDialog(null, "Sản phẩm không tồn tại ! ");
                    }
                 }
             });
           
    }
    
    public ArrayList<final_sanpham> layDanhsachSanpham() {
       
        return this.danhsachsanpham;
    }

    private void refresh() {
        txtMa.setText("");
        txtTen.setText("");
        txtDC.setText("");
    }






}
