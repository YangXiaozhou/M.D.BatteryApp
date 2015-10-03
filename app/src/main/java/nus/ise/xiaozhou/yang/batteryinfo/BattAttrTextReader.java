package nus.ise.xiaozhou.yang.batteryinfo;

/**
 * Created by YANG_XIAOZHOU on 3/10/15.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import android.util.Log;

public class BattAttrTextReader {

    public static Long getValue(File f, String dischargeField, String chargeField) {

        String text = null;
        Long value = null;

        try {

            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            final String chargeFieldHead = chargeField + ": ";
            final String dischargeFieldHead = dischargeField + ": ";


            while (line != null) {
                if (line.contains(chargeField)) {
                    text = line.substring(line.indexOf(chargeFieldHead) + chargeFieldHead.length());
                    try {
                        value = Long.parseLong(text);
                        if (value != 0)
                            break;
                    } catch (NumberFormatException nfe) {
                        Log.e("CurrentWidget", nfe.getMessage(), nfe);
                    }
                }

                //  "batt_discharge_current:"
                if (line.contains(dischargeField)) {
                    text = line.substring(line.indexOf(dischargeFieldHead) + dischargeFieldHead.length());
                    try {
                        value = (-1) * Math.abs(Long.parseLong(text));
                    } catch (NumberFormatException nfe) {
                        Log.e("CurrentWidget", nfe.getMessage(), nfe);
                    }
                    break;
                }


                line = br.readLine();
            }

            br.close();
            fr.close();
        } catch (Exception ex) {
            Log.e("CurrentWidget", ex.getMessage(), ex);
        }


        return value;
    }

}