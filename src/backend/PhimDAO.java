package backend;

import entity.Phim;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhimDAO {
    private final Connection conn;

    public PhimDAO() {
        conn = DatabaseConnection.getConnection();
    }

    public List<Phim> getAllPhim() {
        List<Phim> list = new ArrayList<>();
        String sql = "SELECT * FROM Phim";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Phim(rs.getInt("maPhim"), rs.getString("tenPhim"), rs.getInt("thoiLuong"),
                        rs.getDate("ngayKhoiChieu").toLocalDate(), rs.getString("ngonNgu"), rs.getString("dinhDang"),
                        rs.getString("moTa"), rs.getString("daoDien"), rs.getString("dienVien"),
                        rs.getInt("gioiHanTuoi")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean themPhim(Phim phim) {
        String sql = "INSERT INTO Phim VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, phim.getMaPhim());
            stmt.setString(2, phim.getTenPhim());
            stmt.setInt(3, phim.getThoiLuong());
            stmt.setString(4, String.valueOf(phim.getNgayKhoiChieu()));
            stmt.setString(5, phim.getNgonNgu());
            stmt.setString(6, phim.getDinhDang());
            stmt.setString(7, phim.getMoTa());
            stmt.setString(8, phim.getDaoDien());
            stmt.setString(9, phim.getDienVien());
            stmt.setInt(10, phim.getGioiHanTuoi());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean suaPhim(Phim phim) {
        String sql = "UPDATE Phim SET tenPhim=?, thoiLuong=?, ngayKhoiChieu=?, ngonNgu=?, dinhDang=?, moTa=?, daoDien=?, dienVien=?, gioiHanTuoi=? WHERE maPhim=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, phim.getTenPhim());
            stmt.setInt(2, phim.getThoiLuong());
            stmt.setString(3, String.valueOf(phim.getNgayKhoiChieu()));
            stmt.setString(4, phim.getNgonNgu());
            stmt.setString(5, phim.getDinhDang());
            stmt.setString(6, phim.getMoTa());
            stmt.setString(7, phim.getDaoDien());
            stmt.setString(8, phim.getDienVien());
            stmt.setInt(9, phim.getGioiHanTuoi());
            stmt.setInt(10, phim.getMaPhim());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaPhim(String maPhim) {
        String sql = "DELETE FROM Phim WHERE maPhim=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maPhim);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

