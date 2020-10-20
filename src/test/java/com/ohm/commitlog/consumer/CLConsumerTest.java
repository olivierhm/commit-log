package com.ohm.commitlog.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.log4j.BasicConfigurator;  // Used by Log4J, not Logback

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;


// TODO - implement consumer side tests.  Mock Kafka consumer interface.

@DisplayName("CLConsumer tests")
class CLConsumerTest {
	private static Logger logger;

    @BeforeAll
	static void setup() {
		//BasicConfigurator.configure();
        logger = LoggerFactory.getLogger(CLConsumerTest.class);
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
	@DisplayName("Sample consumer test")
	void test_stub() throws Exception {
        assertTrue(true);
	}
	
}


