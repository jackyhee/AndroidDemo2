package com.shgbit.android.demo.di;

import androidx.annotation.NonNull;

import javax.inject.Inject;

/**
 * @author hexj
 * @createDate 2021/3/30 10:55
 **/
public class Wheel {

    private int index;
    @Inject
    public Wheel(int index) {
        this.index = index;
    }

    @NonNull
    @Override
    public String toString() {
        return "this is Wheel "+index;
    }
}
