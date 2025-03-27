package entity;

public class Ve {
    private int maVe;
    private int maSuatChieu;
    private int maKhachHang;
    private String soGhe;
    private double giaVe;

    public Ve(int maVe, int maSuatChieu, int maKhachHang, String soGhe, double giaVe) {
        this.maVe = maVe;
        this.maSuatChieu = maSuatChieu;
        this.maKhachHang = maKhachHang;
        this.soGhe = soGhe;
        this.giaVe = giaVe;
    }

    public int getMaVe() {
        return maVe;
    }

    public void setMaVe(int maVe) {
        this.maVe = maVe;
    }

    public int getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(int maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
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

    public void setGiaVe(double
                                 giaVe) {
        this.giaVe = giaVe;
    }
}
