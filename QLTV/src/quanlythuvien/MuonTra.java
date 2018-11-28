/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

/**
 *
 * @author Manh VU
 */
public class MuonTra {
    private String maMuon;
    private String maDG;
    private String maNV;
    private String ngayMuon;
    private String hanTra;
    
    public MuonTra(){
        
    }

    public MuonTra(String maMuon, String maDG, String maNV, String ngayMuon, String hanTra) {
        this.maMuon = maMuon;
        this.maDG = maDG;
        this.maNV = maNV;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
    }

    public String getMaMuon() {
        return maMuon;
    }

    public void setMaMuon(String maMuon) {
        this.maMuon = maMuon;
    }

    public String getMaDG() {
        return maDG;
    }

    public void setMaDG(String maDG) {
        this.maDG = maDG;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getHanTra() {
        return hanTra;
    }

    public void setHanTra(String hanTra) {
        this.hanTra = hanTra;
    }
    
    
}
