package com.app.hexuegang.wsnmonitor.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by HEXG on 2017/4/8.
 */
public class ToastUtils
{

        private static Toast toast = null;

        public static void toast(Context context, String msg) {
            if (toast == null) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.show();
        }
        public static void toast(Context context, int msg) {
            if (toast == null) {
                toast = Toast.makeText(context, context.getResources().getString(msg), Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.show();
        }

}
