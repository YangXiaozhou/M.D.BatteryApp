/*
package nus.ise.xiaozhou.yang.batteryinfo;

*/
/**
 * Created by YANG_XIAOZHOU on 3/10/15.
 *//*


import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.provider.BaseColumns;

public class myDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "batteryInfo.db";
    public static final String TABLE_BATTERY_INFO = "batteryInfo";
    public static final String COLUMN_REMAININGPERCENTAGE = "_remainingpercentage";
    public static final String COLUMN_BATTERYTEMPERATURE = "_batterytemperature";
    public static final String COLUMN_BATTERYVOLTAGE = "_batteryvoltage";
    public static final String COLUMN_CHARGING = "_charging";

    public class Columns {

        public static final String _ID = BaseColumns._ID;
        public static final String _REMAININGPERCENTAGE = "remainingpercentage";
        public static final String _BATTERYTEMPERATURE = "batterytemperature";
        public static final String _BATTERYVOLTAGE = "batteryvoltage";
        public static final String _CHARGING = "chargingstatus";
    }

    public myDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_BATTERY_INFO + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_REMAININGPERCENTAGE + " INTEGER, " +
                COLUMN_BATTERYTEMPERATURE + " INTEGER, " +
                COLUMN_BATTERYVOLTAGE + " INTEGER, " +
                COLUMN_CHARGING + " INTEGER " +
                ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_BATTERY_INFO);
        onCreate(db);

    }

    //Add a new row to database
    public void addBatteryInfo(BattInfoPackage infoPackage) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.clear();

        values.put(Columns._REMAININGPERCENTAGE, infoPackage.get_remainingPercentage());
        db.insert(COLUMN_REMAININGPERCENTAGE, null, values);

        values.put(Columns._BATTERYTEMPERATURE, infoPackage.get_batteryTemperature());
        db.insert(COLUMN_BATTERYTEMPERATURE, null, values);

        values.put(Columns._BATTERYVOLTAGE, infoPackage.get_batteryVoltage());
        db.insert(COLUMN_BATTERYVOLTAGE, null, values);

        values.put(Columns._CHARGING, infoPackage.get_chargingStatus());
        db.insert(COLUMN_CHARGING, null, values);

        db.close();

    }

    //Print out the database as a string
    public String databaseToString() {
        String databaseString = "";
        String battInfoString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BATTERY_INFO + "WHERE 1 ";

        //Create a cursor, have it point to a location in your results
        Cursor cs = db.rawQuery(query, null);

        //Move the cursor to the first row
        cs.moveToFirst();

        while (!cs.isAfterLast()) {
            if (cs.getString(cs.getColumnIndex("_remainingpercentage")) != null) {
                battInfoString = cs.getString(cs.getColumnIndex("_remainingpercentage")) + " " +
                        cs.getString(cs.getColumnIndex("_batterytemperature")) + " " +
                        cs.getString(cs.getColumnIndex("_batteryvoltage")) + " " +
                        cs.getString(cs.getColumnIndex("_charging"));

                databaseString += battInfoString;
                databaseString += "\n";

            }
        }

        cs.close();
        db.close();
        return databaseString;

    }
}
*/
