package entity;
import java.time.LocalDateTime;

public class DatVe {
    private int maDatVe;
    private int maKhachHang;
    private int maSuatChieu;
    private int maVe;
    private int soLuong;
    private double tongTien;
    private LocalDateTime ngayDat;

    public DatVe(int maDatVe, int maKhachHang, int maSuatChieu, int maVe, int soLuong, double tongTien, LocalDateTime ngayDat) {
        this.maDatVe = maDatVe;
        this.maKhachHang = maKhachHang;
        this.maSuatChieu = maSuatChieu;
        this.maVe = maVe;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ngayDat = ngayDat;
    }

    public int getMaDatVe() {
        return maDatVe;
    }

    public void setMaDatVe(int maDatVe) {
        this.maDatVe = maDatVe;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(int maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public int getMaVe() {
        return maVe;
    }

    public void setMaVe(int maVe) {
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