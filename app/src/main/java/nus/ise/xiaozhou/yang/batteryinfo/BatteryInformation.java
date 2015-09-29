package nus.ise.xiaozhou.yang.batteryinfo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class BatteryInformation extends Activity {
    private TextView remainingTime, remainingPercentage, remainingPercentageData, batteryTechnology, batteryTechnologyData,
            batteryTemperature, batteryTemperatureData, batteryVoltage, batteryVoltageData,
            batteryHealth, batteryHealthData, batteryChargingState, batteryChargingStateData;
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


                remainingTime.setText("REMAINING TIME: 6 Hrs 54 Mins");

                remainingPercentage.setText("Remaining Battery:");
                remainingPercentageData.setText(level + "%");

                batteryTechnology.setText("Battery Technology:");
                batteryTechnologyData.setText(technology);

                batteryTemperature.setText("Temperature:");
                batteryTemperatureData.setText(temperature + " " + DEGREE);

                batteryVoltage.setText("Voltage:");
                batteryVoltageData.setText(voltage + " mV");

                batteryHealth.setText("Health:");
                batteryHealthData.setText(getHealthString(health));

                batteryChargingState.setText("Charging State:");
                batteryChargingStateData.setText(getStatusString(status) + " (" + getPlugTypeString(plugType) + ")");

/*                batteryInfo.setText(
                        "Remaining Battery: " + level + "%" + "\n" +
                                "Battery Technology: " + technology + "\n" +
                                "Temperature: " + temperature + " " + DEGREE + "\n" +
                                "Voltage: " + voltage + " mV" + "\n" +
                                "Health: " + getHealthString(health) + "\n" +
                                "Charging State: " + getStatusString(status) + " (" + getPlugTypeString(plugType) + ")"
                );*/

/*                imageBatteryState.setImageResource(icon_small);
                imageBatteryState.setScaleX(5);
                imageBatteryState.setScaleY(5);
                imageBatteryState.setPadding(500, 500, 500, 500);*/

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
        batteryHealth = (TextView) findViewById(R.id.health);
        batteryHealthData = (TextView) findViewById(R.id.healthdata);
        batteryChargingState = (TextView) findViewById(R.id.chargingstate);
        batteryChargingStateData = (TextView) findViewById(R.id.charingstatedata);

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
        String statusString = "Unknown";

        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "Discharging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "Full";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "Not Charging";
                break;
        }
        return statusString;
    }


}