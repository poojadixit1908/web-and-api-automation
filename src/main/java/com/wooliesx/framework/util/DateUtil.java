package com.wooliesx.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static boolean isThursday(String dateStr) {
        //format "2020-08-22 06:00:00"
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = simpleDateformat.parse(dateStr);
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            String day = sdf.format(date); // e.g. Thursday, Monday etc
            if (day.equalsIgnoreCase("Thursday")) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isThursday("2020-08-27 06:00:00"));
    }
}
