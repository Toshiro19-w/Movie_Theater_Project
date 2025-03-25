package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {
    private final JPanel mainPanel;

    public MainGUI() {
        setTitle("Hệ thống quản lý rạp chiếu phim");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuQuanLy = new JMenu("Quản lý");
        JMenuItem menuPhim = new JMenuItem("Quản lý Phim");
        JMenuItem menuVe = new JMenuItem("Quản lý Vé");
        JMenuItem menuTaiKhoan = new JMenuItem("Quản lý Tài khoản");

        menuPhim.addActionListener(this::openQuanLyPhim);
        menuVe.addActionListener(this::openQuanLyVe);
        menuTaiKhoan.addActionListener(this::openQuanLyTaiKhoan);

        menuQuanLy.add(menuPhim);
        menuQuanLy.add(menuVe);
        menuQuanLy.add(menuTaiKhoan);
        menuBar.add(menuQuanLy);

        JMenu menuHeThong = new JMenu("Hệ thống");
        JMenuItem menuDangXuat = new JMenuItem("Đăng xuất");
        JMenuItem menuThoat = new JMenuItem("Thoát");

        menuDangXuat.addActionListener(e -> logout());
        menuThoat.addActionListener(e -> System.exit(0));

        menuHeThong.add(menuDangXuat);
        menuHeThong.add(menuThoat);
        menuBar.add(menuHeThong);

        setJMenuBar(menuBar);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void openQuanLyPhim(ActionEvent e) {
        setMainPanel(new QuanLyPhimPanel());
    }

    private void openQuanLyVe(ActionEvent e) {
        setMainPanel(new QuanLyVePanel());
    }

    private void openQuanLyTaiKhoan(ActionEvent e) {
        setMainPanel(new QuanLyTaiKhoanPanel());
    }

    private void setMainPanel(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void logout() {
        dispose();
        SwingUtilities.invokeLater(() -> new LoginPanel().setVisible(true)); // Mở lại màn hình đăng nhập
    }
}