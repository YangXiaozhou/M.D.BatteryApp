package nus.ise.xiaozhou.yang.batteryinfo;

import android.util.Log;

import java.io.File;


/*
 * Created by YANG_XIAOZHOU on 3/10/15.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class OneLineReader {

    private static final String TAG = "OneLineReader";

    public static double getValue(File _f, boolean _convertToMillis) {

        String text = null;

        try {
            FileInputStream fs = new FileInputStream(_f);
            InputStreamReader sr = new InputStreamReader(fs);
            BufferedReader br = new BufferedReader(sr);

            text = br.readLine();

            br.close();
            sr.close();
            fs.close();
        } catch (Exception ex) {
            Log.e(TAG, "Reading current file failed!");
        }

        Long value = null;
        double current = 0;

        if (text != null) {
            try {
                value = Long.parseLong(text);
            } catch (NumberFormatException nfe) {
                Log.e(TAG, "Parsing current value failed!");
                value = null;
            }

            if (_convertToMillis && value != null) {
                value = value / 1000; // convert to milliampere
                current = Long.valueOf(value).doubleValue();
            }
        }

        return current;
    }

}

