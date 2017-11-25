package vn.magik.atmsach.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class DataResponse<T> {

    @SerializedName("message")
    private String message;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("data")
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T  getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
