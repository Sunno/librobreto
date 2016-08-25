package com.webandlogics.librobreto.http.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by alvaro on 25/08/16.
 * XML structure for good read response
 */
@Root(name="GoodreadsResponse", strict=false)
public class GoodReadsResponseXML {
    @Element(name="Request")
    private GoodReadsRequestXML request;

    public GoodReadsRequestXML getRequest() {
        return request;
    }

    public void setRequest(GoodReadsRequestXML request) {
        this.request = request;
    }
}
