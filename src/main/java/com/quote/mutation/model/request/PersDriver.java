package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quote.mutation.service.MutationServices;

import java.util.Map;

public class PersDriver {

    private static MutationServices ms;

    private String CommercialName;

    private String BirthDt;

    @JsonProperty("GeneralPartyInfo")
    private void unpackCommercialName(Map<String, Object> GeneralPartyInfo) {
        if (GeneralPartyInfo.get(ms.nameInfo).toString().contains(ms.customerName)) {
            this.CommercialName = ms.getTagValue(GeneralPartyInfo.get(ms.nameInfo).toString(), ms.customerName);
        } else {
            this.CommercialName = null;
        }
    }

    @JsonProperty("DriverInfo")
    private void unpackBirthDt(Map<String, Object> DriverInfo) {
        if (DriverInfo.get(ms.personInfo).toString().contains(ms.birthDay)) {
            this.BirthDt = ms.getTagValue(DriverInfo.get(ms.personInfo).toString(), ms.birthDay);
        } else {
            this.BirthDt = null;
        }
    }

    public String getCommercialName() {
        return CommercialName;
    }

    public String getBirthDt() {
        return BirthDt;
    }

    @Override
    public String toString() {
        return "[ CommercialName = " + CommercialName + ", BirthDt = " + BirthDt + "]";
    }

}
