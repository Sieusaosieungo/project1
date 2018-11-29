/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import java.util.Date;

/**
 *
 * @author Manh VU
 */
public class DocGia {

    private String maDG;
    private String tenDG;
    private String GT;
    private String ngaySinh;
    private String diaChi;
    private String sdt;

    private String cmnd;

    public DocGia() {

    }

    public DocGia(String maDG, String tenDG, String GT, String ngaySinh, String diaChi, String sdt, String cmnd) {
        this.maDG = maDG;
        this.tenDG = tenDG;
        this.GT = GT;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;

        this.cmnd = cmnd;
    }

    public String getMaDG() {
        return maDG;
    }

    public void setMaDG(String maDG) {
        this.maDG = maDG;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getGT() {
        return GT;
    }

    public void setGT(String GT) {
        this.GT = GT;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cnnd) {
        this.cmnd = cnnd;
    }

}
