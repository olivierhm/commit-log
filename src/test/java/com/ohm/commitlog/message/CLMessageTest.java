package com.ohm.commitlog.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.log4j.BasicConfigurator;  // Used by Log4j, not Logback

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;



@DisplayName("CLMessage tests")
class CLMessageTest {
	private static Logger logger;

    private final static String CID_A = "AA";
    private final static String CID_B = "BBBB";
    private final static String MSG_1 = "My first message";
    private final static String MSG_2 = "My second message";

	@BeforeAll
	static void setup() {
		//BasicConfigurator.configure();
        logger = LoggerFactory.getLogger(CLMessageTest.class);
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
	@DisplayName("Test string message creations from single factory")
	void test_string_message_creations_from_single_factory() throws Exception {
        
        // Each client has its own message factory with its specific commit ID built in.
		ICLMessageFactory<String> msgFactory = new CLMessageFactory<String>(CID_A);
        assertNotEquals(msgFactory, null);

        // Create a couple of messages
        ICLMessage<String> msg1 = msgFactory.createMessage(MSG_1);
        ICLMessage<String> msg2 = msgFactory.createMessage(MSG_2);
        
        // Check that the commit ID stays the same and the unique ID varies for each new message
        assertEquals(msg1.getCommitId(), CID_A);
        assertEquals(msg1.getCommitId(), msg2.getCommitId());
        assertNotEquals(msg1.getUniqueId(), msg2.getUniqueId());
        // Check that the message contains the proper text
        assertEquals(msg1.getData(), MSG_1);
    }
    
    @Test
	@DisplayName("Test string message creations from different factories")
	void test_string_message_creations_from_different_factories() throws Exception {
        
        // Create a message factory for each client ID
		ICLMessageFactory<String> msgFactoryA = new CLMessageFactory<String>(CID_A);
        ICLMessageFactory<String> msgFactoryB = new CLMessageFactory<String>(CID_B);

        // Create a few messages for each client ID
        ICLMessage<String> msgA1 = msgFactoryA.createMessage(String.format("%s from %s", MSG_1, CID_A));
        ICLMessage<String> msgA2 = msgFactoryA.createMessage(String.format("%s from %s", MSG_2, CID_A));
        ICLMessage<String> msgB1 = msgFactoryB.createMessage(String.format("%s from %s", MSG_1, CID_B));
        ICLMessage<String> msgB2 = msgFactoryB.createMessage(String.format("%s from %s", MSG_2, CID_B));

        logger.debug(msgB2.toString());
        
        // Check commit IDs and unique IDs
        assertEquals(msgA1.getCommitId(), CID_A);
        assertEquals(msgA2.getCommitId(), CID_A);
        assertEquals(msgB1.getCommitId(), CID_B);
        assertEquals(msgB2.getCommitId(), CID_B);       
        assertNotEquals(msgA1.getUniqueId(), msgA2.getUniqueId());
        assertNotEquals(msgB1.getUniqueId(), msgB2.getUniqueId());
	}
	
}


