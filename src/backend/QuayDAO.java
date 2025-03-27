package backend;

import entity.Quay;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuayDAO {
    public List<Quay> getAllQuay() {
        List<Quay> list = new ArrayList<>();
        String query = "SELECT * FROM Quay";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Quay(
                        rs.getInt("maQuay"),
                        rs.getString("viTri"),
                        rs.getString("moTa")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
