package com.quote.mutation.controller;

import com.quote.mutation.model.request.*;
import com.quote.mutation.service.MutationServices;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MutationControllerTests {

    @InjectMocks
    private MutationController controller;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void mutateV1Count() throws Exception {
        String testXml = new String(Files.readAllBytes(Paths.get("./data.xml")));
        assertThat(controller.mutateV1(testXml).getRecordCount()).isEqualTo(3);
    }

    @Test
    void mutateV1PolicyNumber() throws Exception {
        String testXml = new String(Files.readAllBytes(Paths.get("./data.xml")));
        assertThat(controller.mutateV1(testXml).getQuotes().get(0).getPolicyNumber()).isEqualTo("4843607801");
    }

    @Mock
    PersAutoLineBusiness palb;
    @Mock
    PersAutoPolicyQuoteInqRq quote;
    @Mock
    PersPolicy persPolicy;
    @Mock
    InsuredOrPrincipal iop;

    private String v2PolicyNumber = "246810";

    public void v2Stubs() {
        Mockito.when(quote.getPersPolicy()).thenReturn(persPolicy);
        Mockito.when(persPolicy.getPolicyNumber()).thenReturn(v2PolicyNumber);
        Mockito.when(quote.getInsuredOrPrincipal()).thenReturn(iop);
        Mockito.when(iop.getGeneralPartyInfo()).thenReturn("info");
        Mockito.when(quote.getPersAutoLineBusiness()).thenReturn(palb);
    }

    @Test
    void mutateV2Count() throws Exception {
        v2Stubs();
        InsuranceSvcRq isr = new InsuranceSvcRq();
        List<PersAutoPolicyQuoteInqRq> list = new ArrayList<>();
        list.add(quote);
        isr.setQuotes(list);
        assertThat(controller.mutateV2(isr).getRecordCount()).isEqualTo(1);
    }

    @Test
    void mutateV2Policy() throws Exception {
        v2Stubs();
        InsuranceSvcRq isr = new InsuranceSvcRq();
        List<PersAutoPolicyQuoteInqRq> list = new ArrayList<>();
        list.add(quote);
        isr.setQuotes(list);
        assertThat(controller.mutateV2(isr).getQuotes().get(0).getPolicyNumber()).isEqualTo(v2PolicyNumber);
    }
}
