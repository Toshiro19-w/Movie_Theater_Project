package frontend;

import backend.TaiKhoanDAO;
import entity.TaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class QuanLyTaiKhoanPanel extends JPanel {
    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private final DefaultTableModel model;

    public QuanLyTaiKhoanPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Tên Đăng Nhập", "Loại Tài Khoản"}, 0);
        JTable table = new JTable(model);
        loadData();

        JPanel panel = new JPanel();

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    public void loadData() {
        model.setRowCount(0);
        List<TaiKhoan> list = taiKhoanDAO.getAllTaiKhoan();
        for (TaiKhoan tk : list) {
            model.addRow(new Object[]{tk.getTenDangNhap(), tk.getLoaiTaiKhoan()});
        }
    }
}
