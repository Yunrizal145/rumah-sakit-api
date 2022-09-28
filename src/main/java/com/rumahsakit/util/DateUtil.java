package com.rumahsakit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    // Untuk Audit Data per Tanggal dan Waktu
    public static String GetDateTime(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());

        return date;

    }


}
