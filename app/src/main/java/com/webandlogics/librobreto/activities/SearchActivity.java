package com.webandlogics.librobreto.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.webandlogics.librobreto.R;
import com.webandlogics.librobreto.adapters.BookAdapter;
import com.webandlogics.librobreto.http.GoodReadsAPI;
import com.webandlogics.librobreto.http.ServiceGenerator;
import com.webandlogics.librobreto.http.callbacks.ShowSearchResultsCallback;
import com.webandlogics.librobreto.http.entities.search.SearchResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.book_list)
    RecyclerView bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookList.setHasFixedSize(true);
        bookList.setAdapter(new BookAdapter(this));
        bookList.setLayoutManager(new LinearLayoutManager(this));
        bookList.setNestedScrollingEnabled(false); //this is for smooth scrolling
        //Search
        GoodReadsAPI goodReadsAPI = ServiceGenerator.createServiceXML(GoodReadsAPI.class);

        Call<SearchResponse> searchResponseCall = goodReadsAPI.searchBook("Ender's Game");
        searchResponseCall.enqueue(new ShowSearchResultsCallback((BookAdapter) bookList.getAdapter()));
    }
}
