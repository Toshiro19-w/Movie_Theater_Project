package backend;

import entity.SuatChieu;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuatChieuDAO {
    public List<SuatChieu> getAllQuay() {
        List<SuatChieu> list = new ArrayList<>();
        String query = "SELECT * FROM Quay";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new SuatChieu(
                        rs.getInt("maSuatChieu"),
                        rs.getInt("maPhim"),
                        rs.getInt("maPhong"),
                        rs.getDate("ngayGioChieu").toLocalDate().atStartOfDay()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
