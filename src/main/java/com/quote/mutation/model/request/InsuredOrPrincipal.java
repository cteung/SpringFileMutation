package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsuredOrPrincipal {
    @JsonProperty("ItemIdInfo")
    public Object ItemIdInfo;

    @JsonProperty("GeneralPartyInfo")
    public Object GeneralPartyInfo;

    @JsonProperty("InsuredOrPrincipalInfo")
    public Object InsuredOrPrincipalInfo;

    @Override
    public String toString()
    {
        return "ItemIdInfo = "+ItemIdInfo+", GeneralPartyInfo = "+GeneralPartyInfo+", InsuredOrPrincipalInfo = "+InsuredOrPrincipalInfo;
    }

}
