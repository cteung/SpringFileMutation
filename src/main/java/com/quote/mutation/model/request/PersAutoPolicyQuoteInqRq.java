package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PersAutoPolicyQuoteInqRq {
    @JsonProperty("InsuredOrPrincipal")
    public InsuredOrPrincipal InsuredOrPrincipal;

    @JsonProperty("PersPolicy")
    public PersPolicy PersPolicy;

    @JsonProperty("PersAutoLineBusiness")
    public List<Object> PersAutoLineBusiness;

    @Override
    public String toString()
    {
        return "PersAutoPolicyQuoteInqRq [InsuredOrPrincipal [ "+InsuredOrPrincipal+" ], PersPolicy = "+PersPolicy+", PersAutoLineBusiness = "+PersAutoLineBusiness+" ]";
    }
}
