package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class PersAutoPolicyQuoteInqRq {

    @JsonProperty("InsuredOrPrincipal")
    private InsuredOrPrincipal InsuredOrPrincipal;

    @JsonProperty("PersPolicy")
    private PersPolicy PersPolicy;

    @JsonProperty("PersAutoLineBusiness")
    @JacksonXmlElementWrapper(useWrapping = false)
    private PersAutoLineBusiness PersAutoLineBusiness;

    public com.quote.mutation.model.request.InsuredOrPrincipal getInsuredOrPrincipal() {
        return InsuredOrPrincipal;
    }

    public void setInsuredOrPrincipal(com.quote.mutation.model.request.InsuredOrPrincipal insuredOrPrincipal) {
        InsuredOrPrincipal = insuredOrPrincipal;
    }

    public com.quote.mutation.model.request.PersPolicy getPersPolicy() {
        return PersPolicy;
    }

    public void setPersPolicy(com.quote.mutation.model.request.PersPolicy persPolicy) {
        PersPolicy = persPolicy;
    }

    public PersAutoLineBusiness getPersAutoLineBusiness() {
        return PersAutoLineBusiness;
    }

    public void setPersAutoLineBusiness(PersAutoLineBusiness persAutoLineBusiness) {
        PersAutoLineBusiness = persAutoLineBusiness;
    }

    @Override
    public String toString()
    {
        return "PersAutoPolicyQuoteInqRq [InsuredOrPrincipal [ "+InsuredOrPrincipal+" ], PersPolicy = "+PersPolicy+", PersAutoLineBusiness = "+PersAutoLineBusiness+" ]";
    }
}
