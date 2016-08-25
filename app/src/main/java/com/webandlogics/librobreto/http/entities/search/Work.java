package com.webandlogics.librobreto.http.entities.search;

import com.webandlogics.librobreto.http.entities.Book;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by alvaro on 25/08/16.
 * Actually it's just a Search Result
 */

@Root(name="work", strict=false)
public class Work {

    @Element
    private long id;

    @Element(name="books_count")
    private int booksCount;

    @Element(name="ratings_count")
    private int rating_count;

    @Element(name="best_book")
    private Book bestBook;

    public Book getBestBook() {
        return bestBook;
    }

    public void setBestBook(Book bestBook) {
        this.bestBook = bestBook;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public void setBooksCount(int booksCount) {
        this.booksCount = booksCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }
}
