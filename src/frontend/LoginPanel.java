package frontend;

import com.formdev.flatlaf.FlatLightLaf;
import entity.TaiKhoan;
import backend.TaiKhoanDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends JFrame {
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public LoginPanel() {
        // Áp dụng FlatLaf Material Design
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Đăng nhập");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tạo panel chính
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40)); // Padding

        JLabel lblTitle = new JLabel("Đăng Nhập", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel lblUsername = new JLabel("Tài khoản:");
        txtUsername = new JTextField(15);
        JLabel lblPassword = new JLabel("Mật khẩu:");
        txtPassword = new JPasswordField(15);

        JButton btnLogin = new JButton("Đăng nhập");
        JButton btnDangKy = new JButton("Đăng Ký");

        // Thiết lập kiểu dáng nút
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(33, 150, 243));
        btnLogin.setForeground(Color.WHITE);
        btnDangKy.setFont(new Font("Arial", Font.BOLD, 14));
        btnDangKy.setBackground(new Color(76, 175, 80));
        btnDangKy.setForeground(Color.WHITE);

        // Căn giữa nội dung
        panelMain.add(lblTitle);
        panelMain.add(Box.createVerticalStrut(20));
        panelMain.add(lblUsername);
        panelMain.add(txtUsername);
        panelMain.add(Box.createVerticalStrut(10));
        panelMain.add(lblPassword);
        panelMain.add(txtPassword);
        panelMain.add(Box.createVerticalStrut(20));
        panelMain.add(btnLogin);
        panelMain.add(Box.createVerticalStrut(10));
        panelMain.add(btnDangKy);

        btnLogin.addActionListener(e -> checkLogin());
        btnDangKy.addActionListener(this::dangKyTaiKhoan);

        add(panelMain, BorderLayout.CENTER);
        setVisible(true);
    }

    private void checkLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.equals("admin") && password.equals("123")) {
            dispose();
            SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
        } else if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
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
                "Mật Khẩu:", txtMatKhau,
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
                JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
                dispose();
                SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
            } else {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại!");
            }
        }
    }
}
