package cn.hx.hxand.net.okhttp;

import android.content.Context;
import android.util.Log;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by huangx on 2016/5/25.
 */
public class CookieManager implements CookieJar {
    private static final String TAG = "CookieManager";

    private Context context;
    private final PersistentOkHttpCookieStore cookieStore;

    public CookieManager(Context context) {
        this.context = context;
        cookieStore = new PersistentOkHttpCookieStore(context);
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie cookie : cookies) {
                Log.d(TAG, "saveCookie: " + cookie);
                cookieStore.add(url, cookie);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        for (Cookie cookie : cookies) {
            Log.d(TAG, "loadCookie: " + cookie);
        }
        return cookies;
    }
}
