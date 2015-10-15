package nus.ise.xiaozhou.yang.batteryinfo;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by YANG_XIAOZHOU on 14/10/15.
 */
public class InfoPackageWriter {

    private static final String LOG_TAG = "InfoPackageWriter";
    private static final String _battDataFileName = "Battery_Data.txt";
    private static BattInfoPackage _myInfoPacakge = new BattInfoPackage();

    public InfoPackageWriter(BattInfoPackage myInfoPacakge) {
        set_myInfoPacakge(myInfoPacakge);
    }

    public static BattInfoPackage get_myInfoPacakge() {
        return _myInfoPacakge;
    }

    public static void set_myInfoPacakge(BattInfoPackage _myInfoPacakge) {
        InfoPackageWriter._myInfoPacakge = _myInfoPacakge;
    }

    public static String get_battDataFileName() {
        return _battDataFileName;
    }

    public void writeToBattDataFile() {
        if (isExternalStorageWritable()) {
            //Get the external storage directory for my file
            //File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

            File root = Environment.getExternalStorageDirectory();

            //Create a file using my battDataFileName under the designated file path
            File dir = new File(root.getAbsolutePath() + "/MyBattAppFile");

            if (!dir.exists()) {
                dir.mkdir();
            }

            File file = new File(dir, get_battDataFileName());

            String battInfoString = get_myInfoPacakge().infoToString();

            try {
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write(battInfoString.getBytes());
                fos.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

/*    // Get the directory for the user's public document directory and create a file there
    public File getBattDataStorageDir(String battDataFileName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), battDataFileName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }*/

    //Check whether external storage is available
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}
