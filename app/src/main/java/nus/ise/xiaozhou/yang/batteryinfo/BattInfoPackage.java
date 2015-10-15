package nus.ise.xiaozhou.yang.batteryinfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by YANG_XIAOZHOU on 3/10/15.
 */
public class BattInfoPackage {

    private int _id;
    private int _remainingPercentage;
    private int _batteryTemperature;
    private int _batteryVoltage;
    private double _batteryCurrent;
    private String _currentTime;
    private String _chargingStatus;


    public BattInfoPackage() {
    }

    public BattInfoPackage(int remainingPercentage, int batteryTemperature, int batteryVoltage,
                           double batteryCurrent, String chargingStatus) {

        this._remainingPercentage = remainingPercentage;
        this._batteryTemperature = batteryTemperature;
        this._batteryVoltage = batteryVoltage;
        this._batteryCurrent = batteryCurrent;
        this._chargingStatus = chargingStatus;

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        set_currentTime(formattedDate);

    }

    public String infoToString() {
        String battDataString = "";
        battDataString = "" + get_remainingPercentage() + " "
                + get_batteryTemperature() + " "
                + get_batteryVoltage() + " "
                + get_batteryCurrent() + " "
                + get_currentTime() + " "
                + get_chargingStatus() + "\n";

        return battDataString;
    }

    public void set_charging(String chargingStatus) {
        this._chargingStatus = chargingStatus;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_remainingPercentage() {
        return _remainingPercentage;
    }

    public void set_remainingPercentage(int _remainingPercentage) {
        this._remainingPercentage = _remainingPercentage;
    }

    public int get_batteryTemperature() {
        return _batteryTemperature;
    }

    public void set_batteryTemperature(int _batteryTemperature) {
        this._batteryTemperature = _batteryTemperature;
    }

    public int get_batteryVoltage() {
        return _batteryVoltage;
    }

    public void set_batteryVoltage(int _batteryVoltage) {
        this._batteryVoltage = _batteryVoltage;
    }

    public double get_batteryCurrent() {
        return _batteryCurrent;
    }

    public void set_batteryCurrent(double _batteryCurrent) {
        this._batteryCurrent = _batteryCurrent;
    }

    public String get_currentTime() {
        return _currentTime;
    }

    public void set_currentTime(String _currentTime) {
        this._currentTime = _currentTime;
    }

    public String get_chargingStatus() {
        return _chargingStatus;
    }


}

