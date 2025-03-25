package backend;

import entity.DatVe;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class DatVeDAO {
    public boolean themDatVe(DatVe datVe) {
        String sql = "INSERT INTO DatVe (maDatVe, maKhachHang, maSuatChieu, maVe, soLuong, tongTien, ngayDat) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, datVe.getMaDatVe());
            pstmt.setString(2, datVe.getMaKhachHang());
            pstmt.setString(3, datVe.getMaSuatChieu());
            pstmt.setString(4, datVe.getMaVe());
            pstmt.setInt(5, datVe.getSoLuong());
            pstmt.setDouble(6, datVe.getTongTien());
            pstmt.setDate(7, Date.valueOf(String.valueOf(datVe.getNgayDat())));

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
