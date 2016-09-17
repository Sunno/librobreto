package com.webandlogics.librobreto.http.callbacks;

import android.widget.TextView;

import com.webandlogics.librobreto.adapters.BookAdapter;
import com.webandlogics.librobreto.http.entities.reviews.ReviewsResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alvaro on 16/09/16.
 * TODO: parse response
 */
public class GetBooksFromShelfCallback implements Callback<ReviewsResponse> {
    BookAdapter books;

    public GetBooksFromShelfCallback(BookAdapter books){
        this.books = books;
    }
    @Override
    public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
        try {
            books.swap(response.body().getBooks());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ReviewsResponse> call, Throwable t) {

    }
}
