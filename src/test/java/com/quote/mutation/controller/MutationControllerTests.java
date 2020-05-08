package com.quote.mutation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MutationControllerTests {

    @Autowired
    private MutationController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void mutateV1Count() throws Exception {
        Data data = new Data();
        assertThat(controller.mutateV1(data.input).recordCount).isEqualTo(3);
    }

    @Test
    void mutateV1PolicyNumber() throws Exception {
        Data data = new Data();
        assertThat(controller.mutateV1(data.input).quotes.get(0).policyNumber).isEqualTo("4843607801");
    }
}
