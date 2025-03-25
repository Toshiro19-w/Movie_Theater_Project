package entity;
import java.time.LocalDateTime;

public class DatVe {
    private String maDatVe;
    private String maKhachHang;
    private String maSuatChieu;
    private String maVe;
    private int soLuong;
    private double tongTien;
    private LocalDateTime ngayDat;

    public DatVe(String maDatVe, String maKhachHang, String maSuatChieu, String maVe, int soLuong, double tongTien, LocalDateTime ngayDat) {
        this.maDatVe = maDatVe;
        this.maKhachHang = maKhachHang;
        this.maSuatChieu = maSuatChieu;
        this.maVe = maVe;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ngayDat = ngayDat;
    }

    public String getMaDatVe() {
        return maDatVe;
    }

    public void setMaDatVe(String maDatVe) {
        this.maDatVe = maDatVe;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(String maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDateTime getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDateTime ngayDat) {
        this.ngayDat = ngayDat;
    }
}