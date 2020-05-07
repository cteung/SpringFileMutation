package com.quote.mutation.model;

import com.quote.mutation.model.QuoteRecord;

import java.util.ArrayList;
import java.util.List;

public class ResponseV1 {

    public int recordCount;
    public List<QuoteRecord> quotes;

    public ResponseV1() {
        quotes = new ArrayList<>();
    }
}