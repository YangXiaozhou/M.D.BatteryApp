package nus.ise.xiaozhou.yang.batteryinfo;

import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by YANG_XIAOZHOU on 24/10/15.
 */
public class MyService extends Service {


    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            boolean present = intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
            if (present) {

                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
                int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10; //convert to Celcius Degrees
                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);


                //Get current from Current Reader
                CurrentReader myCurrentReader = new CurrentReader();
                double current = myCurrentReader.getCurrent();


                //Create a new batt info package
                BattInfoPackage myInfoPackage = new BattInfoPackage(level, temperature, voltage, current, getStatusString(status));
                InfoPackageWriter myBattInfoWriter = new InfoPackageWriter(myInfoPackage);
                myBattInfoWriter.writeToBattDataFile();


            }

        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startID) {
        //this service will run until we stop it
        Toast.makeText(this, "Service started...", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        unregisterReceiver(batteryInfoReceiver);
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate() {

        registerReceiver(batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private String getStatusString(int status) {
        String statusString = "Unknown_Background";

        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "Charging_Background";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "Discharging_Background";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "Full_Background";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "Not Charging_Background";
                break;
        }
        return statusString;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }
}
