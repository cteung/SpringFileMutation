package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InsuranceSvcRq {

    @JsonProperty("InsuranceSvcRq")
    private List<PersAutoPolicyQuoteInqRq> quotes;

    public List<PersAutoPolicyQuoteInqRq> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<PersAutoPolicyQuoteInqRq> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString()
    {
        return "InsuranceSvcRq "+quotes;
    }
}