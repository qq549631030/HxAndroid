package cn.hx.hxand.net.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huangx on 2016/6/2.
 */
public class OkhttpInterceptor implements Interceptor {
    private static final String TAG = "OkhttpInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Response response = chain.proceed(original);
        int retryCount = 0;
        while (!response.isSuccessful() && retryCount < 3) {//失败重连
            retryCount++;
            Log.d(TAG, "intercept: request not successful retry " + retryCount);
            response = chain.proceed(original);
        }
        return response;
    }
}
