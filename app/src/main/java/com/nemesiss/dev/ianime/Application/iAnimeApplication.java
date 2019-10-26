package com.nemesiss.dev.ianime.Application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import com.nemesiss.dev.ianime.Services.NetworkStateReceiver;
import rx.subjects.BehaviorSubject;


public class iAnimeApplication extends MultiDexApplication {
    private static Context context;
    private NetworkStateReceiver networkStateReceiver;
    private static BehaviorSubject<Boolean> CurrentNetworkStatus;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext()
    {
        return context;
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        unregisterReceiver(networkStateReceiver);
    }
    public static BehaviorSubject<Boolean> GetCurrentNetworkStatusObservable()
    {
        return CurrentNetworkStatus;
    }
}
