package com.airsenze.eaomvp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

public class TimeUtils {

    private final static String DATE_FORMAT = "yyyy-MM-dd";

    public static String getDateString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.CANADA);
        return simpleDateFormat.format(date);
    }

}
