package com.quote.mutation.service;

import com.quote.mutation.model.request.PersDriver;
import com.quote.mutation.model.request.PersVeh;
import com.quote.mutation.model.response.Driver;
import com.quote.mutation.model.response.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Component
public class MutationServices {

    private static final Logger logger = LoggerFactory.getLogger(MutationServices.class);

    // XML Element tags
    public static final String quoteRecordXpath = "//PersAutoPolicyQuoteInqRq";
    public static final String policyNumber = "PolicyNumber";
    public static final String nameInfo = "NameInfo";
    public static final String customerName = "CommercialName";
    public static final String policyType = "LOBCd";
    public static final String totalPremium = "Amt";

    private static final String vehicle = "PersVeh";
    private static final String make = "Manufacturer";
    private static final String model = "Model";
    private static final String year = "ModelYear";
    private static final String driver = "PersDriver";
    private static final String driverName = "CommercialName";
    public static final String personInfo = "PersonInfo";
    public static final String birthDay = "BirthDt";

    public static List<Vehicle> getVehicles(Element item) {
        List<Vehicle> Vehicles = new ArrayList<>();

        int VehicleCount = item.getElementsByTagName(vehicle).getLength();
        for (int i = 0; i < VehicleCount; i++) {
            Vehicle v = new Vehicle();
            v.setMake(getElementText(item, make, i));
            v.setModel(getElementText(item, model, i));
            v.setYear(getElementText(item, year, i));
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
            d.setDriverName(getElementText(e, driverName, 0));
            d.setAge(calculateAge(getElementText(e, birthDay, 0)));
            Drivers.add(d);
        }
        return Drivers;
    }

    // Utility Method
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
            logger.error("Date format is incorrect, returning age as -1");
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

    //V2 Methods
    public static String getTagValue(String object, String tag) {
        if(object.contains(tag)) {
            return object.split(tag)[1].substring(1).split("}|,")[0];
        }
        return "";
    }

    public static List<Driver> getDriversV2 (List<PersDriver> PersDriver) {
        List<Driver> Drivers = new ArrayList<>();
        for (int i = 0; i < PersDriver.size(); i++) {
            Drivers.add(getDriverV2(PersDriver.get(i)));
        }
        return Drivers;
    }

    private static Driver getDriverV2(PersDriver pd) {
        Driver d = new Driver();
        d.setDriverName(pd.getCommercialName());
        d.setAge(calculateAge(pd.getBirthDt()));
        return d;
    }

    public static List<Vehicle> getVehiclesV2 (List<PersVeh> PersVeh) {
        List<Vehicle> Vehicles = new ArrayList<>();
        for (int i = 0; i < PersVeh.size(); i++) {
            Vehicles.add(getVehicleV2(PersVeh.get(i)));
        }
        return Vehicles;
    }

    private static Vehicle getVehicleV2(PersVeh pv) {
        Vehicle v = new Vehicle();
        v.setMake(pv.getManufacturer());
        v.setModel(pv.getModel());
        v.setYear(pv.getModelYear());
        return v;
    }
}
