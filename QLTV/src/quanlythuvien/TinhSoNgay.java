/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ManhVD
 */
public class TinhSoNgay {

    public TinhSoNgay() {

    }

    public long soNgay(String startDate, String endDate) {

        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date currentDate = new Date();
        Date date1 = null;
        Date date2 = null;

        long getDiff = 0;
        long getDaysDiff = 0;
        try {

            date1 = simpleDateFormat.parse(startDate);
            date2 = simpleDateFormat.parse(endDate);

            getDiff = date2.getTime() - date1.getTime();

            getDaysDiff = getDiff / (24 * 60 * 60 * 1000);

            //System.out.println("Differance between date " + startDate + " and " + endDate + " is " + getDaysDiff + " days.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getDaysDiff;

    }
}
