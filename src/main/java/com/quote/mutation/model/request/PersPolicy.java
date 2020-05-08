package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class PersPolicy {
    @JsonProperty("PolicyNumber")
    public String PolicyNumber;

    @JsonProperty("LOBCd")
    public String LOBCd;

    @JsonProperty("CurrentTermAmt")
    public Map<String, Float> CurrentTermAmt;

    @Override
    public String toString()
    {
        return "PersPolicy [PolicyNumber = "+PolicyNumber+", LOBCd = "+LOBCd+", CurrentTermAmt = [ Amt = "+CurrentTermAmt.get("Amt")+"]]";
    }
}
