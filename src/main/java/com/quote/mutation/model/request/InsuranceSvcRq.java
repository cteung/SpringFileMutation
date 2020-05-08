package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InsuranceSvcRq {

    @JsonProperty("InsuranceSvcRq")
    public List<PersAutoPolicyQuoteInqRq> quotes;

    @Override
    public String toString()
    {
        return "InsuranceSvcRq "+quotes;
    }
}