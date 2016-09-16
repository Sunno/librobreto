package com.webandlogics.librobreto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.webandlogics.librobreto.activities.LoginActivity;
import com.webandlogics.librobreto.http.GoodReadsAPI;
import com.webandlogics.librobreto.http.ServiceGenerator;
import com.webandlogics.librobreto.http.callbacks.CallbackForTesting;
import com.webandlogics.librobreto.http.callbacks.GetBooksFromShelfCallback;
import com.webandlogics.librobreto.http.callbacks.GetShelvesCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.response_text)
    TextView responseText;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_load)
    Button btnLoad;

    String requestToken;
    String tokenSecret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SharedPreferences preferences = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE);
        requestToken = preferences.getString(Settings.REQUEST_TOKEN, null);
        tokenSecret = preferences.getString(Settings.REQUEST_TOKEN_SECRET, null);
        if (requestToken == null){
            responseText.setText("Nothing to show");
            btnLoad.setVisibility(View.GONE);
        }
        else {
            btnLoad.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE);
        requestToken = preferences.getString(Settings.REQUEST_TOKEN, null);
        tokenSecret = preferences.getString(Settings.REQUEST_TOKEN_SECRET, null);
        if (requestToken == null){
            btnLogin.setText(R.string.login);
        }
        else {
            btnLogin.setText(R.string.logout);
        }
    }

    @OnClick(R.id.btn_login)
    public void onClickBtnLogin(){
        if (requestToken == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else {
            SharedPreferences.Editor editor = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE).edit();
            editor.remove(Settings.REQUEST_TOKEN);
            editor.remove(Settings.REQUEST_TOKEN_SECRET);
            editor.apply();
            btnLogin.setText(R.string.login);
            requestToken = null;
            btnLoad.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_load)
    public void onClickBtnLoad(){
        GoodReadsAPI goodReadsAPI = ServiceGenerator.createSignedServiceXML(
                GoodReadsAPI.class,
                requestToken,
                tokenSecret
        );

        Call<ResponseBody> responseBodyCall = goodReadsAPI.getBooksFromShelf("12651336", "actual_shelf");
        //Call<ResponseBody> responseBodyCall = goodReadsAPI.getUser();
        responseBodyCall.enqueue(new CallbackForTesting(responseText));
    }
}
