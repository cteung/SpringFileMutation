package com.quote.mutation.controller;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

import com.quote.mutation.*;
import com.quote.mutation.model.Driver;
import com.quote.mutation.model.QuoteRecord;
import com.quote.mutation.model.ResponseV1;
import com.quote.mutation.model.Vehicle;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@RestController
public class MutationController {

    @PostMapping(
        value = "/v1/mutate",
        consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE }, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseV1 mutateV1(@RequestBody String xml) throws Exception {
        ResponseV1 res = new ResponseV1();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        XPathExpression expr = xpath.compile("//PersAutoPolicyQuoteInqRq");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        res.recordCount = nodes.getLength();

        for (int i = 0; i < nodes.getLength(); i++) {
            QuoteRecord record = new QuoteRecord();
            Element item = (Element) nodes.item(i);
            record.policyNumber = getElementText(item, "PolicyNumber", 0);
            record.customerName = getElementText(item, "CommercialName", 0);
            record.policyType = getElementText(item, "LOBCd", 0);
            record.totalPremium = Float.parseFloat(getElementText(item, "Amt",0));
            record.vehicles = getVehicles(item);
            record.drivers = getDrivers(item);
            res.quotes.add(record);
        }

        return res;
    }

    private List<Vehicle> getVehicles(Element item) {
        List<Vehicle> Vehicles = new ArrayList<>();

        int VehicleCount = item.getElementsByTagName("PersVeh").getLength();
        for (int i = 0; i < VehicleCount; i++) {
            Vehicle v = new Vehicle();
            v.make = getElementText(item,"Manufacturer", i);
            v.model = getElementText(item, "Model", i);
            v.year = getElementText(item, "ModelYear", i);
            Vehicles.add(v);
        }

        return Vehicles;
    }

    private List<Driver> getDrivers(Element item) {
        List<Driver> Drivers = new ArrayList<>();

        int DriverCount = item.getElementsByTagName("PersDriver").getLength();

        for (int i = 0; i < DriverCount; i++) {
            Driver d = new Driver();
            Element e = (Element) item.getElementsByTagName("PersDriver").item(i);
            d.driverName = getElementText(e, "CommercialName", 0);
            d.age = calculateAge(getElementText(e, "BirthDt", 0));
            Drivers.add(d);
        }
        return Drivers;
    }

    private String getElementText(Element item, String tag, int index) {
        NodeList e = item.getElementsByTagName(tag);
        if (e.getLength() > 0) {
            return e.item(index).getTextContent();
        }
        return "";
    }

    private int calculateAge(String bd) {
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