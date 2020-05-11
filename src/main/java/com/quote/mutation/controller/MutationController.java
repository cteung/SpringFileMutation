package com.quote.mutation.controller;

import com.quote.mutation.model.request.PersAutoPolicyQuoteInqRq;
import com.quote.mutation.model.response.QuoteRecord;
import com.quote.mutation.model.request.InsuranceSvcRq;
import com.quote.mutation.model.response.Response;
import com.quote.mutation.service.MutationServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


@RestController
public class MutationController {

    private static final Logger logger = LoggerFactory.getLogger(MutationController.class);

    private final MutationServices ms;

    public MutationController (MutationServices ms) {
        this.ms = ms;
    }

    @PostMapping(
        value = "/v1/mutate",
        consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE }, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Response mutateV1(@RequestBody String xml) throws Exception {

        logger.info("Executing /v1/mutate");

        Response res = new Response();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        XPathExpression expr = xpath.compile(ms.quoteRecordXpath);
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        res.setRecordCount(nodes.getLength());

        for (int i = 0; i < nodes.getLength(); i++) {
            QuoteRecord record = new QuoteRecord();
            Element item = (Element) nodes.item(i);
            record.setPolicyNumber(ms.getElementText(item, ms.policyNumber, 0));
            record.setCustomerName(ms.getElementText(item, ms.customerName, 0));
            record.setPolicyType(ms.getElementText(item, ms.policyType, 0));
            record.setTotalPremium(Float.parseFloat(ms.getElementText(item, ms.totalPremium,0)));
            record.setVehicles(ms.getVehicles(item));
            record.setDrivers(ms.getDrivers(item));

            res.getQuotes().add(record);
        }

        return res;
    }

    @PostMapping(
            value = "/v2/mutate",
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE },
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Response mutateV2(@RequestBody InsuranceSvcRq xml) {

        logger.info("Executing /v2/mutate");

        Response res = new Response();
        res.setRecordCount(xml.getQuotes().size());

        for (int i = 0; i < xml.getQuotes().size(); i++) {
            QuoteRecord record = new QuoteRecord();
            PersAutoPolicyQuoteInqRq quote = xml.getQuotes().get(i);
            record.setPolicyNumber(quote.getPersPolicy().getPolicyNumber());
            record.setCustomerName(ms.getTagValue(quote.getInsuredOrPrincipal().getGeneralPartyInfo().toString(), ms.customerName));
            record.setPolicyType(quote.getPersPolicy().getLOBCd());
            record.setTotalPremium(quote.getPersPolicy().getCurrentTermAmt().get(ms.totalPremium));
            record.setVehicles(ms.getVehiclesV2(quote.getPersAutoLineBusiness().getPersVeh()));
            record.setDrivers(ms.getDriversV2(quote.getPersAutoLineBusiness().getPersDriver()));
            res.getQuotes().add(record);
        }
        return res;
    }
}