package com.webandlogics.librobreto.http.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by alvaro on 25/08/16.
 *
 */
@Root(name="Request", strict=false)
public class GoodReadsRequestXML {
    @Element
    private boolean authentication;

    @Element
    String method;
}
