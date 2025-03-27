package backend;

import entity.*;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatVeDAO {
    public boolean kiemTraGheTrong(int maSuatChieu, String soGhe) {
        String sql = "SELECT COUNT(*) FROM Ve WHERE maSuatChieu = ? AND soGhe = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maSuatChieu);
            stmt.setString(2, soGhe);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // Ghế đã có người đặt
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true; // Ghế trống
    }

    public boolean themDatVe(DatVe datVe) {
        String sql = "INSERT INTO DatVe (maDatVe, maKhachHang, maSuatChieu, maVe, soLuong, tongTien, ngayDat) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, datVe.getMaDatVe());
            pstmt.setInt(2, datVe.getMaKhachHang());
            pstmt.setInt(3, datVe.getMaSuatChieu());
            pstmt.setInt(4, datVe.getMaVe());
            pstmt.setInt(5, datVe.getSoLuong());
            pstmt.setDouble(6, datVe.getTongTien());
            pstmt.setDate(7, Date.valueOf(String.valueOf(datVe.getNgayDat())));

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean huyVe(String maVe) {
        String sqlLayGiaVe = "SELECT giaVe, maKhachHang FROM Ve WHERE maVe = ?";
        String sqlXoaVe = "DELETE FROM Ve WHERE maVe = ?";
        String sqlHoanTien = "UPDATE KhachHang SET soDu = soDu + ? WHERE maKhachHang = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmtLayGia = conn.prepareStatement(sqlLayGiaVe);
             PreparedStatement stmtXoa = conn.prepareStatement(sqlXoaVe);
             PreparedStatement stmtHoanTien = conn.prepareStatement(sqlHoanTien)) {

            // Lấy giá vé & mã khách hàng
            stmtLayGia.setString(1, maVe);
            ResultSet rs = stmtLayGia.executeQuery();
            if (rs.next()) {
                double giaVe = rs.getDouble("giaVe");
                String maKhachHang = rs.getString("maKhachHang");

                // Xóa vé khỏi hệ thống
                stmtXoa.setString(1, maVe);
                stmtXoa.executeUpdate();

                // Hoàn tiền cho khách hàng
                stmtHoanTien.setDouble(1, giaVe);
                stmtHoanTien.setString(2, maKhachHang);
                return stmtHoanTien.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean doiGhe(String maVe, int maSuatChieu, String gheMoi) {
        if (!kiemTraGheTrong(maSuatChieu, gheMoi)) {
            System.out.println("Lỗi: Ghế này đã có người đặt!");
            return false;
        }

        String sql = "UPDATE Ve SET soGhe = ? WHERE maVe = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gheMoi);
            stmt.setString(2, maVe);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> layDanhSachGheTrong(String maSuatChieu, int soGheToiDa) {
        List<String> gheTrong = new ArrayList<>();
        String sql = "SELECT soGhe FROM Ve WHERE maSuatChieu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSuatChieu);
            try (ResultSet rs = stmt.executeQuery()) {
                List<String> gheDaDat = new ArrayList<>();
                while (rs.next()) {
                    gheDaDat.add(rs.getString("soGhe"));
                }
                // Tạo danh sách tất cả ghế
                for (int i = 1; i <= soGheToiDa; i++) {
                    String ghe = "A" + i; // Ví dụ: A1, A2, A3...
                    if (!gheDaDat.contains(ghe)) {
                        gheTrong.add(ghe);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gheTrong;
    }

    public boolean datNhieuVe(List<Ve> danhSachVe) {
        String sql = "INSERT INTO Ve (maVe, maSuatChieu, maKhachHang, soGhe, giaVe) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Ve ve : danhSachVe) {
                if (!kiemTraGheTrong(ve.getMaSuatChieu(), ve.getSoGhe())) {
                    System.out.println("Lỗi: Ghế " + ve.getSoGhe() + " đã có người đặt!");
                    return false;
                }
                stmt.setInt(1, ve.getMaVe());
                stmt.setInt(2, ve.getMaSuatChieu());
                stmt.setInt(3, ve.getMaKhachHang());
                stmt.setString(4, ve.getSoGhe());
                stmt.setDouble(5, ve.getGiaVe());
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
