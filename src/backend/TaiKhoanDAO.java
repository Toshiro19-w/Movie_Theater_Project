package backend;

import entity.TaiKhoan;
import utils.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
    private final Connection conn;

    public TaiKhoanDAO() {
        conn = DatabaseConnection.getConnection();
    }

    public List<TaiKhoan> getAllTaiKhoan() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT tenDangNhap, loaiTaiKhoan FROM TaiKhoan";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new TaiKhoan(
                        rs.getString("tenDangNhap"),
                        "",  // Không cần lấy mật khẩu
                        rs.getString("loaiTaiKhoan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean dangKyTaiKhoan(TaiKhoan tk) {
        // Kiểm tra xem tài khoản đã tồn tại chưa
        String checkSql = "SELECT COUNT(*) FROM TaiKhoan WHERE tenDangNhap = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, tk.getTenDangNhap());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;  // Tên đăng nhập đã tồn tại
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Mã hóa mật khẩu trước khi lưu vào database
        String hashedPassword = BCrypt.hashpw(tk.getMatKhau(), BCrypt.gensalt());

        // Chèn tài khoản mới
        String sql = "INSERT INTO TaiKhoan (tenDangNhap, matKhau, loaiTaiKhoan) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tk.getTenDangNhap());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, tk.getLoaiTaiKhoan());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
