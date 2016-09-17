package com.webandlogics.librobreto.http.callbacks;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import com.webandlogics.librobreto.Settings;
import com.webandlogics.librobreto.http.entities.user.AuthUserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alvaro on 16/09/16.
 * api/auth_user callback
 */
public class AuthUserCallback implements Callback<AuthUserResponse> {
    Context context;

    public AuthUserCallback(Context context){
        this.context = context;
    }
    @Override
    public void onResponse(Call<AuthUserResponse> call, Response<AuthUserResponse> response) {
        try {
            //responseText.setText("Id: " + response.body().getUser().getId());
            SharedPreferences.Editor editor = context.getSharedPreferences(Settings.SHARED_PREFERENCES, Context.MODE_PRIVATE).edit();
            editor.putString(Settings.USER_ID, response.body().getUser().getId());
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Toast.makeText(context, "Error: " + response.errorBody().string(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<AuthUserResponse> call, Throwable t) {

    }
}
