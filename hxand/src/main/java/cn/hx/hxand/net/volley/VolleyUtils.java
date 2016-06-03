package cn.hx.hxand.net.volley;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import cn.hx.hxand.net.DefaultSSLSocketFactory;
import cn.hx.hxand.net.PersistentHttpCookieStore;

/**
 * Created by huangx on 2016/6/3.
 */
public class VolleyUtils {

    private static Context mContext;

    private static RequestQueue mRequestQueue;

    public static void init(@NonNull Context context) {
        mContext = context;
    }

    public static synchronized RequestQueue getRequestQueue() {
        if (mContext == null) {
            throw new RuntimeException("not init");
        }
        if (mRequestQueue == null) {
            synchronized (VolleyUtils.class) {
                if (mRequestQueue == null) {
                    HurlStack stack = null;
                    try {
                        CookieManager cookieManager = new CookieManager(new PersistentHttpCookieStore(mContext), null);
                        CookieHandler.setDefault(cookieManager);
                        stack = new HttpsHurlStack(null, new DefaultSSLSocketFactory());
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (KeyManagementException e) {
                        e.printStackTrace();
                    } catch (KeyStoreException e) {
                        e.printStackTrace();
                    }
                    mRequestQueue = Volley.newRequestQueue(mContext, stack);
                }
            }
        }
        return mRequestQueue;
    }
}
