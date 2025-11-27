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
        // 判斷要抓哪個 package
        String packageName;
        String flavor = BuildConfig.FLAVOR;

        if ("mooInkChill".equals(flavor)) {
            packageName = "com.readmoo.mooinkneo.app";
        } else {
            packageName = "com.readmoo.mooreader.eink";
        }

        PackageManager pm = getPackageManager();
        try {
            pm.getApplicationInfo(packageName, PackageManager.MATCH_SYSTEM_ONLY);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "may not ready yet for " + packageName);
            e.printStackTrace();
            return;
        }

        m_installer.exec();
    }
}
