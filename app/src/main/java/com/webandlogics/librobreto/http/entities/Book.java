package com.webandlogics.librobreto.http.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by alvaro on 25/08/16.
 * General class for book
 */
@Root(name="best_book", strict=false)
public class Book {

    @Element
    private long id;

    @Element
    private String title;

    @Element(name="image_url")
    private String imageURL;

    @Element(name="small_image_url")
    private String smallImageURL;

    @ElementList
    private List<Author> authors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
