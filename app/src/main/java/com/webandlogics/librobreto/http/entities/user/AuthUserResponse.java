package com.webandlogics.librobreto.http.entities.user;

import com.webandlogics.librobreto.http.entities.GoodReadsResponseXML;
import com.webandlogics.librobreto.http.entities.search.Search;

import org.simpleframework.xml.Element;

/**
 * Created by alvaro on 25/08/16.
 * Response for search
 */
public class AuthUserResponse extends GoodReadsResponseXML {
    @Element
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
