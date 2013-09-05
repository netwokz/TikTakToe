package com.netwokz.tiktaktoe;

import android.app.Application;
import android.content.Context;

/**
 * Created by Stephen Deane Jr. on 8/16/13.
 */
public class MyApp extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApp.context;
    }
}
