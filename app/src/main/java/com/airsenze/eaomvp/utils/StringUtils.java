package com.airsenze.eaomvp.utils;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

public class StringUtils {

    public static int getSimilarity (CharSequence string1, CharSequence string2) {

        int min = Math.min(string1.length(), string2.length());
        int similarity = 0;

        for(int i = 0; i < min; i++) {
            if(string1.charAt(i) == string2.charAt(i)) {
                similarity++;
            }
        }

        return similarity;
    }
}
