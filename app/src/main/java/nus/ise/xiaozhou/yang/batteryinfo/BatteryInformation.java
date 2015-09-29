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
    private TextView batteryInfo;
    private ImageView imageBatteryState;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        batteryInfo=(TextView)findViewById(R.id.textViewBatteryInfo);
        imageBatteryState=(ImageView)findViewById(R.id.imageViewBatteryState);

        this.registerReceiver(this.batteryInfoReceiver,	new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String DEGREE  = "\u2103";
            boolean  present = intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
            if(present) {
                Bundle bundle = intent.getExtras();
                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
                int icon_small = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0);
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
                int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10; //convert to celcius degrees
                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
                int current = bundle.getInt("current_avg");
                int plugType = bundle.getInt(BatteryManager.EXTRA_PLUGGED);
                String technology = bundle.getString(BatteryManager.EXTRA_TECHNOLOGY);


                batteryInfo.setText(
                        "Remaining Battery: " + level + "%" + "\n" +
                                "Scale: " + scale + "\n" +
                                "Battery Technology: " + technology + "\n" +
                                "Temperature: " + temperature + " " + DEGREE + "\n" +
                                "Voltage: " + voltage + " mV" + "\n" +
                                "Current: " + current + " mA" + "\n" +
                                "Health: " + getHealthString(health) + "\n" +
                                "Charging State: " + getStatusString(status) + " (" + getPlugTypeString(plugType) + ")"
                );

                imageBatteryState.setImageResource(icon_small);
                imageBatteryState.setScaleX(5);
                imageBatteryState.setScaleY(5);
                imageBatteryState.setPadding(500, 500, 500, 500);

            }else{
                batteryInfo.setText("Battery is not present!");
            }

        }
    };

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