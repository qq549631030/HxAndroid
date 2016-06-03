package cn.hx.hxand.net.volley;

import com.android.volley.toolbox.HurlStack;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by huangx on 2016/6/3.
 */
public class HttpsHurlStack extends HurlStack {
    public HttpsHurlStack(UrlRewriter urlRewriter, SSLSocketFactory sslSocketFactory) {
        super(urlRewriter, sslSocketFactory);
    }

    public HttpsHurlStack(UrlRewriter urlRewriter) {
        super(urlRewriter);
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) super.createConnection(url);
        connection.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return connection;
    }
}
