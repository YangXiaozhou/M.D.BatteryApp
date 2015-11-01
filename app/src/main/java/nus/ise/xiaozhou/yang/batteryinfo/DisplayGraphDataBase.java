package nus.ise.xiaozhou.yang.batteryinfo;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by YANG_XIAOZHOU on 29/10/15.
 */
public class DisplayGraphDataBase {
    public static ArrayList<CurrentTimeInfoPair> infoPairData = new ArrayList<CurrentTimeInfoPair>();
    public static int sizeOfDataBase = 0;

    public DisplayGraphDataBase() {

    }

    public void newPairAction(CurrentTimeInfoPair infoPair) {

        if (sizeOfDataBase < 50) {
            if (sizeOfDataBase == 0) {
                infoPairData.add(0, infoPair);
                sizeOfDataBase = sizeOfDataBase + 1;
            } else {
                infoPairData.add(infoPair);
                sizeOfDataBase = sizeOfDataBase + 1;
            }


        } else {

            if (infoPairData.get(0).get_dayOfTheWeek().equals(infoPair.get_dayOfTheWeek())) {  //Checking whether the new info is within 24 hrs of the existing data
                infoPairData.add(infoPair);
                sizeOfDataBase = sizeOfDataBase + 1;
            } else {
                infoPairData.remove(0);
                infoPairData.add(infoPair);
            }
        }


    }

}
