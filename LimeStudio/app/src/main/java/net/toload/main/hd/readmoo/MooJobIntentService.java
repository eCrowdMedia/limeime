package net.toload.main.hd.readmoo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import net.toload.main.hd.BuildConfig;

public class MooJobIntentService extends JobIntentService {
    private final static String TAG = "[MooJobIntentService]";
    public static final int JOB_ID = 0xa0;

    private IMEInstaller m_installer;
    private static final String PACKAGE_MOOINK_CHILL = "com.readmoo.mooinkneo";
    private static final String PACKAGE_MOOREADER_EINK = "com.readmoo.mooreader.eink";


    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, MooJobIntentService.class, JOB_ID, work);
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "[onCreate]");
        m_installer = new IMEInstaller(this);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "[onDestroy]");
        m_installer.release();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        String flavor = BuildConfig.FLAVOR;
        String packageName = resolvePackageName(flavor, intent);

        if (packageName == null) {
            Log.w(TAG, "no package resolved for flavor=" + flavor);
            return;
        }

        if (!isPackageInstalled(packageName)) {
            Log.w(TAG, "package not ready: " + packageName);
            return;
        }

        if (m_installer == null) {
            m_installer = new IMEInstaller(this);
        }

        try {
            m_installer.exec();
        } catch (Exception e) {
            Log.e(TAG, "installer exec failed for " + packageName, e);
        }
    }

    private String resolvePackageName(String flavor, Intent intent) {
        if (intent != null && intent.hasExtra("packageName")) {
            String fromIntent = intent.getStringExtra("packageName");
            if (fromIntent != null && !fromIntent.isEmpty()) return fromIntent;
        }

        if ("mooInkChill".equals(flavor)) return PACKAGE_MOOINK_CHILL;
        return PACKAGE_MOOREADER_EINK;
    }

    private boolean isPackageInstalled(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            pm.getApplicationInfo(packageName, PackageManager.MATCH_SYSTEM_ONLY);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
