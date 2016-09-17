package com.webandlogics.librobreto.http.entities.reviews;

import com.webandlogics.librobreto.http.entities.Book;
import com.webandlogics.librobreto.http.entities.GoodReadsResponseXML;
import com.webandlogics.librobreto.http.entities.search.Search;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by alvaro on 25/08/16.
 * Response for search
 */
public class ReviewsResponse extends GoodReadsResponseXML {
    @ElementList
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
