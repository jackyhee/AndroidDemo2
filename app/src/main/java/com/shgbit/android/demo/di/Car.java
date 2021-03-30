package com.shgbit.android.demo.di;

import javax.inject.Inject;

/**
 * @author hexj
 * @createDate 2021/3/30 10:59
 **/
public class Car {

    Wheel[] wheels;
    @Inject
    public Car(Wheel[] wheels) {
        this.wheels = wheels;
    }

    public Wheel[] getWheels() {
        return wheels;
    }
}
