package com.webandlogics.librobreto.http.entities.search;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by alvaro on 25/08/16.
 * Actually it's just a Search Result
 */

@Root(strict=false)
public class Work {

    @Element(name="id")
    private long id;

    @Element(name="books_count")
    private int booksCount;

    @Element(name="ratings_count")
    private int rating_count;
}
