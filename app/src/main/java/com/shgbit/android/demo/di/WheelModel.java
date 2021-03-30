package com.shgbit.android.demo.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author hexj
 * @createDate 2021/3/30 12:20
 **/
@Module
@InstallIn(ActivityComponent.class)
public class WheelModel {
    @Provides
    public static Wheel[] provideWheels() {
        int count = 4;
        if (count > 0) {
            Wheel[] wheels = new Wheel[count];
            for (int i = 0; i <count ; i++) {
                wheels[i] = new Wheel(i);
            }
            return wheels;
        }
        return null;
    }
}
