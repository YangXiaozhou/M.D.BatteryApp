package nus.ise.xiaozhou.yang.batteryinfo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class BatteryInformation extends Activity {

    private TextView remainingTime, remainingPercentage, remainingPercentageData,
            batteryTechnology, batteryTechnologyData, batteryTemperature,
            batteryTemperatureData, batteryVoltage, batteryVoltageData,
            batteryHealth, batteryHealthData, batteryChargingState,
            batteryChargingStateData, batteryCurrent, batteryCurrentData;


    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            final String DEGREE = "\u2103";
            boolean present = intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
            if (present) {

                Bundle bundle = intent.getExtras();
                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
                int icon_small = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0);
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
                int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10; //convert to Celcius Degrees
                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
                int plugType = bundle.getInt(BatteryManager.EXTRA_PLUGGED);
                String technology = bundle.getString(BatteryManager.EXTRA_TECHNOLOGY);


                //Get current from Current Reader
                CurrentReader myCurrentReader = new CurrentReader();
                double current = myCurrentReader.getCurrent();

                remainingTime.setText("REMAINING TIME: 6 Hrs 54 Mins");

                remainingPercentage.setText("Remaining Battery:");
                remainingPercentageData.setText(level + "%");

                batteryTechnology.setText("Battery Technology:");
                batteryTechnologyData.setText(technology);

                batteryTemperature.setText("Temperature:");
                batteryTemperatureData.setText(temperature + " " + DEGREE);

                batteryVoltage.setText("Voltage:");
                batteryVoltageData.setText(voltage + " mV");

                batteryCurrent.setText("Current:");
                batteryCurrentData.setText(current + " mA");

                batteryHealth.setText("Health:");
                batteryHealthData.setText(getHealthString(health));

                batteryChargingState.setText("Charging State:");
                if (getStatusString(status).equals("Discharging") || getStatusString(status).equals("Not Charging")) {
                    batteryChargingStateData.setText(getStatusString(status));
                } else {
                    batteryChargingStateData.setText(getStatusString(status) + " (" + getPlugTypeString(plugType) + ")");

                }

                //Create a new batt info package
                BattInfoPackage myInfoPackage = new BattInfoPackage(level, temperature, voltage, current, getStatusString(status));
                InfoPackageWriter myBattInfoWriter = new InfoPackageWriter(myInfoPackage);
                myBattInfoWriter.writeToBattDataFile();


            } else {
                remainingTime.setText("Battery is not present!");
            }

        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        startService();


        //Find all textviews in the mainActivity
        remainingTime = (TextView) findViewById(R.id.remainingtime);
        remainingPercentage = (TextView) findViewById(R.id.remainingpercentage);
        remainingPercentageData = (TextView) findViewById(R.id.remainingpercentagedata);
        batteryTechnology = (TextView) findViewById(R.id.batterytechnology);
        batteryTechnologyData = (TextView) findViewById(R.id.batterytechnologydata);
        batteryTemperature = (TextView) findViewById(R.id.temperature);
        batteryTemperatureData = (TextView) findViewById(R.id.temperaturedata);
        batteryVoltage = (TextView) findViewById(R.id.voltage);
        batteryVoltageData = (TextView) findViewById(R.id.voltagedata);
        batteryCurrent = (TextView) findViewById(R.id.current);
        batteryCurrentData = (TextView) findViewById(R.id.currentdata);
        batteryHealth = (TextView) findViewById(R.id.health);
        batteryHealthData = (TextView) findViewById(R.id.healthdata);
        batteryChargingState = (TextView) findViewById(R.id.chargingstate);
        batteryChargingStateData = (TextView) findViewById(R.id.chargingstatedata);

        this.registerReceiver(this.batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }


    private String getPlugTypeString(int plugged) {
        String plugType = "Unknown";

        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                plugType = "AC";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                plugType = "USB";
                break;
        }
        return plugType;
    }

    private String getHealthString(int health) {
        String healthString = "Unknown";
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = "Good Condition";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = "Over Heat";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = "Failure";
                break;
        }
        return healthString;
    }

    private String getStatusString(int status) {
        String statusString = "Unknown_Foreground";

        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "Charging_Foreground";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "Discharging_Foreground";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "Full_Foreground";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "Not Charging_Foreground";
                break;
        }
        return statusString;
    }

    public void startService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

    }
}