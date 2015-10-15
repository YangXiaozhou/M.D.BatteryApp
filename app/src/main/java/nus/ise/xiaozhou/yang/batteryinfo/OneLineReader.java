/*
package nus.ise.xiaozhou.yang.batteryinfo;

import java.io.File;

*/
/*
 * Created by YANG_XIAOZHOU on 3/10/15.
 *//*



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.util.Log;

public class OneLineReader {

    public static Long getValue(File _f, boolean _convertToMillis) {

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
            Log.e("CurrentWidget", ex.getMessage());
            ex.printStackTrace();
        }

        Long value = null;

        if (text != null) {
            try {
                value = Long.parseLong(text);
            } catch (NumberFormatException nfe) {
                Log.e("CurrentWidget", nfe.getMessage());
                value = null;
            }

            if (_convertToMillis && value != null) {
                value = value / 1000; // convert to milliampere
            }
        }

        return value;
    }

}
*/
