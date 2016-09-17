package com.webandlogics.librobreto.http;

import com.webandlogics.librobreto.Settings;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthProvider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by alvaro on 25/08/16.
 * Creates services according to apis
 */
public class ServiceGenerator {
    final public static OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(
            Settings.API_KEY,
            Settings.API_SECRET
    );

    public static boolean alreadyFetchedAccessToken = false;

    public final static OAuthProvider provider = new DefaultOAuthProvider(
            "http://www.goodreads.com/oauth/request_token",
            "http://www.goodreads.com/oauth/access_token",
            "http://www.goodreads.com/oauth/authorize");

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Settings.API_BASE_URL);

    public static <S> S createServiceXML(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createSignedServiceXML(Class<S> serviceClass, String oauthToken, String secret) {
        consumer.setTokenWithSecret(oauthToken, secret);
        try {
            provider.retrieveAccessToken(consumer, oauthToken);
        } catch (Exception e){
            e.printStackTrace();
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer)).build();
        Retrofit retrofit = builder.client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
