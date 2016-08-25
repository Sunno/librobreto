package com.webandlogics.librobreto.http.entities.search;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by alvaro on 25/08/16.
 * List of searchResults
 */
@Root(strict=false)
public class Search {
    @ElementList
    private List<Work> results;

    @Element(name="results-start")
    private int resultsStart;

    @Element(name="results-end")
    private int resultsEnd;

    @Element(name="total-results")
    private int totalResults;

    @Element
    private String query;

    public List<Work> getResults() {
        return results;
    }

    public void setResults(List<Work> results) {
        this.results = results;
    }

    public int getResultsEnd() {
        return resultsEnd;
    }

    public void setResultsEnd(int resultsEnd) {
        this.resultsEnd = resultsEnd;
    }

    public int getResultsStart() {
        return resultsStart;
    }

    public void setResultsStart(int resultsStart) {
        this.resultsStart = resultsStart;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
