package com.quote.mutation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersVeh {

    @JsonProperty("Manufacturer")
    private String Manufacturer;

    @JsonProperty("Model")
    private String Model;

    @JsonProperty("ModelYear")
    private String ModelYear;

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getModelYear() {
        return ModelYear;
    }

    public void setModelYear(String modelYear) {
        ModelYear = modelYear;
    }

    @Override
    public String toString() {
        return "[ Manufacturer " + Manufacturer + " , Model = " + Model + " , ModelYear = " + ModelYear +" ]";

    }
}
