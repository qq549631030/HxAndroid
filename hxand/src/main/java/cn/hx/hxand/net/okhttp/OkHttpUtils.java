package cn.hx.hxand.net.okhttp;

import android.content.Context;
import android.support.annotation.NonNull;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cn.hx.hxand.net.DefaultSSLSocketFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by huangx on 2016/6/2.
 */
public class OkHttpUtils {
    private static OkHttpClient client;

    private static Context mContext;

    public static void init(@NonNull Context context) {
        mContext = context;
    }

    public static OkHttpClient getClient() {
        if (mContext == null) {
            throw new RuntimeException("not init");
        }
        if (client == null) {
            synchronized (OkHttpUtils.class) {
                if (client == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.cookieJar(new CookieManager(mContext)); //自动存取cookie
                    try {
                        builder.sslSocketFactory(new DefaultSSLSocketFactory()); //SSLSocketFactory
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (KeyManagementException e) {
                        e.printStackTrace();
                    } catch (KeyStoreException e) {
                        e.printStackTrace();
                    }
                    builder.hostnameVerifier(new HostnameVerifier() {//跳过域名验证
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });
                    OkhttpInterceptor okhttpInterceptor = new OkhttpInterceptor();
                    builder.addInterceptor(okhttpInterceptor);

                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    builder.addInterceptor(httpLoggingInterceptor);//log最后加
                    client = builder.build();
                }
            }
        }
        return client;
    }
}
