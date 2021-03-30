package com.shgbit.android.demo;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.shgbit.android.demo.bean.Res_Login;
import com.shgbit.android.demo.http.HttpCallback;
import com.shgbit.android.demo.http.HttpUtil;
import com.shgbit.android.demo.http.tool.GBUE1;

import static com.shgbit.android.demo.http.HttpUtil.SecretKey;
import static com.shgbit.android.demo.http.HttpUtil.SessionType;

public class LoginViewModel extends ViewModel {

    private String sessionId;
    private String errMsg;
    MediatorLiveData<Integer> loginResult = new MediatorLiveData<>();

    public LiveData<Integer> getLoginResult() {
        return loginResult;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void login(String username, String pwd) {
        String url = HttpUtil.getInstance().getBaseUrl() + "/login";
        JsonObject jObject = new JsonObject();
        jObject.addProperty("userName", username);
        jObject.addProperty("password", pwd);
        String secrString=  GBUE1.encode(jObject.toString(), SecretKey);

        JsonObject object = new JsonObject();
        object.addProperty("data", secrString);
        object.addProperty("sessionType", SessionType);

        HttpUtil.getInstance().executePost(url, object.toString(), new HttpCallback<Res_Login>() {
            @Override
            public void onSuccess(Res_Login response) {
                sessionId = ((Res_Login) response).getSessionId();
                loginResult.postValue(0);
            }

            @Override
            public void onError(int errCode, String errMsg) {
                setErrMsg(errMsg);
                loginResult.postValue(1);
            }
        });
    }
}