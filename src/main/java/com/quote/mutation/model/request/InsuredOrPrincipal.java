package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsuredOrPrincipal {
    @JsonProperty("ItemIdInfo")
    private Object ItemIdInfo;

    @JsonProperty("GeneralPartyInfo")
    private Object GeneralPartyInfo;

    @JsonProperty("InsuredOrPrincipalInfo")
    private Object InsuredOrPrincipalInfo;

    public Object getItemIdInfo() {
        return ItemIdInfo;
    }

    public void setItemIdInfo(Object itemIdInfo) {
        ItemIdInfo = itemIdInfo;
    }

    public Object getGeneralPartyInfo() {
        return GeneralPartyInfo;
    }

    public void setGeneralPartyInfo(Object generalPartyInfo) {
        GeneralPartyInfo = generalPartyInfo;
    }

    public Object getInsuredOrPrincipalInfo() {
        return InsuredOrPrincipalInfo;
    }

    public void setInsuredOrPrincipalInfo(Object insuredOrPrincipalInfo) {
        InsuredOrPrincipalInfo = insuredOrPrincipalInfo;
    }

    @Override
    public String toString()
    {
        return "ItemIdInfo = "+ItemIdInfo+", GeneralPartyInfo = "+GeneralPartyInfo+", InsuredOrPrincipalInfo = "+InsuredOrPrincipalInfo;
    }

}
