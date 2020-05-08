package com.quote.mutation.model.response;

import java.util.ArrayList;
import java.util.List;

public class Response {

    public int recordCount;
    public List<QuoteRecord> quotes;

    public Response() {
        quotes = new ArrayList<>();
    }
}