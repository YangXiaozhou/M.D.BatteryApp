package nus.ise.xiaozhou.yang.batteryinfo;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by YANG_XIAOZHOU on 14/10/15.
 */
public class InfoPackageWriter {

    private static final String LOG_TAG = "InfoPackageWriter";
    private BattInfoPackage _myInfoPacakge = new BattInfoPackage();

    public InfoPackageWriter(BattInfoPackage myInfoPacakge) {
        this._myInfoPacakge = myInfoPacakge;
    }

    public void writeToBattDataFile() {
        File battDataFile = new File();
        battDataFile = this.getBattDataStorageDir();

    }

    // Get the directory for the user's public document directory and create a file there
    public File getBattDataStorageDir(String battDataFileName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), battDataFileName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }

    //Check whether external storage is available
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}
