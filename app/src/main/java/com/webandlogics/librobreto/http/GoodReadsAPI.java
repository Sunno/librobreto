package com.webandlogics.librobreto.http;

import com.webandlogics.librobreto.BuildConfig;
import com.webandlogics.librobreto.Settings;
import com.webandlogics.librobreto.http.entities.search.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alvaro on 25/08/16.
 * Retrofit interface for searching into goodreads api
 */
public interface GoodReadsAPI {

    final static String Q3M_P6_LBIRFX_CFB_XDL4W = Settings.API_KEY;

    @GET("search/index.xml?key=" + Q3M_P6_LBIRFX_CFB_XDL4W)
    Call<SearchResponse> searchBook(@Query("q") String q);
}
