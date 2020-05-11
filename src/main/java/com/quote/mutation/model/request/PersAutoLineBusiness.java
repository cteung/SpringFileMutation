package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class PersAutoLineBusiness {

    @JsonProperty("PersDriver")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PersDriver> PersDriver;

    @JsonProperty("PersVeh")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PersVeh> PersVeh;

    public List<PersDriver> getPersDriver() {
        return PersDriver;
    }

    public void setPersDriver(List<PersDriver> persDriver) {
        PersDriver = persDriver;
    }

    public List<PersVeh> getPersVeh() {
        return PersVeh;
    }

    public void setPersVeh(List<PersVeh> persVeh) {
        PersVeh = persVeh;
    }

    @Override
    public String toString()
    {
        return "PersDriver "+PersDriver+", PersVeh = "+PersVeh;
    }

}
