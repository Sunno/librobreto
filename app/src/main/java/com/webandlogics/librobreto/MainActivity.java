package com.webandlogics.librobreto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.webandlogics.librobreto.activities.LoginActivity;
import com.webandlogics.librobreto.activities.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.response_text)
    TextView responseText;

    @BindView(R.id.btn_login)
    Button btnLogin;

    String oauthToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE);
        oauthToken = preferences.getString(Settings.OAUTH_TOKEN, null);
        if (oauthToken == null){
            btnLogin.setText(R.string.login);
        }
        else {
            btnLogin.setText(R.string.logout);
        }
    }

    @OnClick(R.id.btn_login)
    public void onClickBtnLogin(){
        if (oauthToken == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else {
            SharedPreferences.Editor editor = getSharedPreferences(Settings.SHARED_PREFERENCES, MODE_PRIVATE).edit();
            editor.remove(Settings.OAUTH_TOKEN);
            editor.apply();
            btnLogin.setText(R.string.login);
            oauthToken = null;
        }
    }
}
