package backend;

import entity.HoaDon;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        String query = "SELECT * FROM HoaDon";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new HoaDon(
                        rs.getInt("maHoaDon"),
                        rs.getInt("maNhanVien"),
                        rs.getInt("maKhachHang"),
                        rs.getDate("ngayLap"),
                        rs.getDouble("tongTien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
