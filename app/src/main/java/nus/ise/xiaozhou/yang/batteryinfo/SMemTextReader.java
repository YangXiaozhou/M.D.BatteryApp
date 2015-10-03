package nus.ise.xiaozhou.yang.batteryinfo;

/**
 * Created by YANG_XIAOZHOU on 3/10/15.
 */

import java.io.BufferedReader;
import java.io.FileReader;

import android.util.Log;

public class SMemTextReader {

    public static Long getValue() {

        boolean success = false;
        String text = null;

        try {

            FileReader fr = new FileReader("/sys/class/power_supply/battery/smem_text");
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                if (line.contains("I_MBAT")) {
                    text = line.substring(line.indexOf("I_MBAT: ") + 8);
                    success = true;
                    break;
                }
                line = br.readLine();
            }

            br.close();
            fr.close();
        } catch (Exception ex) {
            Log.e("CurrentWidget", ex.getMessage());
            ex.printStackTrace();
        }

        Long value = null;

        if (success) {

            try {
                value = Long.parseLong(text);
            } catch (NumberFormatException nfe) {
                Log.e("CurrentWidget", nfe.getMessage());
                value = null;
            }

        }

        return value;
    }

}
