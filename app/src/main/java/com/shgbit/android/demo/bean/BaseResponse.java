package com.shgbit.android.demo.bean;

public class BaseResponse {
    private String result;
    private boolean success;
    private String failedMessage;
    private String failMessage;
    private String msg;
    private int errCode;
    public BaseResponse(){}

    public BaseResponse(int errCode,String msg){
        this.result = "failed";
        this.success = false;
        this.failedMessage = msg;
        this.failMessage = msg;
        this.msg = msg;
        this.errCode = errCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFailedMessage() {
        return failedMessage;
    }

    public void setFailedMessage(String failedMessage) {
        this.failedMessage = failedMessage;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }
}
