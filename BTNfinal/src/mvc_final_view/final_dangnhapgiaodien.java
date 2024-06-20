package mvc_final_view;




/**
 *
 * @author Admin
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mvc_final_dao.final_dao_dangnhap;
import mvc_final_dao.final_dao_sanpham;
import mvc_final_model.final_dangnhap;
import mvc_final_model.final_sanpham;



public class final_dangnhapgiaodien extends JFrame{
    JTextField Hang2, Hang3;
    JButton buttonlogin, buttoncreate;
    JPasswordField passwordField;

    public final_dangnhapgiaodien(String title) throws HeadlessException {
        super(title);
        addControl3();
    }

    public final_dangnhapgiaodien() {
		// TODO Auto-generated constructor stub
	}

	public void addControl3(){
        Container condangnhap = getContentPane();
        JPanel pnMaindangnhap = new JPanel();
        pnMaindangnhap.setLayout(new BoxLayout(pnMaindangnhap, BoxLayout.Y_AXIS));
        condangnhap.add(pnMaindangnhap);
        
        Font rbItalic = new Font("Times New Roman", Font.CENTER_BASELINE, 20);
        
        // dòng 1 
        JLabel lblHang1 = new JLabel("Đăng nhập vào hệ thống");
     
        lblHang1.setFont(rbItalic);
        JPanel pnHang1 = new JPanel();
        pnHang1.add(lblHang1);
        pnMaindangnhap.add(pnHang1);
        
        // dòng 2 
        JPanel pnHang2 = new JPanel();
        JLabel txtHang2 = new JLabel("Username");
       
        txtHang2.setFont(rbItalic);
        txtHang2.setOpaque(true);
        txtHang2.setForeground(Color.BLACK);
        txtHang2.setBackground(Color.LIGHT_GRAY); // Thiết lập màu nền
        txtHang2.setBorder(new EmptyBorder(10, 25, 10, 25));
        Hang2 = new JTextField(20);
        Dimension labelSize = txtHang2.getPreferredSize();
        Hang2.setPreferredSize(new Dimension(Hang2.getPreferredSize().width, labelSize.height));
        pnHang2.add(txtHang2);
        pnHang2.add(Hang2);
        pnMaindangnhap.add(pnHang2);
        
        // dòng 3 
        JPanel pnHang3 = new JPanel();
        JLabel txtHang3 = new JLabel("Password");
        txtHang3.setFont(rbItalic);
        txtHang3.setOpaque(true);
        txtHang3.setForeground(Color.BLACK);
        txtHang3.setBackground(Color.LIGHT_GRAY); 
        txtHang3.setBorder(new EmptyBorder(10, 25, 10, 25));
        pnHang3.add(txtHang3);
        passwordField = new JPasswordField(20);
        Dimension labelSize2 = txtHang3.getPreferredSize();
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, labelSize2.height));
        pnHang3.add(passwordField);
        pnMaindangnhap.add(pnHang3);

        // dòng 4
        JPanel pnHang4 = new JPanel();
        Dimension pnHang4Size = new Dimension(360, 50);
        pnHang4.setPreferredSize(pnHang4Size);
        pnHang4.setLayout(new BoxLayout(pnHang4, BoxLayout.X_AXIS));    
        buttonlogin = new JButton("Đăng nhập");
        buttonlogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttoncreate = new JButton("Đăng ký");
        buttoncreate.setAlignmentX(Component.RIGHT_ALIGNMENT);
        buttonlogin.setBackground(Color.LIGHT_GRAY);
        buttoncreate.setBackground(Color.LIGHT_GRAY);
        buttonlogin.setPreferredSize(labelSize2); 
        buttoncreate.setPreferredSize(labelSize2); 
        buttonlogin.setFont(rbItalic);
        buttoncreate.setFont(rbItalic);

        pnHang4.add(buttonlogin);
        pnHang4.add(Box.createHorizontalGlue());
        pnHang4.add(buttoncreate);
        JPanel pnHang44 = new JPanel();
        pnHang44.add(pnHang4);
        pnMaindangnhap.add(pnHang44);

        buttonlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               login1();
            }
        });

        buttoncreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             openformdangnhap();  
            }
        });
    }

    public void login1(){
        String usename = Hang2.getText();
        String password = new String(passwordField.getPassword());
        final_dao_dangnhap dn = new final_dao_dangnhap();
        
        if(dn.kiemTraTaiKhoanMatKhau(usename, password)){
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
            dispose();
            final_congnhangiaodien  test = new final_congnhangiaodien("Sản Phẩm");
            test.showWindow();
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng!");
        }
    }

    private void openformdangnhap(){
        createaccount dangnhap = new createaccount(this);
        dangnhap.setVisible(true);
    }
    
    public void showWindowsdangnhap() {
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

   
}

class createaccount extends JDialog {
   
    JTextField Hang2, Hang3;
    JButton submit;
    JPasswordField passwordField;
    public createaccount(JFrame parent) {
        super(parent, "Đăng ký tài khoản", true);
        setSize(600, 400);
        addControl4();
    }

    public void addControl4(){
          Font rbItalic = new Font("Times New Roman", Font.CENTER_BASELINE, 20);
        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        con.add(pnMain);
        
        JLabel txthang1 = new JLabel("Đăng ký tài khoản");
        JPanel pnHang1 = new JPanel();
        Font f = new Font("Times New Roman", Font.PLAIN, 20);
        txthang1.setFont(rbItalic);
        pnHang1.add(txthang1);
        pnMain.add(pnHang1);
        
         // dòng 2 
        JPanel pnHang2 = new JPanel();
        JLabel txtHang2 = new JLabel("Username");
        
        txtHang2.setFont(rbItalic);
        txtHang2.setOpaque(true);
        txtHang2.setForeground(Color.BLACK);
        txtHang2.setBackground(Color.LIGHT_GRAY); // Thiết lập màu nền
        txtHang2.setBorder(new EmptyBorder(10, 25, 10, 25));
        Hang2 = new JTextField(20);
        Dimension labelSize = txtHang2.getPreferredSize();
        Hang2.setPreferredSize(new Dimension(Hang2.getPreferredSize().width, labelSize.height));
        pnHang2.add(txtHang2);
        pnHang2.add(Hang2);
        pnMain.add(pnHang2);
        
        // dòng 3 
        JPanel pnHang3 = new JPanel();
        JLabel txtHang3 = new JLabel("Password");
        txtHang3.setFont(rbItalic);
        txtHang3.setOpaque(true);
        txtHang3.setForeground(Color.BLACK);
        txtHang3.setBackground(Color.LIGHT_GRAY); 
        txtHang3.setBorder(new EmptyBorder(10, 25, 10, 25));
        pnHang3.add(txtHang3);
        passwordField = new JPasswordField(20);
        Dimension labelSize2 = txtHang3.getPreferredSize();
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, labelSize2.height));
        pnHang3.add(passwordField);
        pnMain.add(pnHang3);
        
        //hang4
        JPanel pnHang4 = new JPanel();
        submit = new JButton("Đăng ký");
        submit.setPreferredSize(labelSize2);
        submit.setBackground(Color.LIGHT_GRAY);
        submit.setFont(rbItalic);
        pnHang4.add(submit);
        pnMain.add(pnHang4);
        
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             register();
            }
        });
      
        

    }
    private void register(){
       String username = Hang2.getText();
       String password = new String(passwordField.getPassword());
       if(username.length()==0&&password.length()==0){
           JOptionPane.showMessageDialog(this,"Chưa nhập tên đăng nhập và mật khẩu !");
       }
       else if(username.contains(" ")){
           JOptionPane.showMessageDialog(this, "Tên đăng nhập không chứa dấu cách!");
       }
       else if(username.length()==0){
           JOptionPane.showMessageDialog(this,"Chưa nhập mật khẩu !");
       }
       else if (password.length() < 6 || !password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
           JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự và bao gồm chữ cái, chữ số và ký tự đặc biệt!");
       }
       else {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
            
         
           
           
            final_dangnhap sv = new final_dangnhap();
            sv.setTaikhoan(username);
            sv.setMatkhau(password);
           

            final_dao_dangnhap tk = new final_dao_dangnhap();
             tk.themtaikhoan(sv);


        }
       
    }
               
               
               
               }

