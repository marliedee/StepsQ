package org.pursuit.stepsq.model;

import java.util.List;

public class QuotesContent {

    private List<Quotes> quotes;

    public QuotesContent(List<Quotes> quotes) {
        this.quotes = quotes;
    }

    public List<Quotes> getQuotes() {
        return quotes;
    }
}
