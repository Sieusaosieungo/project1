package quanlythuvien;

public class Sach {

    private String maSach;
    private String tenSach;
    private String tenTG;
    private String NXB;
    private String namXB;
    private int giaTien;
    private String theLoai;
    private int soLuongCon;
    private int soLuong;

    public Sach() {

    }

    public Sach(String maSach, String tenSach, String tenTG, String nXB, String namXB, int giaTien, String theLoai,
            int soLuongCon, int soLuong) {
        super();
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tenTG = tenTG;
        NXB = nXB;
        this.namXB = namXB;
        this.giaTien = giaTien;
        this.theLoai = theLoai;
        this.soLuongCon = soLuongCon;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String nXB) {
        NXB = nXB;
    }

    public String getNamXB() {
        return namXB;
    }

    public void setNamXB(String namXB) {
        this.namXB = namXB;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getSoLuongCon() {
        return soLuongCon;
    }

    public void setSoLuongCon(int soLuongCon) {
        this.soLuongCon = soLuongCon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

}
