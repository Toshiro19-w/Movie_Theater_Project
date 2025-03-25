package frontend;

import backend.PhimDAO;
import entity.Phim;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

public class QuanLyPhimPanel extends JPanel {
    private final PhimDAO phimDAO = new PhimDAO();
    private final JTable table;
    private final DefaultTableModel model;

    public QuanLyPhimPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Mã Phim", "Tên Phim", "Thời Lượng", "Ngày Khởi Chiếu"}, 0);
        table = new JTable(model);
        loadData();

        JPanel panel = new JPanel();
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");

        btnSua.addActionListener(this::suaPhim);
        btnXoa.addActionListener(this::xoaPhim);

        panel.add(btnSua);
        panel.add(btnXoa);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    void loadData() {
        model.setRowCount(0);
        List<Phim> list = phimDAO.getAllPhim();
        for (Phim p : list) {
            model.addRow(new Object[]{p.getMaPhim(), p.getTenPhim(), p.getThoiLuong(), p.getNgayKhoiChieu()});
        }
    }

    private void suaPhim(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn phim cần sửa!");
            return;
        }

        String maPhim = (String) model.getValueAt(selectedRow, 0);
        String tenPhim = JOptionPane.showInputDialog(this, "Nhập tên phim mới:", model.getValueAt(selectedRow, 1));
        if (tenPhim == null || tenPhim.trim().isEmpty()) return;

        // Lấy ngày khởi chiếu cũ từ table
        LocalDate ngayKhoiChieu = (LocalDate) model.getValueAt(selectedRow, 3);

        Phim phim = new Phim(maPhim, tenPhim, 120, ngayKhoiChieu, "Tiếng Việt", "2D", "", "", "", 0);
        if (phimDAO.suaPhim(phim)) {
            JOptionPane.showMessageDialog(this, "Sửa phim thành công!");
            loadData();
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi sửa phim!");
        }
    }

    private void xoaPhim(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn phim cần xóa!");
            return;
        }

        String maPhim = (String) model.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa phim này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (phimDAO.xoaPhim(maPhim)) {
                JOptionPane.showMessageDialog(this, "Xóa phim thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi xóa phim!");
            }
        }
    }
}