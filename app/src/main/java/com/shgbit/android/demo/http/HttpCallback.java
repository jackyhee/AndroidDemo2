package com.shgbit.android.demo.http;

import com.shgbit.android.demo.bean.BaseResponse;

public interface HttpCallback<T extends BaseResponse> {
    public void onSuccess(T response);
    public void onError(int errCode, String errMsg);


}
