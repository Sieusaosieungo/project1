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
public class test {
    public static void main(String[] args) {
        String date1="20-11-2018";
        String date2="30-11-2018";
        
        TinhSoNgay dates=new TinhSoNgay();
        System.out.println(dates.soNgay(date1, date2));
    }
}
