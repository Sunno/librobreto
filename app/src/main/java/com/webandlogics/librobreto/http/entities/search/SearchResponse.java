package com.webandlogics.librobreto.http.entities.search;

import com.webandlogics.librobreto.http.entities.GoodReadsResponseXML;

import org.simpleframework.xml.Element;

/**
 * Created by alvaro on 25/08/16.
 * Response for search
 */
public class SearchResponse extends GoodReadsResponseXML {
    @Element
    private Search search;

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }
}
