package com.airsenze.eaomvp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

public class ToastUtils {

    private static Toast toast;

    @SuppressLint("ShowToast")
    public static void initialize(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    public static void showToast(String message) {
        if(toast != null) {
            toast.setText(message);
            toast.show();
        }
    }

}
