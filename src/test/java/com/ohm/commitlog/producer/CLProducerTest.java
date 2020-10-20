package com.ohm.commitlog.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.log4j.BasicConfigurator;  // Used by Log4j, not Logback

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;


// TODO - implement producer side tests.  Mock Kafka responses.

@DisplayName("CLConsumer tests")
class CLProducerTest {
	private static Logger logger;

    @BeforeAll
	static void setup() {
		//BasicConfigurator.configure();
        logger = LoggerFactory.getLogger(CLProducerTest.class);
	}
	
	@BeforeEach
	void beforeEachTest(TestInfo testInfo) {
		logger.info("Starting \"{}\"", testInfo.getDisplayName());
	}
	
	@AfterEach
	protected void afterEachTest() {
		System.out.println("");
	}
    
    @Test
	@DisplayName("Sample producer test")
	void test_stub() throws Exception {
        assertTrue(true);
	}
	
}


