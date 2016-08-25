package com.webandlogics.librobreto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.webandlogics.librobreto.http.GoodReadsAPI;
import com.webandlogics.librobreto.http.entities.search.SearchResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.response_text)
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.goodreads.com")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        GoodReadsAPI goodReadsAPI = retrofit.create(GoodReadsAPI.class);

        Call<SearchResponse> searchResponseCall = goodReadsAPI.searchBook("Ender's Game");
        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                // TODO process results
                responseText.setText(response.toString());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
