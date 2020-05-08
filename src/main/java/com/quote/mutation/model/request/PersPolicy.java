package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class PersPolicy {

    @JsonProperty("PolicyNumber")
    private String PolicyNumber;

    @JsonProperty("LOBCd")
    private String LOBCd;

    @JsonProperty("CurrentTermAmt")
    private Map<String, Float> CurrentTermAmt;


    public String getPolicyNumber() {
        return PolicyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        PolicyNumber = policyNumber;
    }

    public String getLOBCd() {
        return LOBCd;
    }

    public void setLOBCd(String LOBCd) {
        this.LOBCd = LOBCd;
    }

    public Map<String, Float> getCurrentTermAmt() {
        return CurrentTermAmt;
    }

    public void setCurrentTermAmt(Map<String, Float> currentTermAmt) {
        CurrentTermAmt = currentTermAmt;
    }

    @Override
    public String toString()
    {
        return "PersPolicy [PolicyNumber = "+PolicyNumber+", LOBCd = "+LOBCd+", CurrentTermAmt = [ Amt = "+CurrentTermAmt.get("Amt")+"]]";
    }
}
