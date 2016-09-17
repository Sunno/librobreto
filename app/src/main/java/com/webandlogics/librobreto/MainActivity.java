package com.webandlogics.librobreto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.webandlogics.librobreto.activities.LoginActivity;
import com.webandlogics.librobreto.adapters.BookAdapter;
import com.webandlogics.librobreto.http.GoodReadsAPI;
import com.webandlogics.librobreto.http.ServiceGenerator;
import com.webandlogics.librobreto.http.callbacks.AuthUserCallback;
import com.webandlogics.librobreto.http.callbacks.CallbackForTesting;
import com.webandlogics.librobreto.http.callbacks.GetBooksFromShelfCallback;
import com.webandlogics.librobreto.http.callbacks.GetShelvesCallback;
import com.webandlogics.librobreto.http.entities.reviews.ReviewsResponse;
import com.webandlogics.librobreto.http.entities.user.AuthUserResponse;

import java.io.IOException;

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

    @BindView(R.id.book_list)
    RecyclerView bookList;

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
        }
        else {
            responseText.setText("Click load");
        }

        bookList.setHasFixedSize(true);
        bookList.setAdapter(new BookAdapter(this));
        bookList.setLayoutManager(new LinearLayoutManager(this));
        bookList.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillToken();
        if (requestToken == null){
            btnLogin.setText(R.string.login);
            btnLoad.setVisibility(View.GONE);
        }
        else {
            btnLogin.setText(R.string.logout);
            btnLoad.setVisibility(View.VISIBLE);
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
        AsyncTaskCompat.executeParallel(new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... objects) {
                SharedPreferences preferences = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE);
                String user_id = preferences.getString(Settings.USER_ID, null);
                fillToken();
                GoodReadsAPI goodReadsAPI = ServiceGenerator.createSignedServiceXML(
                        GoodReadsAPI.class,
                        requestToken,
                        tokenSecret
                );
                if (user_id == null){
                    Call<AuthUserResponse> responseBodyCall = goodReadsAPI.getUser();
                    try {
                        Response<AuthUserResponse> response= responseBodyCall.execute();
                        SharedPreferences.Editor editor = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE).edit();
                        editor.putString(Settings.USER_ID, response.body().getUser().getId());
                        editor.apply();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                final Call<ReviewsResponse> responseBodyCall = goodReadsAPI.getBooksFromShelf(user_id, "actual_shelf");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseBodyCall.enqueue(new GetBooksFromShelfCallback((BookAdapter) bookList.getAdapter()));
                    }
                });

                return null;
            }
        });
    }

    private void fillToken(){
        if (requestToken == null || tokenSecret == null){
            SharedPreferences preferences = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE);
            requestToken = preferences.getString(Settings.REQUEST_TOKEN, null);
            tokenSecret = preferences.getString(Settings.REQUEST_TOKEN_SECRET, null);
            if (requestToken != null && tokenSecret != null) {
                ServiceGenerator.consumer.setTokenWithSecret(requestToken, tokenSecret);
            }
        }

    }
}
