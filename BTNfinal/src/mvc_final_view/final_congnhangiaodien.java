package mvc_final_view;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

import mvc_controller.dao;
import mvc_final_model.final_congnhan;
import mvc_final_model.final_sanpham;
import mvc_final_model.final_thongtinnopsanpham;
import mvc_model.sinhvien;
import mvc_final_dao.final_dao_congnhan;
public class final_congnhangiaodien extends JFrame{
	 
	   

	   
    
ArrayList<final_congnhan>danhsachcongnhan= new ArrayList<>();

ArrayList<final_thongtinnopsanpham>danhsachnopthongtinsanpham = new ArrayList<>();
Scanner sc = new Scanner(System.in);
JTable tblSinhVien;
DefaultTableModel dtmSinhVien;
JTextField txtMa, txtTen, txtDC,txttimkiemcongnhan,txtnhamcn,txttimkiemsanpham;
JButton btnThem, btnXoa, btnSua,btntimkiemcongnhan,btntimkiemsanpham,btn1,btn2,btn3;
private  Vector<final_congnhan> listTKcongnhan;
// phần đăng nhập
JTextField Hang2, Hang3;
JButton buttonlogin, buttoncreate;
JPasswordField passwordField;


JMenuBar menuBar;
JMenu menucongnhan, menusanpham, menuthongtinsanpham;





public final_congnhangiaodien(String title) throws HeadlessException {
    super(title);
    addControl();
    addEvent();
 
}



public void addControl(){
    // thân
    
    
    
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

    // Tạo menu
    menucongnhan = new JMenu("Công nhân");
    menusanpham = new JMenu("Sản phẩm"); // Khởi tạo menu Edit
    menuthongtinsanpham = new JMenu("Thông tin sản phẩm"); // Khởi tạo menu Help
    
    
    menuBar.add(menucongnhan);
    menuBar.add(menusanpham);
    menuBar.add(menuthongtinsanpham);

    setJMenuBar(menuBar); // Thiết lập thanh menu cho JFrame
    //Tạo JTable
    
    dtmSinhVien = new DefaultTableModel();
    dtmSinhVien.addColumn("Mã CN");
    dtmSinhVien.addColumn("Tên CN");
    dtmSinhVien.addColumn("Địa chỉ");
  
    
    
    final_dao_congnhan tk = new final_dao_congnhan();
    listTKcongnhan = tk.layDSTK();
    for (final_congnhan sv : listTKcongnhan) {
        Vector<Object> rowData = new Vector<>();  // Tạo vectơ hàng mới
        rowData.add(sv.getManv());
        rowData.add(sv.getName());
        rowData.add(sv.getDiachi());
        danhsachcongnhan.add(sv);
        dtmSinhVien.addRow(rowData);  // Thêm dữ liệu hàng vào mô hình bảng
    }
   
    tblSinhVien = new JTable(dtmSinhVien);
   
    // phần cuộn chuột của bảng
    JScrollPane scSinhVien = new JScrollPane(tblSinhVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    pnLefttoTop.setLayout(new BorderLayout());
    pnLefttoTop.add(scSinhVien, BorderLayout.CENTER);
    JPanel pnTieuDeDS = new JPanel();
    JLabel lblTDDS = new JLabel("DANH SÁCH CÔNG NHÂN 2024");
    lblTDDS.setFont(rbItalic);
    lblTDDS.setForeground(Color.DARK_GRAY);
    pnTieuDeDS.add(lblTDDS);
    pnLefttoTop.add(pnTieuDeDS, BorderLayout.NORTH);
    // phần top bên phải
    pnRightofTop.setLayout(new BoxLayout(pnRightofTop, BoxLayout.Y_AXIS));
   
    JPanel pnTTTT = new JPanel();
    JLabel lblTT = new JLabel("Thông tin công nhân");
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
    JLabel lblDC = new JLabel("ĐC");
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
JLabel lblTTbotton = new JLabel("Tìm kiếm công nhân ");
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

//phần trái dưới 
pnLefttoBottom.setLayout(new BoxLayout(pnLefttoBottom, BoxLayout.Y_AXIS));
JPanel first = new JPanel();
JLabel lblfirst = new JLabel("Thông tin công nhân tìm kiếm ");
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

public final_congnhan travecongnhan(String macn) {
    for (final_congnhan cn : danhsachcongnhan) {
        if (cn.getManv().equals(macn)) {
            return cn;
        }
    }
    return null; // Return null if no match is found
}
public void addEvent(){
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
             vec.add(txtDC.getText());
             dtmSinhVien.addRow(vec);
             danhsachcongnhan.add( new final_congnhan(txtMa.getText(),txtTen.getText(),txtDC.getText()));
          
             final_congnhan sv = new final_congnhan();
             sv.setManv(txtMa.getText());
             sv.setName(txtTen.getText());
             sv.setDiachi(txtDC.getText());
             danhsachcongnhan.add(sv);
             final_dao_congnhan tk = new final_dao_congnhan();
              tk.themcongnhan(sv);
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
                             danhsachcongnhan.remove(i);
                             break; 
                         }
                     }

                     
                     final_dao_congnhan tk = new final_dao_congnhan();
                     tk.xoacongnhan(maSV);
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
        	            String maCNCu = (String) tblSinhVien.getValueAt(selectedRow, 0); 
        	            String nameCNCu = (String) tblSinhVien.getValueAt(selectedRow, 1);
        	            String diachiCNCu = (String) tblSinhVien.getValueAt(selectedRow, 2); 
        	            // Lấy các giá trị mới từ JTextField (txtMa, txtTen, txtDC)
        	            String maMoi = txtMa.getText().trim();
        	            String tenMoi = txtTen.getText().trim();
        	            String diaChiMoi = txtDC.getText().trim();

        	            // Kiểm tra các giá trị đầu vào
        	           
        	            if (!maMoi.isEmpty() && !tenMoi.isEmpty() && !diaChiMoi.isEmpty()) {
        	                try {
        	                    final_congnhan sv = danhsachcongnhan.get(selectedRow);
        	                    if(travecongnhan(maMoi)==null) {
        	                    	sv.setManv(maCNCu);
        	                    	sv.setName(nameCNCu);
        	                    	sv.setDiachi(diachiCNCu);
        	                    	dtmSinhVien.setValueAt(maMoi, selectedRow, 0);
        	                     	dtmSinhVien.setValueAt(tenMoi, selectedRow, 1);
        	                     	dtmSinhVien.setValueAt(diaChiMoi, selectedRow, 2);
        	                     	final_dao_congnhan tk = new final_dao_congnhan();
        	                     	tk.capnhatcongnhan(sv, maMoi,tenMoi,diaChiMoi);
        	                     	 System.out.print(sv);
        	                     	 final_congnhan test = new final_congnhan();
        	                     	 test = sv;
        	                     	danhsachcongnhan.set(selectedRow,test );
        	                     	for(final_congnhan cn : danhsachcongnhan) {
        	                     		System.out.print(cn.getManv()+" ");
											
										
        	                     	}
        	                    	 JOptionPane.showMessageDialog(null, "Cập nhật công nhân thành công!");
        	                    }
        	                    else {
        	                    	JOptionPane.showMessageDialog(null, "Mã công nhân đã tồn tại !");
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
                for( final_congnhan cn : danhsachcongnhan){
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


public ArrayList<final_congnhan> layDanhsachCongnhan() {
    
    return this.danhsachcongnhan;
}

private void refresh() {
    txtMa.setText("");
    txtTen.setText("");
    txtDC.setText("");
}

public void openform(){
   final_sanphamgiaodien tao = new final_sanphamgiaodien("dj");
   tao.setVisible(true);
 
}
public void openform1(){
 final_thongtingiaodien tao = new final_thongtingiaodien("th");
tao.setVisible(true);

}

public void showWindow(){
    this.setSize(700, 600);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    
}







}