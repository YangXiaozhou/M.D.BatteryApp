package nus.ise.xiaozhou.yang.batteryinfo;

import android.annotation.TargetApi;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by YANG_XIAOZHOU on 14/10/15.
 */

/*public class CurrentReader {
    public static final int PROC_SPACE_TERM = (int) ' ';
    public static final int PROC_OUT_LONG = 0x2000;
    private static final String TAG = "CurrentReader";

    private static final String CURRENT_FILE = "/sys/class/power_supply/battery/current_now";
    private static final String CURRENT_FILE_HTC_ONE = "/sys/class/power_supply/battery/batt_attr_text";
    private static final String CURRENT_FILE_HTC = "/sys/class/power_supply/battery/batt_current";
    private static final String CURRENT_FILE_GALAXY_NOTE = "/sys/class/power_supply/battery/batt_current_adc";
    private static final String CURRENT_FILE_NEXUS_10 = "/sys/class/power_supply/ds2784-fuelgauge/current_now";
    private static final String CURRENT_FILE_NEXUS_ONE = "/sys/devices/platform/ds2784-battery/getcurrent";

    private static final double CURRENT_CONV = 1e-6; // Source in microamps.
    private static final int[] READ_LONG_FORMAT = new int[]{
            PROC_SPACE_TERM | PROC_OUT_LONG
    };
    private static CurrentReader instance = null;
    String currentFile;
    double currentConv;
    private Method methodReadProcFile;
    private long[] readBuf;


    public static CurrentReader getInstance() {
        if (instance == null) {
            instance = new CurrentReader();
        }
        return instance;
    }

    public boolean hasCurrent() {
        return currentFile != null;
    }

    public double getCurrent() {

        long curr = 0;

        if (new File(CURRENT_FILE).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        } else if (new File(CURRENT_FILE_HTC_ONE).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        } else if (new File(CURRENT_FILE_GALAXY_NOTE).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        } else if (new File(CURRENT_FILE_HTC).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        } else if (new File(CURRENT_FILE_NEXUS_10).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        } else if (new File(CURRENT_FILE_NEXUS_ONE).exists()) {
            currentFile = CURRENT_FILE;
            currentConv = CURRENT_CONV;
        }


        if (hasCurrent()) {
            curr = readLongFromFile(currentFile);
        }

        //return curr == -1 ? -1.0 : 0.11;
        return curr == -1 ? -1.0 : currentConv * curr;
    }

    public long readLongFromFile(String file) {
        if (methodReadProcFile == null) return -1;
        try {
            if ((Boolean) methodReadProcFile.invoke(
                    null, file, READ_LONG_FORMAT, null, readBuf, null)) {
                return readBuf[0];
            }
        } catch (IllegalAccessException e) {
            Log.w(TAG, "Failed to get pid cpu usage");
        } catch (InvocationTargetException e) {
            Log.w(TAG, "Exception thrown while getting pid cpu usage");
        }
        return -1L;
    }


}*/

/*
 *  Copyright (c) 2010-2013 Ran Manor
 *
 *  This file is part of CurrentWidget.
 *
 * 	CurrentWidget is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  CurrentWidget is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CurrentWidget.  If not, see <http://www.gnu.org/licenses/>.
 */


import java.util.Locale;

import android.os.Build;

public class CurrentReader {

    static final String BUILD_MODEL = Build.MODEL.toLowerCase(Locale.ENGLISH);

    public CurrentReader() {
    }

