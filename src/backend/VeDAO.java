package backend;

import entity.Ve;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeDAO {
    private final Connection conn;

    public VeDAO() {
        conn = DatabaseConnection.getConnection();
    }

    public List<Ve> getAllVe() {
        List<Ve> list = new ArrayList<>();
        String sql = "SELECT * FROM Ve";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Ve(rs.getString("maVe"), rs.getString("maSuatChieu"), rs.getString("maKhachHang"),
                        rs.getString("soGhe"), rs.getDouble("giaVe")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean suaVe(Ve ve) {
        String sql = "UPDATE Ve SET maSuatChieu=?, maKhachHang=?, soGhe=?, giaVe=? WHERE maVe=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ve.getMaSuatChieu());
            stmt.setString(2, ve.getMaKhachHang());
            stmt.setString(3, ve.getSoGhe());
            stmt.setDouble(4, ve.getGiaVe());
            stmt.setString(5, ve.getMaVe());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaVe(String maVe) {
        String sql = "DELETE FROM Ve WHERE maVe=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maVe);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
