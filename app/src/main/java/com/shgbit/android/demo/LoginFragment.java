package com.shgbit.android.demo;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {
    private EditText editText1;
    private EditText editText2;
    private Button btnStart;
    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mViewModel.getLoginResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 0) {
                    Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT).show();
                } else {
                    String errMsg = mViewModel.getErrMsg();
                    Toast.makeText(getContext(),"登录失败："+errMsg,Toast.LENGTH_SHORT).show();
                }
            }
        });
        editText1 = view.findViewById(R.id.et_1);
        editText2 = view.findViewById(R.id.et_2);
        btnStart = view.findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String username = editText1.getText().toString();
                String password = editText2.getText().toString();
                mViewModel.login(username,password);
            }
        });
    }

}