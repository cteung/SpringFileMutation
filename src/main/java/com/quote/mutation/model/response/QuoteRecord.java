package com.quote.mutation.model.response;

import java.util.ArrayList;
import java.util.List;

public class QuoteRecord {
    private String policyNumber;
    private String customerName;
    private String policyType;
    private Float totalPremium;
    private List<Vehicle> vehicles;
    private List<Driver> drivers;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public Float getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(Float totalPremium) {
        this.totalPremium = totalPremium;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public QuoteRecord() {
        vehicles = new ArrayList<>();
        drivers = new ArrayList<>();
    }
}
