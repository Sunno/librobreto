package com.webandlogics.librobreto.http.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by alvaro on 25/08/16.
 * General Author class
 */
@Root(strict=false)
public class Author {
    @Element
    private long id;

    @Element
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
