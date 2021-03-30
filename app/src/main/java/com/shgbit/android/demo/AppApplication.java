package com.shgbit.android.demo;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * @author hexj
 * @createDate 2021/3/30 9:53
 **/
@HiltAndroidApp
public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
