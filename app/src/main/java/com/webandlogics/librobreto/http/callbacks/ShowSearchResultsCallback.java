package com.webandlogics.librobreto.http.callbacks;

import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.webandlogics.librobreto.adapters.BookAdapter;
import com.webandlogics.librobreto.http.entities.Book;
import com.webandlogics.librobreto.http.entities.search.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alvaro on 25/08/16.
 * This class will fill adapter with results
 */
public class ShowSearchResultsCallback implements Callback<SearchResponse> {
    private BookAdapter bookList;

    public ShowSearchResultsCallback(BookAdapter bookList){
        this.bookList = bookList;
    }

    @Override
    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
        bookList.swap(response.body().getSearch().getBooks());
//        String results = "";
//        for (Book book: response.body().getSearch().getBooks()){
//            // THIS LOOP WILL CHANGE FOR AN ADAPTER
//            results += " || " + book.getTitle();
//        }
        //bookList.setText(results.length() > 0 ? results.substring(4) : "No results");
    }

    @Override
    public void onFailure(Call<SearchResponse> call, Throwable t) {
        t.printStackTrace();
    }
}
