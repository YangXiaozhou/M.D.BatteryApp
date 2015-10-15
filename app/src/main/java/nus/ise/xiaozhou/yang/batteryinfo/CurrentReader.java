package nus.ise.xiaozhou.yang.batteryinfo;

import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by YANG_XIAOZHOU on 14/10/15.
 */

public class CurrentReader {
    public static final int PROC_SPACE_TERM = (int) ' ';
    public static final int PROC_OUT_LONG = 0x2000;
    private static final String TAG = "CurrendReader";

    private static final String CURRENT_FILE = "/sys/class/power_supply/battery/current_now";
    private static final String CURRENT_FILE_HTC_ONE = "/sys/class/power_supply/battery/batt_attr_text";
    private static final String CURRENT_FILE_HTC = "/sys/class/power_supply/battery/batt_current";
    private static final String CURRENT_FILE_GALAXY_NOTE = "/sys/class/power_supply/battery/batt_current_adc";
    private static final String CURRENT_FILE_NEXUS_10 = "/sys/class/power_supply/ds2784-fuelgauge/current_now";
    private static final String CURRENT_FILE_NEXUS_ONE = "/sys/devices/platform/ds2784-battery/getcurrent";

    private static final double CURRENT_CONV = 1e-6; // Source in microamps.
    private static final int[] READ_LONG_FORMAT = new int[]{
            PROC_SPACE_TERM | PROC_OUT_LONG
    };
    private static CurrentReader instance = null;
    String currentFile;
    double currentConv;
    private Method methodReadProcFile;
    private long[] readBuf;


    public static CurrentReader getInstance() {
        if (instance == null) {
            instance = new CurrentReader();
        }
        return instance;
    }

    public boolean hasCurrent() {
        return currentFile != null;
    }

    public double getCurrent() {

        long curr = 0;

        if (new File(CURRENT_FILE).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        } else if (new File(CURRENT_FILE_HTC_ONE).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        } else if (new File(CURRENT_FILE_GALAXY_NOTE).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        } else if (new File(CURRENT_FILE_HTC).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        } else if (new File(CURRENT_FILE_NEXUS_10).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        } else if (new File(CURRENT_FILE_NEXUS_ONE).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        }


        if (hasCurrent()) {
            curr = readLongFromFile(currentFile);
        }

        //return curr == -1 ? -1.0 : 0.11;
        return curr == -1 ? -1.0 : currentConv * curr;
    }

    public long readLongFromFile(String file) {
        if (methodReadProcFile == null) return -1;
        try {
            if ((Boolean) methodReadProcFile.invoke(
                    null, file, READ_LONG_FORMAT, null, readBuf, null)) {
                return readBuf[0];
            }
        } catch (IllegalAccessException e) {
            Log.w(TAG, "Failed to get pid cpu usage");
        } catch (InvocationTargetException e) {
            Log.w(TAG, "Exception thrown while getting pid cpu usage");
        }
        return -1L;
    }


}
