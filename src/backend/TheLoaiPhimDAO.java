package backend;

import entity.TheLoaiPhim;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TheLoaiPhimDAO {
    public List<TheLoaiPhim> getAllTheLoaiPhim() {
        List<TheLoaiPhim> list = new ArrayList<>();
        String query = "SELECT * FROM TheLoaiPhim";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new TheLoaiPhim(
                        rs.getInt("maTheLoai"),
                        rs.getInt("tenTheLoai")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
