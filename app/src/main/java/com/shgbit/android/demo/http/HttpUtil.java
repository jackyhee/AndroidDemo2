package com.shgbit.android.demo.http;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.shgbit.android.demo.bean.BaseResponse;
import com.shgbit.android.demo.utils.JsonUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    private static final String TAG = "HttpUtil";
    public static final String SecretKey = "Your Secret Key";
    public static final String SessionType = "MobileState";
    private final String mServiceUrl = "https://yourserverurl.com";
    private static HttpUtil sInstance;

    private HttpUtil() {

    }

    public static HttpUtil getInstance() {
        if (sInstance == null) {
            sInstance = new HttpUtil();
        }
        return sInstance;
    }

    public String getBaseUrl() {
        return mServiceUrl;
    }

    public <T extends BaseResponse> void executeGet(final String url, final String header, final HttpCallback<T> callback) {
        Type type = callback.getClass().getGenericInterfaces()[0];
        ParameterizedType pt = (ParameterizedType) type;
        final Type[] actualTypes = pt.getActualTypeArguments();
        GetTask getTask = new GetTask(url, (Class) actualTypes[0], null, header, callback);
        getTask.execute();
    }

    public <T extends BaseResponse> void executePost(final String url, final String json, final HttpCallback<T> callback) {
        final Type type = callback.getClass().getGenericInterfaces()[0];
        ParameterizedType pt = (ParameterizedType) type;
        final Type[] actualTypes = pt.getActualTypeArguments();
        new PostTask(url,json,(Class) actualTypes[0],callback).execute();
    }

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS).build();

    private final String TimeOut = "timeout";
    private final String NoNetwork = "no address associated with hostname";
    private final String ResponseCode = "Request exception ";

    private <T extends BaseResponse> BaseResponse httpGet(String url, String header, Class<T> type) {
        BaseResponse baseResponse = null;
        try {
            Request request;
            if (header != null) {
                request = new Request.Builder().url(url).header("X-AUTH-TOKEN", header).build();
            } else {
                request = new Request.Builder().url(url).build();
            }
            Response response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                baseResponse = JsonUtil.parseJsonToClass(result, type);
            } else {
                baseResponse = new BaseResponse(response.code(), response.body().string());
            }
        } catch (Throwable e) {
            if (HttpUtil.CaughtException(e).toLowerCase().contains(TimeOut)) {
                baseResponse = new BaseResponse(-1, TimeOut);
            } else if (HttpUtil.CaughtException(e).toLowerCase().contains(NoNetwork)) {
                baseResponse = new BaseResponse(-1, NoNetwork);
            }
        }
        return baseResponse;
    }

    private <T extends BaseResponse> BaseResponse httpPost(String url, String json, Class<T> type) {
        System.out.println("postTask,url=" + url + ",postJson=" + json);
        BaseResponse baseResponse = null;
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = mOkHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String result = response.body().string();
                baseResponse = JsonUtil.parseJsonToClass(result, type);
                baseResponse = new Gson().fromJson(result, type);
            } else {
                baseResponse = new BaseResponse(response.code(), response.body().string());
            }
        } catch (Throwable e) {
            if (HttpUtil.CaughtException(e).toLowerCase().contains(TimeOut)) {
                baseResponse = new BaseResponse(-1, TimeOut);
            } else if (HttpUtil.CaughtException(e).toLowerCase().contains(NoNetwork)) {
                baseResponse = new BaseResponse(-1, NoNetwork);
            }
        }
        return baseResponse;
    }

    private <T extends BaseResponse> BaseResponse httpPut(String url, String json, Class<T> type) {
        BaseResponse baseResponse = null;
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(url).put(body).build();
            Response response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                baseResponse = JsonUtil.parseJsonToClass(result, type);
            } else {
                baseResponse = new BaseResponse(response.code(), response.body().string());
            }
        } catch (Throwable e) {
            if (HttpUtil.CaughtException(e).toLowerCase().contains(TimeOut)) {
                baseResponse = new BaseResponse(-1, TimeOut);
            } else if (HttpUtil.CaughtException(e).toLowerCase().contains(NoNetwork)) {
                baseResponse = new BaseResponse(-1, NoNetwork);
            }
        }
        return baseResponse;
    }

    private static String CaughtException(Throwable ex) {

        String info = "";
        ByteArrayOutputStream baos = null;
        PrintStream printStream = null;

        try {
            baos = new ByteArrayOutputStream();

            printStream = new PrintStream(baos);
            ex.printStackTrace(printStream);

            byte[] data = baos.toByteArray();

            info = new String(data);
            data = null;

        } catch (Throwable e) {
            info = ex.toString();

        } finally {
            try {
                if (printStream != null) {
                    printStream.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (Throwable e) {
            }
        }

        return info;
    }

    private class PostTask extends AsyncTask<Void, Void, BaseResponse> {
        private String mUrl = "";
        private String mPostJson;
        private Class mType;
        private HttpCallback mCallback;

        public PostTask (String url, String postJson, Class type,  HttpCallback callback) {
            mUrl = url;
            mPostJson = postJson;
            mType = type;
            mCallback = callback;
        }

        @Override
        protected BaseResponse doInBackground(Void... arg0) {
            return httpPost(mUrl, mPostJson, mType);
        }

        @Override
        protected void onPostExecute(BaseResponse response) {
            super.onPostExecute(response);
            try {
                Log.i(TAG, "post type:" + mType + ",result=" + response);
                if (response != null && "success".equals(response.getResult())) {
                    mCallback.onSuccess( response);
                } else if (response != null) {
                    mCallback.onError(response.getErrCode(), response.getFailedMessage());
                }
            } catch (Throwable e) {
                Log.e(TAG, "PostTask onPostExecute Throwable:" + HttpUtil.CaughtException(e));
            }
        }
    }

    private class GetTask extends AsyncTask<Void, Void, BaseResponse> {
        private String mUrl = "";
        private Class mType;
        private Object mIntentData;
        private String mHeader;
        private HttpCallback mCallback;

        public GetTask(String url, Class type, Object intentData, String header, HttpCallback callback) {
            mUrl = url;
            mType = type;
            mIntentData = intentData;
            mHeader = header;
            mCallback = callback;
        }

        @Override
        protected BaseResponse doInBackground(Void... arg0) {
            return httpGet(mUrl, mHeader, mType);
        }

        @Override
        protected void onPostExecute(BaseResponse response) {
            super.onPostExecute(response);

            if (response != null && "success".equals(response.getResult())) {
                mCallback.onSuccess(response);
            } else if (response != null) {
                mCallback.onError(response.getErrCode(), response.getFailedMessage());
            }

        }
    }

    private class PutTask extends AsyncTask<Void, Void, BaseResponse> {
        private String mUrl = "";
        private String mPutJson;
        private Class mType;
        private HttpCallback mCallback;

        public PutTask(String url, String putJson, Class type) {
            mUrl = url;
            mPutJson = putJson;
            mType = type;
        }

        @Override
        protected BaseResponse doInBackground(Void... voids) {
            try {
                return httpPut(mUrl, mPutJson, mType);
            } catch (Throwable e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(BaseResponse response) {
            super.onPostExecute(response);
            if (response != null && "success".equals(response.getResult())) {
                mCallback.onSuccess( response);
            } else if (response != null) {
                mCallback.onError(response.getErrCode(), response.getFailedMessage());
            }
        }
    }
}

