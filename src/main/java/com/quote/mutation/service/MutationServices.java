package com.quote.mutation.service;

import com.quote.mutation.model.Driver;
import com.quote.mutation.model.Vehicle;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MutationServices {

    // XML Element tags
    public static String quoteRecordXpath = "//PersAutoPolicyQuoteInqRq";
    public static String policyNumber = "PolicyNumber";
    public static String customerName = "CommercialName";
    public static String policyType = "LOBCd";
    public static String totalPremium = "Amt";

    private static String vehicle = "PersVeh";
    private static String make = "Manufacturer";
    private static String model = "Model";
    private static String year = "ModelYear";
    private static String driver = "PersDriver";
    private static String driverName = "CommercialName";
    private static String birthDay = "BirthDt";

    public static List<Vehicle> getVehicles(Element item) {
        List<Vehicle> Vehicles = new ArrayList<>();

        int VehicleCount = item.getElementsByTagName(vehicle).getLength();
        for (int i = 0; i < VehicleCount; i++) {
            Vehicle v = new Vehicle();
            v.make = getElementText(item, make, i);
            v.model = getElementText(item, model, i);
            v.year = getElementText(item, year, i);
            Vehicles.add(v);
        }

        return Vehicles;
    }

    public static List<Driver> getDrivers(Element item) {
        List<Driver> Drivers = new ArrayList<>();

        int DriverCount = item.getElementsByTagName(driver).getLength();

        for (int i = 0; i < DriverCount; i++) {
            Driver d = new Driver();
            Element e = (Element) item.getElementsByTagName(driver).item(i);
            d.driverName = getElementText(e, driverName, 0);
            d.age = calculateAge(getElementText(e, birthDay, 0));
            Drivers.add(d);
        }
        return Drivers;
    }

    public static String getElementText(Element item, String tag, int index) {
        NodeList e = item.getElementsByTagName(tag);
        if (e.getLength() > 0) {
            return e.item(index).getTextContent();
        }
        return "";
    }

    private static int calculateAge(String bd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = sdf.parse(bd);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        LocalDate l1 = LocalDate.of(year, month, date);
        LocalDate now1 = LocalDate.now();
        Period diff1 = Period.between(l1, now1);

        return diff1.getYears();
    }
}
