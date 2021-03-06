package com.webandlogics.librobreto.http.callbacks;

import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alvaro on 16/09/16.
 * TODO: parse response
 */
public class GetShelvesCallback implements Callback<ResponseBody> {
    TextView responseText;

    public GetShelvesCallback(TextView responseText){
        this.responseText = responseText;
    }
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            responseText.setText(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }
}
