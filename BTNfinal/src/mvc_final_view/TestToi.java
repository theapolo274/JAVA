package mvc_final_view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import mvc_final_model.final_congnhan;
import mvc_final_model.final_sanpham;

import java.awt.*;
import java.util.ArrayList;

public class TestToi extends JFrame {

    private ArrayList<final_sanpham> danhsachsanpham;
    private ArrayList<final_congnhan> danhsachcongnhan;

    public TestToi() {
        // Khởi tạo các ArrayList trong constructor
        danhsachsanpham = new ArrayList<>();
        danhsachcongnhan = new ArrayList<>();

        // Lấy dữ liệu từ giao diện để gán vào danh sách
        final_congnhangiaodien ds = new final_congnhangiaodien(getTitle());
        danhsachcongnhan = ds.layDanhsachCongnhan();

        final_sanphamgiaodien tao = new final_sanphamgiaodien(getTitle());
        danhsachsanpham = tao.layDanhsachSanpham();

        // Cấu hình JFrame
        setTitle("Danh sách sản phẩm và công nhân");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Tạo JPanel để chứa JTable
        JPanel panel = new JPanel(new GridLayout(2, 1)); // GridLayout 2 dòng 1 cột

        // Tạo JTable cho danh sách công nhân
        String[] columnCongNhan = {"ID", "Tên", "Địa chỉ"};
        DefaultTableModel modelCongNhan = new DefaultTableModel(columnCongNhan, 0);
        for (final_congnhan congnhan : danhsachcongnhan) {
            modelCongNhan.addRow(new Object[]{congnhan.getManv(), congnhan.getName(), congnhan.getDiachi()});
        }
        JTable tableCongNhan = new JTable(modelCongNhan);

        // Tạo JTable cho danh sách sản phẩm
        String[] columnSanPham = {"Mã sản phẩm", "Tên sản phẩm", "Giá"};
        DefaultTableModel modelSanPham = new DefaultTableModel(columnSanPham, 0);
        for (final_sanpham sanpham : danhsachsanpham) {
            modelSanPham.addRow(new Object[]{sanpham.getMasp(), sanpham.getName(), sanpham.getGia()});
        }
        JTable tableSanPham = new JTable(modelSanPham);

        // Thêm JTable vào JPanel
        panel.add(new JScrollPane(tableCongNhan));
        panel.add(new JScrollPane(tableSanPham));

        // Thêm JPanel vào JFrame
        add(panel);

        // Hiển thị JFrame
        setVisible(true);
    }

    public static void main(String[] args) {
        // Tạo một đối tượng của lớp TestToi để hiển thị GUI
        SwingUtilities.invokeLater(() -> new TestToi());
    }
}
