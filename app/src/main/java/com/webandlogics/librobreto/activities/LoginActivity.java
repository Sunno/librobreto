package com.webandlogics.librobreto.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;
import android.widget.Button;

import com.webandlogics.librobreto.MainActivity;
import com.webandlogics.librobreto.R;
import com.webandlogics.librobreto.Settings;

import butterknife.ButterKnife;
import butterknife.OnClick;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

/**
 * Created by alvaro on 12/09/16.
 * Call login from app
 */
public class LoginActivity extends Activity {
    final private static OAuthConsumer consumer = new DefaultOAuthConsumer(
            Settings.API_KEY,
            Settings.API_SECRET
    );
    private final static OAuthProvider provider = new DefaultOAuthProvider(
            "http://www.goodreads.com/oauth/request_token",
            "http://www.goodreads.com/oauth/access_token",
            "http://www.goodreads.com/oauth/authorize");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        SharedPreferences preferences = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE);
        String oauthToken = preferences.getString(Settings.OAUTH_TOKEN, null);
        if (oauthToken != null){
            finish();
        }
    }

    @OnClick(R.id.btn_login)
    public void onClickBtnLogin(Button btnLogin){
        AsyncTaskCompat.executeParallel(new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... objects) {
                try {
                    // Getting authorization URI
                    String authUrl = provider.retrieveRequestToken(
                            consumer, Settings.OAUTH_REDIRECT_URI);
                    authUrl += "&mobile=1";
                    Log.i(Settings.TAG, "Auth URL: " + authUrl);
                    SharedPreferences preferences = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(Settings.REQUEST_TOKEN, consumer.getToken());
                    editor.putString(Settings.REQUEST_TOKEN_SECRET, consumer.getTokenSecret());
                    editor.apply();
                    Intent intent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(authUrl)
                    );
                    Intent chooser = Intent.createChooser(
                            intent, getResources().getString(R.string.choose_your_browser));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(chooser);
                    }
                } catch (OAuthException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri data = getIntent().getData();
        Log.i(Settings.TAG, "data: " + data);
        if (data != null && data.toString().startsWith(Settings.OAUTH_REDIRECT_URI)){
            // we process the response
            String authorize = data.getQueryParameter("authorize");
            if (authorize.equals("1")){
                String oauthToken = data.getQueryParameter("oauth_token");
                SharedPreferences.Editor editor = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(Settings.OAUTH_TOKEN, oauthToken);
                editor.apply();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
