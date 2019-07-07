package com.trading.communication.communicator.impl;

import java.util.UUID;

import com.trading.communication.communicator.Communicator;
import com.trading.communication.model.Message;
import com.trading.communication.model.MessageQueue;
import com.trading.communication.model.Player;

/**
 * one to one communication implementation
 * In this implementation there is only one initiator and one target player who are communicating with each other
 * @author YunusEmre
 *
 */
public class OneToOneCommunicator implements Communicator {
    private final MessageQueue messageQueue;
    private final MessageQueue responseQueue;
    private final Player initiator;
    private final Player target;
    private final int maxMessageNumber;
    private int messageNumber;

    public OneToOneCommunicator(MessageQueue messageQueue, MessageQueue responseQueue, Player initiator, Player target,
	    int maxMessageNumber) {
	this.messageQueue = messageQueue;
	this.responseQueue = responseQueue;
	this.initiator = initiator;
	this.target = target;
	this.maxMessageNumber = maxMessageNumber;
	messageNumber = 0;
    }

    @Override
    public Message produceMessage() throws InterruptedException {
	Message message = createMessage();
	messageQueue.putMessage(target.getId(), message);
	System.out.println("Message Sent by: " + initiator.getName() + " " + message.getContent());
	return message;

    }

    @Override
    public Message consumeMessage() throws InterruptedException {
	Message message = messageQueue.getMessage(target.getId());
	messageNumber++;
	Thread.sleep(1000);
	System.out.println("***** Message Received by: " + target.getName() + " " + message.getContent());
	return message;
    }

    @Override
    public Message receiveResponse() throws InterruptedException {
	Message response = responseQueue.getMessage(initiator.getId());
	Thread.sleep(2000);
	System.out.println("***** Response received from: " + initiator.getName() + " " + response.getContent());
	return response;

    }

    @Override
    public Message sendResponse(Message message) throws InterruptedException {
	Message response = createResponse(message);
	responseQueue.putMessage(response.getSourcePlayerId(), response);
	System.out.println("Response sent by: " + target.getName() + " " + response.getContent());
	return response;

    }

    private Message createMessage() {
	Message message = new Message(UUID.randomUUID(), initiator.getId(), target.getId());
	message.setContent("----------This is your last chance.----------");
	return message;
    }

    private Message createResponse(Message message) {
	Message response = new Message(UUID.randomUUID(), message.getSourcePlayerId(), target.getId());
	response.setContent(message.getContent() + " " + messageNumber);
	return response;
    }

    @Override
    public boolean isRunning() {
	return messageNumber < maxMessageNumber;
    }

}
