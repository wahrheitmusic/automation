/*
 * Canadian Tire Corporation, Ltd. Do not reproduce without permission in writing. Copyright (c) 2014 Canadian Tire
 * Corporation, Ltd. All rights reserved.
 */

package util;

import java.util.Locale;

public final class OsCheck {
    // cached result of OS detection
    private static OSType detectedOS;

    /**
     * types of Operating Systems
     */

    private OsCheck() {
    }

    public static OSType getOperatingSystemType() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                detectedOS = OSType.MAC;
            } else if (OS.indexOf("win") >= 0) {
                detectedOS = OSType.WINDOWS;
            } else if (OS.indexOf("nux") >= 0) {
                detectedOS = OSType.LINUX;
            }
        }
        return detectedOS;
    }

    public enum OSType {
        WINDOWS, MAC, LINUX
    }
}
