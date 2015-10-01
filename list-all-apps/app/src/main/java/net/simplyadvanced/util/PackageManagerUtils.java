package net.simplyadvanced.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

/**
 * Static helper methods for to be used instead of accessing the PackageManager API directly.
 * This class is a singleton so that retrieving the list of all installed apps only has to
 * be done once, which may take a "long" time depending on the number of apps installed on
 * the device.
 */
public final class PackageManagerUtils {
    private static void log(String message) {
        Log.d("DEBUG: PackageManagerUtils", message);
    }



    private PackageManager mPackageManager;

    private static volatile PackageManagerUtils mPackageManagerUtilsInstance;

    private static volatile List<PackageInfo> mPackagesList;

    private static Context mAppContext;

    /** This lock helps ensure that only one instance of this class is created. */
    private static final Object mInstanceLock = new Object();

    /** Use `getInstance()` to instantiate this class.
     *
     * @param appContext any context (application context will be used)
     */
    private PackageManagerUtils(Context appContext) {
        mAppContext = appContext;
        mPackageManager = appContext.getPackageManager();
        mPackagesList = getListOfAllInstalledApps();
    }

    /** Returns a singleton instance of this class.
     *
     * @param context any context (application context will be used)
     */
    public static PackageManagerUtils getInstance(Context context) {
        if (mPackageManagerUtilsInstance == null) {
            synchronized(mInstanceLock) {
                if (mPackageManagerUtilsInstance == null) {
                    mPackageManagerUtilsInstance = new PackageManagerUtils(context.getApplicationContext());
                }
            }
        }
        return mPackageManagerUtilsInstance;
    }

    /**
     * Returns a list of all apps that are installed on a device, including disabled ones.
     */
    public List<PackageInfo> getListOfAllInstalledApps() {
        if (mPackagesList == null) {
            mPackagesList = getListOfAllInstalledAppsRefresh();
        }
        return mPackagesList;
    }

    /** Returns a list of all apps that are installed on a device, including disabled ones.
     *
     * Note: This has the potential to take a long time depending on the amount of apps that
     * are installed on the device. It might be a good idea to call this method in a
     * separate thread, off of the UI thread.
     */
    public List<PackageInfo> getListOfAllInstalledAppsRefresh() {
        PackageManager pm = mAppContext.getPackageManager();

        // This first version might be better without the disabled components because possibly
        // nothing can be done with the disabled
        List<PackageInfo> packages = pm.getInstalledPackages(
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
//        List<PackageInfo> packages = pm.getInstalledPackages(
//                PackageManager.GET_DISABLED_COMPONENTS);
        return packages;
    }

}
