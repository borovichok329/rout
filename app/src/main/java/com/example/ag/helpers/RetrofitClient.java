package com.example.ag.helpers;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit basicRetrofit = null;
    private static Retrofit bearerRetrofit = null;


    public static Retrofit getBasicAuthRetrofit() {

        if (basicRetrofit == null) {
            BasicAuthInterceptor interceptor1 = new BasicAuthInterceptor("mobileapp", "kQ5S4HUy9iOD");
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor1)
                    .build();

            basicRetrofit = new Retrofit.Builder()
                    .baseUrl("https://soctourism.asoft21.ru")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getUnsafeOkHttpClient(interceptor1))
                    .build();
        }
        return basicRetrofit;
    }

    public static Retrofit getAuthUserRetrofit() {

        if (bearerRetrofit == null) {

            bearerRetrofit = new Retrofit.Builder()
                    .baseUrl("https://soctourism.asoft21.ru")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getUnsafeOkHttpClient(null))
                    .build();
        }
        return bearerRetrofit;
    }

    private static OkHttpClient getUnsafeOkHttpClient(BasicAuthInterceptor interceptor) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            if (interceptor != null) builder.addInterceptor(interceptor);
            OkHttpClient okHttpClient = builder
                    .build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}