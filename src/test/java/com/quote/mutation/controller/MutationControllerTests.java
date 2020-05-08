package com.quote.mutation.controller;

import com.quote.mutation.model.request.InsuranceSvcRq;
import com.quote.mutation.model.request.InsuredOrPrincipal;
import com.quote.mutation.model.request.PersAutoPolicyQuoteInqRq;
import com.quote.mutation.model.request.PersPolicy;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

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
        Data data = new Data();
        assertThat(controller.mutateV1(data.input).getRecordCount()).isEqualTo(3);
    }

    @Test
    void mutateV1PolicyNumber() throws Exception {
        Data data = new Data();
        assertThat(controller.mutateV1(data.input).getQuotes().get(0).getPolicyNumber()).isEqualTo("4843607801");
    }

    @Mock
    PersAutoPolicyQuoteInqRq quote;
    @Mock
    PersPolicy persPolicy;
    @Mock
    InsuredOrPrincipal iop;

    @Test
    void mutateV2Count() throws Exception {
        Data data = new Data();
        InsuranceSvcRq isr = new InsuranceSvcRq();
        List<PersAutoPolicyQuoteInqRq> list = new ArrayList<>();
        list.add(quote);
        Mockito.when(quote.getPersPolicy()).thenReturn(persPolicy);
        Mockito.when(persPolicy.getPolicyNumber()).thenReturn("100");
        Mockito.when(quote.getInsuredOrPrincipal()).thenReturn(iop);
        Mockito.when(iop.getGeneralPartyInfo()).thenReturn("abc");
        isr.setQuotes(list);
        assertThat(controller.mutateV2(isr).getRecordCount()).isEqualTo(1);
    }
}
