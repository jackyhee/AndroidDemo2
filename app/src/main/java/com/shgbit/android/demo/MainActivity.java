package com.shgbit.android.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    MainFragment mainFragment;
    Fragment currFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: "+savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            String lastFragId = savedInstanceState.getString("currfrag");
            Log.d(TAG, "onRestoreInstanceState: "+lastFragId);
            Fragment lastFrag = getSupportFragmentManager().findFragmentByTag(lastFragId);
            if (lastFrag != null) {
                changeFragment(lastFrag);

            }
        } else {
            mainFragment = new MainFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frlt_frag_main, mainFragment).commit();
        }
    }

    public void changeFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            transaction.show(fragment).commit();
        } else {
            transaction.add(R.id.frlt_frag_main, fragment,fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        }
        currFragment = fragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (currFragment != null) {
            Log.d(TAG, "onSaveInstanceState: "+currFragment.getTag());
            outState.putString("currfrag",currFragment.getTag());
            currFragment = null;
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}