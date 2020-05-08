package com.quote.mutation.model.response;

import java.util.ArrayList;
import java.util.List;

public class QuoteRecord {
    public String policyNumber;
    public String customerName;
    public String policyType;
    public Float totalPremium;
    public List<Vehicle> vehicles;
    public List<Driver> drivers;

    public QuoteRecord() {
        vehicles = new ArrayList<>();
        drivers = new ArrayList<>();
    }
}
