package com.net;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BotInternetBankApplicationTests {

    @Autowired
    private SampleService sampleService; // Example service to test

    @Test
    void contextLoads() {
        // This test ensures that the Spring application context loads successfully.
    }

    @Test
    void testSampleService() {
        // Verify that the sample service is not null and behaves as expected.
        assertThat(sampleService).isNotNull();
        String result = sampleService.performOperation();
        assertThat(result).isEqualTo("Operation Successful");
    }
}
