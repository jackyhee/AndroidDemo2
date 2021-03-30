package com.shgbit.android.demo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ServiceFragment extends Fragment {

    private static final String TAG = "ServiceFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText editText1;
    private EditText editText2;
    private TextView tvSum;
    private Button btnStart;

    private ServiceFragmentViewModel mViewModel;
    LiveData<Integer> resultData;

    private MyService.MyBinder mBinder;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MyService.MyBinder) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBinder = null;
        }
    };

    public ServiceFragment() {
        // Required empty public constructor
    }

    public static ServiceFragment newInstance(String param1, String param2) {
        ServiceFragment fragment = new ServiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        bindService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService();
        stopService();
    }

    private void startService() {
        getActivity().startService(new Intent(getContext(),MyService.class));
    }
    private void stopService() {
        getActivity().stopService(new Intent(getContext(),MyService.class));
    }

    private void bindService() {
        getActivity().bindService(new Intent(getContext(),MyService.class),serviceConnection, Service.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        if (mBinder != null && mBinder.isBinderAlive()) {
            getActivity().unbindService(serviceConnection);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ServiceFragmentViewModel.class);
        resultData = mViewModel.getData();
        resultData.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvSum.setText(""+integer);
            }
        });

        editText1 = view.findViewById(R.id.et_1);
        editText2 = view.findViewById(R.id.et_2);
        tvSum = view.findViewById(R.id.tv_sum);
//        tvSum.setText(""+mViewModel.getData().getValue());
        btnStart = view.findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinder != null) {

                    try {
                        int param1 = Integer.parseInt(editText1.getText().toString());
                        int param2 = Integer.parseInt(editText2.getText().toString());
                        int reslt = mBinder.sum(param1,param2);
                        mViewModel.updateValue(reslt);
                    } catch (Exception e) {

                    }
                }
            }
        });

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "onViewStateRestored: ");
    }
}