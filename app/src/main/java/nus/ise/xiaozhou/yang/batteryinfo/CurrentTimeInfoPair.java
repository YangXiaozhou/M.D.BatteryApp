package nus.ise.xiaozhou.yang.batteryinfo;

import java.util.Date;

/**
 * Created by YANG_XIAOZHOU on 29/10/15.
 */
public class CurrentTimeInfoPair {

    private int _remPer;
    private String _time;
    private Date _date;
    private String _dayOfTheWeek;

    public CurrentTimeInfoPair(int _remPer, Date _date) {
        this._remPer = _remPer;
        this._date = _date;
        String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", _date);//Thursday
        set_dayOfTheWeek(dayOfTheWeek);
    }


    public int get_remPer() {
        return _remPer;
    }

    public void set_remPer(int _remPer) {
        this._remPer = _remPer;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }

    public String get_dayOfTheWeek() {
        return _dayOfTheWeek;
    }

    public void set_dayOfTheWeek(String _dayOfTheWeek) {
        this._dayOfTheWeek = _dayOfTheWeek;
    }
}
