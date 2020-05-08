package com.quote.mutation.model.response;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private int recordCount;
    private List<QuoteRecord> quotes;

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<QuoteRecord> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<QuoteRecord> quotes) {
        this.quotes = quotes;
    }

    public Response() {
        quotes = new ArrayList<>();
    }
}