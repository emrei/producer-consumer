package com.trading.communication.communicator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.trading.communication.communicator.impl.OneToOneCommunicator;
import com.trading.communication.model.Message;
import com.trading.communication.model.MessageQueue;
import com.trading.communication.model.Player;

/**
 * Test class for communicator test. This tests are like integration test,
 * messageQueue or other dependencies are not mocked here
 * 
 * @author YunusEmre
 *
 */
public class OneToOneCommunicatorTests {

    private OneToOneCommunicator communicator;
    private static final int MAX_MESSAGE_NUMBER = 10;
    private Player initiator;
    private Player target;
    private MessageQueue messageQueue;
    private MessageQueue responseQueue;
    private String expectedMessage;
    private String expectedResponse;

    @Before
    public void setup() {
	initiator = new Player(UUID.randomUUID(), "Morpheus", "");
	target = new Player(UUID.randomUUID(), "Neo", "");
	List<UUID> playerIdList = new ArrayList<UUID>();
	playerIdList.add(initiator.getId());
	playerIdList.add(target.getId());

	messageQueue = new MessageQueue(playerIdList, MAX_MESSAGE_NUMBER);
	responseQueue = new MessageQueue(playerIdList, MAX_MESSAGE_NUMBER);
	communicator = new OneToOneCommunicator(messageQueue, responseQueue, initiator, target, MAX_MESSAGE_NUMBER);

	expectedMessage = "----------This is your last chance.----------";
	expectedResponse = "----------This is your last chance.---------- 1";
    }

    @Test
    public void testProduceMessage() throws InterruptedException {
	Message actualMessage = communicator.produceMessage();

	assertEquals(initiator.getId(), actualMessage.getSourcePlayerId());
	assertEquals(target.getId(), actualMessage.getTargetPlayerId());
	assertEquals(expectedMessage, actualMessage.getContent());
    }

    @Test
    public void testConsumeMessage() throws InterruptedException {
	communicator.produceMessage();
	Message actualMessage = communicator.consumeMessage();
	assertEquals(initiator.getId(), actualMessage.getSourcePlayerId());
	assertEquals(target.getId(), actualMessage.getTargetPlayerId());
	assertEquals(expectedMessage, actualMessage.getContent());
    }

    @Test
    public void testSendResponse() throws InterruptedException {
	communicator.produceMessage();
	Message message = communicator.consumeMessage();
	Message response = communicator.sendResponse(message);
	assertEquals(expectedResponse, response.getContent());
    }

    @Test
    public void testReceiveResponse() throws InterruptedException {
	communicator.produceMessage();
	communicator.consumeMessage();
	communicator.sendResponse(createMessage());
	Message response = communicator.receiveResponse();
	assertEquals(expectedResponse, response.getContent());
    }

    private Message createMessage() {
	Message message = new Message(UUID.randomUUID(), initiator.getId(), target.getId());
	message.setContent("----------This is your last chance.----------");
	return message;
    }

}
