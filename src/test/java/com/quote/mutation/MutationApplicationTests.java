package com.quote.mutation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MutationApplicationTests {

	@Autowired
	private MutationApplication app;

	@Test
	void contextLoads() {
		String[] args = {};
		app.main(args);
		assertThat(app).isNotNull();
	}

}
