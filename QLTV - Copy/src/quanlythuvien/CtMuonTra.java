/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

/**
 *
 * @author ManhVD
 */
public class CtMuonTra {
    private String maMuon;
    private String maSach;
    private int soLuong;
    private String ngayTra;
    private int tienCoc;
    private long tienPhat;
    private String ghiChu;
    
    public CtMuonTra(){
        
    }

    public CtMuonTra(String maMuon, String maSach, int soLuong, String ngayTra, int tienCoc, long tienPhat, String ghiChu) {
        this.maMuon = maMuon;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.ngayTra = ngayTra;
        this.tienCoc = tienCoc;
        this.tienPhat = tienPhat;
        this.ghiChu = ghiChu;
    }

    public String getMaMuon() {
        return maMuon;
    }

    public String getMaSach() {
        return maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public int getTienCoc() {
        return tienCoc;
    }

    public long getTienPhat() {
        return tienPhat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setMaMuon(String maMuon) {
        this.maMuon = maMuon;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public void setTienCoc(int tienCoc) {
        this.tienCoc = tienCoc;
    }

    public void setTienPhat(long tienPhat) {
        this.tienPhat = tienPhat;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
