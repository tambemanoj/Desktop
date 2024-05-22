package reusableComponent;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class CommonUtils {

    public static boolean isTrue(Boolean bool) {
        return (bool!=null && bool);
    }

    public static OkHttpClient getHttpClient(Boolean useInternalTestProxy, Boolean ignoreSslErrors) throws Exception {
        OkHttpClient client;
        OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
        
        //to increase timeout 
        newBuilder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
        .writeTimeout(5, TimeUnit.MINUTES) // write timeout
        .readTimeout(5, TimeUnit.MINUTES); // read timeout

       

        if (isTrue(ignoreSslErrors) || isTrue(useInternalTestProxy)) {
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }
            
                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }
            
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
            };
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
            newBuilder.hostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String host, SSLSession session) {
					return true;
				}
			});
        }

        if (isTrue(useInternalTestProxy)) {
            String hostname = "localhost"/*127.0.0.1*/;
            int port = 18080;
            Proxy proxy = new Proxy(Proxy.Type.HTTP,
            new InetSocketAddress(hostname, port));
            client = newBuilder.proxy(proxy).build();
        } else {
            client = newBuilder.build();
        }
        return client;
    }

}
