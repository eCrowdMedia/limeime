package net.toload.main.hd.readmoo;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;


public class MooService extends IntentService {
    private static final String TAG = "[MooService]";

    private IMEInstaller m_installer;

    public MooService() {
        super("[MooService]");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        m_installer = new IMEInstaller(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        m_installer.release();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // if screen lock is on, HAVE TO wait util system fully ready
        PackageManager pm = getPackageManager();
        String packageName = "com.readmoo.mooreader.eink";
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                pm.getApplicationInfo(packageName,
                        PackageManager.MATCH_SYSTEM_ONLY);
            } else {
                PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_SHARED_LIBRARY_FILES);
                int uid = packageInfo.applicationInfo.uid;
//                String sharedUserId = packageInfo.sharedUserId;
                if (uid != 1000) {
                    Log.e(TAG, packageName + " is not system app, not allowed to install ime files");
                    return;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "may not ready yet ?");
            e.printStackTrace();
            return;
        }

        m_installer.exec();
    }
}
