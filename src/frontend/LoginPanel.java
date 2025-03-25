package frontend;

import entity.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import backend.*;

public class LoginPanel extends JFrame {
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    QuanLyPhimPanel quanLyPhimPanel = new QuanLyPhimPanel();

    public LoginPanel() {
        setTitle("Đăng nhập");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 100, 50));

        JLabel lblUsername = new JLabel("Tài khoản:");
        JLabel lblPassword = new JLabel("Mật khẩu:");
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Đăng nhập");
        JButton btnDangKy = new JButton("Đăng Ký");

        btnDangKy.addActionListener(this::dangKyTaiKhoan);
        btnLogin.addActionListener(e -> checkLogin());

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        add(new JLabel());
        add(btnDangKy);
        setVisible(true);
    }

    private void checkLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.equals("admin") && password.equals("123")) {
            dispose(); // Đóng cửa sổ đăng nhập
            SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true)); // Mở MainGUI admin
        } else if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dangKyTaiKhoan(ActionEvent e) {
        JTextField txtTenDangNhap = new JTextField();
        JPasswordField txtMatKhau = new JPasswordField();
        JPasswordField txtXacNhanMatKhau = new JPasswordField();

        Object[] message = {
                "Tên Đăng Nhập:", txtTenDangNhap,
                "Mật Khẩu (Tối thiểu 6 ký tự):", txtMatKhau,
                "Xác Nhận Mật Khẩu:", txtXacNhanMatKhau
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Đăng Ký Tài Khoản", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String tenDangNhap = txtTenDangNhap.getText().trim();
            String matKhau = new String(txtMatKhau.getPassword());
            String xacNhanMatKhau = new String(txtXacNhanMatKhau.getPassword());

            if (tenDangNhap.isEmpty() || matKhau.isEmpty() || xacNhanMatKhau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            if (matKhau.length() < 6) {
                JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!");
                return;
            }

            if (!matKhau.equals(xacNhanMatKhau)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp!");
                return;
            }

            TaiKhoan tk = new TaiKhoan(tenDangNhap, matKhau, "user");
            if (taiKhoanDAO.dangKyTaiKhoan(tk)) {
                JOptionPane.showMessageDialog(this, "Đăng ký tài khoản thành công!");
                dispose();
                SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true)); // mở GUI cho user
                quanLyPhimPanel.loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại. Hãy thử tên khác!");
            }
        }
    }
}