package frontend;

import backend.VeDAO;
import entity.Ve;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class QuanLyVePanel extends JPanel {
    private VeDAO veDAO = new VeDAO();
    private JTable table;
    private DefaultTableModel model;

    public QuanLyVePanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Mã Vé", "Mã Suất Chiếu", "Số Ghế", "Giá Vé"}, 0);
        table = new JTable(model);
        loadData();

        JPanel panel = new JPanel();
        JButton btnXoa = new JButton("Xóa");

        btnXoa.addActionListener(e -> xoaVe());

        panel.add(btnXoa);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    private void loadData() {
        model.setRowCount(0);
        List<Ve> list = veDAO.getAllVe();
        for (Ve v : list) {
            model.addRow(new Object[]{v.getMaVe(), v.getMaSuatChieu(), v.getSoGhe(), v.getGiaVe()});
        }
    }

    private void xoaVe() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn vé cần xóa!");
            return;
        }

        String maVe = (String) model.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa vé này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (veDAO.xoaVe(maVe)) {
                JOptionPane.showMessageDialog(this, "Xóa vé thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Không thể xóa vé này. Hãy kiểm tra ràng buộc dữ liệu!");
            }
        }
    }
}
