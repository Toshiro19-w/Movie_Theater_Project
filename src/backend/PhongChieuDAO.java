package backend;

import entity.PhongChieu;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongChieuDAO {
    public List<PhongChieu> getAllPhongChieu() {
        List<PhongChieu> list = new ArrayList<>();
        String query = "SELECT * FROM PhongChieu";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new PhongChieu(
                        rs.getInt("maTheLoai"),
                        rs.getInt("soLuongGhe"),
                        rs.getString("loaiPhong")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
