package com.quote.mutation.controller;

import com.quote.mutation.Employee;
import com.quote.mutation.model.QuoteRecord;
import com.quote.mutation.model.InsuranceSvcRq;
import com.quote.mutation.model.ResponseV1;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

import static com.quote.mutation.service.MutationServices.*;

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

        XPathExpression expr = xpath.compile(quoteRecordXpath);
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        res.recordCount = nodes.getLength();

        for (int i = 0; i < nodes.getLength(); i++) {
            QuoteRecord record = new QuoteRecord();
            Element item = (Element) nodes.item(i);
            record.policyNumber = getElementText(item, policyNumber, 0);
            record.customerName = getElementText(item, customerName, 0);
            record.policyType = getElementText(item, policyType, 0);
            record.totalPremium = Float.parseFloat(getElementText(item, totalPremium,0));
            record.vehicles = getVehicles(item);
            record.drivers = getDrivers(item);
            res.quotes.add(record);
        }

        return res;
    }

}