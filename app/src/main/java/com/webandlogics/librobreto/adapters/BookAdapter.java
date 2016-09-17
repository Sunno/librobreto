package com.webandlogics.librobreto.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.webandlogics.librobreto.R;
import com.webandlogics.librobreto.http.entities.Author;
import com.webandlogics.librobreto.http.entities.Book;

import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alvaro on 25/08/16.
 *
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> books;
    private Context context;

    public BookAdapter(Context context, List<Book> books){
        this.context = context;
        this.books = books;
    }

    public BookAdapter(Context context){
        this.context = context;
        this.books = new Vector<>();
    }

    public void swap(List<Book> books){
        this.books = books;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.tvTitle.setText(book.getTitle());
        String authors = "";
        for (Author author: book.getAuthors()){
            authors += ", " + author.getName();
        }
        holder.tvAuthor.setText(
                authors.length() > 0 ?
                        authors.substring(1).trim() :
                        holder.tvAuthor.getContext().getResources().getText(R.string.unknown_author));

        Glide.with(context).load(book.getImageURL()).into(holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.tv_author) public TextView tvAuthor;
        @BindView(R.id.tv_title) public TextView tvTitle;
        @BindView(R.id.iv_thumbnail) public ImageView ivThumbnail;
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
