package com.trading.communication.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class MessageQueueTests {
    
    private MessageQueue messageQueue;
    private Player player1;
    private Player player2;
    List<UUID> players;
    private final int maxMessageSize = 10; 
    
    @Before
    public void setup() {
	player1 = new Player(UUID.randomUUID(), "Ron", "");
	player2 = new Player(UUID.randomUUID(), "Harry", "");
	players = new ArrayList<UUID>();
	players.add(player1.getId());
	players.add(player2.getId());
	messageQueue = new MessageQueue(players, maxMessageSize);
    }

    @Test
    public void testPutAndGetMessage_Success() throws InterruptedException {
	Message expectedMessage = createMessage();
	messageQueue.putMessage(player1.getId(), createMessage());
	Message message = messageQueue.getMessage(player1.getId());
	assertEquals(expectedMessage.getContent(), message.getContent());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetMessage_Error() throws InterruptedException {
	messageQueue.getMessage(UUID.randomUUID());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPutMessage_Error() throws InterruptedException {
	messageQueue.putMessage(UUID.randomUUID(), createMessage());
    }
    
    private Message createMessage() {
	Message message = new Message(UUID.randomUUID(), player1.getId(), player2.getId());
	message.setContent("----------This is your last chance.----------");
	return message;
    }
}
