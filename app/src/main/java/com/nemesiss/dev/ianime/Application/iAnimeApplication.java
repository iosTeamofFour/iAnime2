package com.nemesiss.dev.ianime.Application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import com.nemesiss.dev.ianime.Services.NetworkStateReceiver;
import com.nemesiss.dev.ianime.Services.UserServices;
import rx.subjects.BehaviorSubject;


public class iAnimeApplication extends MultiDexApplication {
    private static Context context;
    private static iAnimeApplication application;
    // Services

    public UserServices _UserServices;

    @Override
    public void onCreate()
    {
        super.onCreate();
        application = this;
        context = getApplicationContext();
        _UserServices = new UserServices();
    }

    public static iAnimeApplication getApplication() {
        return application;
    }
    public static Context getContext()
    {
        return context;
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
    }
}