    public double getValue() {
        File f = null;

        // Galaxy S3
        if (CurrentReader.BUILD_MODEL.contains("gt-i9300")
                || CurrentReader.BUILD_MODEL.contains("gt-i9300T")
                || CurrentReader.BUILD_MODEL.contains("gt-i9305")
                || CurrentReader.BUILD_MODEL.contains("gt-i9305N")
                || CurrentReader.BUILD_MODEL.contains("gt-i9305T")
                || CurrentReader.BUILD_MODEL.contains("shv-e210k")
                || CurrentReader.BUILD_MODEL.contains("shv-e210l")
                || CurrentReader.BUILD_MODEL.contains("shv-e210s")
                || CurrentReader.BUILD_MODEL.contains("sgh-t999")
                || CurrentReader.BUILD_MODEL.contains("sgh-t999l")
                || CurrentReader.BUILD_MODEL.contains("sgh-t999v")
                || CurrentReader.BUILD_MODEL.contains("sgh-i747")
                || CurrentReader.BUILD_MODEL.contains("sgh-i747m")
                || CurrentReader.BUILD_MODEL.contains("sgh-n064")
                || CurrentReader.BUILD_MODEL.contains("sc-06d")
                || CurrentReader.BUILD_MODEL.contains("sgh-n035")
                || CurrentReader.BUILD_MODEL.contains("sc-03e")
                || CurrentReader.BUILD_MODEL.contains("SCH-j021")
                || CurrentReader.BUILD_MODEL.contains("scl21")
                || CurrentReader.BUILD_MODEL.contains("sch-r530")
                || CurrentReader.BUILD_MODEL.contains("sch-i535")
                || CurrentReader.BUILD_MODEL.contains("sch-S960l")
                || CurrentReader.BUILD_MODEL.contains("gt-i9308")
                || CurrentReader.BUILD_MODEL.contains("sch-i939")
                || CurrentReader.BUILD_MODEL.contains("sch-s968c")) {
            f = new File("/sys/class/power_supply/battery/current_max");
            if (f.exists()) {
                return OneLineReader.getValue(f, false);
            }
        }

        if (CurrentReader.BUILD_MODEL.contains("nexus 7")
                || CurrentReader.BUILD_MODEL.contains("one")
                || CurrentReader.BUILD_MODEL.contains("lg-d851")) {
            f = new File("/sys/class/power_supply/battery/current_now");
            if (f.exists()) {
                return OneLineReader.getValue(f, false);
            }
        }

        if (CurrentReader.BUILD_MODEL.contains("sl930")) {
            f = new File("/sys/class/power_supply/da9052-bat/current_avg");
            if (f.exists()) {
                return OneLineReader.getValue(f, false);
            }
        }

        // Galaxy S4
        if (CurrentReader.BUILD_MODEL.contains("sgh-i337")
                || CurrentReader.BUILD_MODEL.contains("gt-i9505")
                || CurrentReader.BUILD_MODEL.contains("gt-i9500")
                || CurrentReader.BUILD_MODEL.contains("sch-i545")
                || CurrentReader.BUILD_MODEL.contains("find 5")
                || CurrentReader.BUILD_MODEL.contains("sgh-m919")
                || CurrentReader.BUILD_MODEL.contains("sgh-i537")) {
            f = new File("/sys/class/power_supply/battery/current_now");
            if (f.exists()) {
                return OneLineReader.getValue(f, false);
            }
        }

        if (CurrentReader.BUILD_MODEL.contains("cynus")) {
            f = new File(
                    "/sys/devices/platform/mt6329-battery/FG_Battery_CurrentConsumption");
            if (f.exists()) {
                return OneLineReader.getValue(f, false);
            }
        }
        // Zopo Zp900, etc.
        if (CurrentReader.BUILD_MODEL.contains("zp900")
                || CurrentReader.BUILD_MODEL.contains("jy-g3")
                || CurrentReader.BUILD_MODEL.contains("zp800")
                || CurrentReader.BUILD_MODEL.contains("zp800h")
                || CurrentReader.BUILD_MODEL.contains("zp810")
                || CurrentReader.BUILD_MODEL.contains("w100")
                || CurrentReader.BUILD_MODEL.contains("zte v987")) {
            f = new File(
                    "/sys/class/power_supply/battery/BatteryAverageCurrent");
            if (f.exists()) {
                return OneLineReader.getValue(f, false);
            }
        }

        // Samsung Galaxy Tab 2
        if (CurrentReader.BUILD_MODEL.contains("gt-p31")
                || CurrentReader.BUILD_MODEL.contains("gt-p51")) {
            f = new File("/sys/class/power_supply/battery/current_avg");
            if (f.exists()) {
                return OneLineReader.getValue(f, false);
            }
        }

        // HTC One X
/*        if (CurrentReader.BUILD_MODEL.contains("htc one x")) {
            f = new File("/sys/class/power_supply/battery/batt_attr_text");
            if (f.exists()) {
                Long value = BattAttrTextReader.getValue(f, "I_MBAT", "I_MBAT");
                if (value != null)
                    return value;
            }
        }*/

        // wildfire S
/*        if (CurrentReader.BUILD_MODEL.contains("wildfire s")) {
            f = new File("/sys/class/power_supply/battery/smem_text");
            if (f.exists()) {
                Long value = BattAttrTextReader.getValue(f, "eval_current",
                        "batt_current");
                if (value != null)
                    return value;
            }
        }*/

        // trimuph with cm7, lg ls670, galaxy s3, galaxy note 2
        if (CurrentReader.BUILD_MODEL.contains("triumph")
                || CurrentReader.BUILD_MODEL.contains("ls670")
                || CurrentReader.BUILD_MODEL.contains("gt-i9300")
                || CurrentReader.BUILD_MODEL.contains("sm-n9005")
                || CurrentReader.BUILD_MODEL.contains("gt-n7100")
                || CurrentReader.BUILD_MODEL.contains("sgh-i317")) {
            f = new File("/sys/class/power_supply/battery/current_now");
            if (f.exists()) {
                return OneLineReader.getValue(f, false);
            }
        }

        // htc desire hd / desire z / inspire?
        // htc evo view tablet
        if (CurrentReader.BUILD_MODEL.contains("desire hd")
                || CurrentReader.BUILD_MODEL.contains("desire z")
                || CurrentReader.BUILD_MODEL.contains("inspire")
                || CurrentReader.BUILD_MODEL.contains("pg41200")) {
            f = new File("/sys/class/power_supply/battery/batt_current");
            if (f.exists()) {
                return OneLineReader.getValue(f, false);
            }
        }

        // nexus one cyangoenmod
        f = new File("/sys/devices/platform/ds2784-battery/getcurrent");
        if (f.exists()) {
            return OneLineReader.getValue(f, true);
        }

        // sony ericsson xperia x1
        f = new File(
                "/sys/devices/platform/i2c-adapter/i2c-0/0-0036/power_supply/ds2746-battery/current_now");
        if (f.exists()) {
            return OneLineReader.getValue(f, false);
        }

        // xdandroid
        /* if (Build.MODEL.equalsIgnoreCase("MSM")) { */
        f = new File(
                "/sys/devices/platform/i2c-adapter/i2c-0/0-0036/power_supply/battery/current_now");
        if (f.exists()) {
            return OneLineReader.getValue(f, false);
        }
		/* } */

        // droid eris
/*        f = new File("/sys/class/power_supply/battery/smem_text");
        if (f.exists()) {
            Long value = SMemTextReader.getValue();
            if (value != null)
                return value;
        }*/

        // htc sensation / evo 3d
/*        f = new File("/sys/class/power_supply/battery/batt_attr_text");
        if (f.exists()) {
            Long value = BattAttrTextReader.getValue(f,
                    "batt_discharge_current", "batt_current");
            if (value != null)
                return value;
        }*/

        // some htc devices
        f = new File("/sys/class/power_supply/battery/batt_current");
        if (f.exists()) {
            return OneLineReader.getValue(f, false);
        }

        // Nexus One.
        // TODO: Make this not default but specific for N1 because of the normalization.
        f = new File("/sys/class/power_supply/battery/current_now");
        if (f.exists()) {
            return OneLineReader.getValue(f, true);
        }

        // samsung galaxy vibrant
        f = new File("/sys/class/power_supply/battery/batt_chg_current");
        if (f.exists())
            return OneLineReader.getValue(f, false);

        // sony ericsson x10
        f = new File("/sys/class/power_supply/battery/charger_current");
        if (f.exists())
            return OneLineReader.getValue(f, false);

        // Nook Color
        f = new File("/sys/class/power_supply/max17042-0/current_now");
        if (f.exists())
            return OneLineReader.getValue(f, false);

        // Xperia Arc
        f = new File("/sys/class/power_supply/bq27520/current_now");
        if (f.exists())
            return OneLineReader.getValue(f, true);

        // Motorola Atrix
        f = new File(
                "/sys/devices/platform/cpcap_battery/power_supply/usb/current_now");
        if (f.exists())
            return OneLineReader.getValue(f, false);

        // Acer Iconia Tab A500
        f = new File("/sys/EcControl/BatCurrent");
        if (f.exists())
            return OneLineReader.getValue(f, false);

        // charge current only, Samsung Note
        f = new File("/sys/class/power_supply/battery/batt_current_now");
        if (f.exists())
            return OneLineReader.getValue(f, false);

        // galaxy note, galaxy s2
        f = new File("/sys/class/power_supply/battery/batt_current_adc");
        if (f.exists())
            return OneLineReader.getValue(f, false);

        // intel
        f = new File("/sys/class/power_supply/max170xx_battery/current_now");
        if (f.exists())
            return OneLineReader.getValue(f, true);

        // Sony Xperia U
        f = new File("/sys/class/power_supply/ab8500_fg/current_now");
        if (f.exists())
            return OneLineReader.getValue(f, true);

        f = new File("/sys/class/power_supply/android-battery/current_now");
        if (f.exists()) {
            return OneLineReader.getValue(f, false);
        }

        // Nexus 10, 4.4.
        f = new File("/sys/class/power_supply/ds2784-fuelgauge/current_now");
        if (f.exists()) {
            return OneLineReader.getValue(f, true);
        }

        f = new File("/sys/class/power_supply/Battery/current_now");
        if (f.exists()) {
            return OneLineReader.getValue(f, false);
        }

        return 0;
    }
}
