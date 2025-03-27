package backend;

import entity.ChiTietHoaDon;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {
    public List<ChiTietHoaDon> getAllChiTietHoaDon() {
        List<ChiTietHoaDon> list = new ArrayList<>();
        String query = "SELECT * FROM ChiTietHoaDon";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new ChiTietHoaDon(
                        rs.getInt("maHoaDon"),
                        rs.getInt("maVe"),
                        rs.getInt("soLuong"),
                        rs.getDouble("thanhTien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
