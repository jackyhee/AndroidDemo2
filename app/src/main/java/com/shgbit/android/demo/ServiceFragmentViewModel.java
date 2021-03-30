package com.shgbit.android.demo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author hexj
 * @createDate 2021/3/29 14:47
 **/
public class ServiceFragmentViewModel extends ViewModel {
    private static final String TAG = "ServiceFragmentViewMode";

    private MediatorLiveData<Integer> mData = new MediatorLiveData<>();

    public LiveData<Integer> getData() {
        return mData;
    }

    public void updateValue(int value) {
        mData.postValue(value);
    }

    @Override
    protected void onCleared() {
        Log.d(TAG, "onCleared: ");
        mData = null;
    }
}
