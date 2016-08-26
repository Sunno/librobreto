package com.webandlogics.librobreto.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by alvaro on 25/08/16.
 * Creates services according to apis
 */
public class ServiceGenerator {
    public static final String API_BASE_URL = "https://www.goodreads.com";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL);

    public static <S> S createServiceXML(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
