package entity;

public class Quay {
    private int maQuay;
    private String viTri;
    private String moTa;

    public Quay(int maQuay, String viTri, String moTa) {
        this.maQuay = maQuay;
        this.viTri = viTri;
        this.moTa = moTa;
    }

    public int getMaQuay() {
        return maQuay;
    }

    public void setMaQuay(int maQuay) {
        this.maQuay = maQuay;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
