package entity;

public class Ve {
    private String maVe;
    private String maSuatChieu;
    private String maKhachHang;
    private String soGhe;
    private double giaVe;

    public Ve(String maVe, String maSuatChieu, String maKhachHang, String soGhe, double giaVe) {
        this.maVe = maVe;
        this.maSuatChieu = maSuatChieu;
        this.maKhachHang = maKhachHang;
        this.soGhe = soGhe;
        this.giaVe = giaVe;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(String maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }
}
