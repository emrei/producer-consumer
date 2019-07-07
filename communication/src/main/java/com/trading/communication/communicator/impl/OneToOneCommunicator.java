package com.trading.communication.communicator.impl;

import java.util.HashMap;
import java.util.Map;
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
    private final Player initiator;
    private final Player target;
    private final int maxMessageNumber;
    private final Map<UUID, Player> playerMap = new HashMap<UUID, Player>();
    private final Map<UUID, Integer> messageCountMap = new HashMap<UUID, Integer>();

    public OneToOneCommunicator(MessageQueue messageQueue, Player initiator, Player target, int maxMessageNumber) {
	this.messageQueue = messageQueue;
	this.initiator = initiator;
	this.target = target;
	this.maxMessageNumber = maxMessageNumber;
	initPlayerMap();
	initMessageCountMap();

    }

    private void initMessageCountMap() {
	messageCountMap.put(initiator.getId(), 0);
	messageCountMap.put(target.getId(), 0);

    }

    private void initPlayerMap() {
	playerMap.put(initiator.getId(), initiator);
	playerMap.put(target.getId(), target);

    }

    @Override
    public void initiate() throws InterruptedException {
	Message message = createInitialMessage();
	messageQueue.putMessage(target.getId(), message);
	System.out
		.println("Message Sent by: " + playerMap.get(initiator.getId()).getName() + " " + message.getContent());
	increaseMessageCount(initiator);

    }

    @Override
    public Message consumeMessage(Player player) throws InterruptedException {
	Message message = messageQueue.getMessage(player.getId());
	Thread.sleep(1000);
	System.out.println("***** Message Received by: " + player.getName() + " " + message.getContent());
	return message;
    }

    @Override
    public Message sendResponse(Message message) throws InterruptedException {
	Message response = createResponse(message);
	messageQueue.putMessage(response.getTargetPlayerId(), response);
	System.out.println("Response sent by: " + playerMap.get(response.getSourcePlayerId()).getName() + " "
		+ response.getContent());
	return response;
    }

    @Override
    public boolean isRunning() {
	return messageCountMap.get(initiator.getId()) < maxMessageNumber;
    }

    private Message createInitialMessage() {
	Message message = new Message(UUID.randomUUID(), initiator.getId(), target.getId());
	message.setContent("----------This is your last chance.----------");
	return message;
    }

    private Message createResponse(Message message) {
	Message response = new Message(UUID.randomUUID(), message.getTargetPlayerId(), message.getSourcePlayerId());
	response.setContent(message.getContent() + " " + messageCountMap.get(response.getSourcePlayerId()));
	return response;
    }

    @Override
    public void increaseMessageCount(Player player) {
	messageCountMap.compute(player.getId(), (k, v) -> v + 1);

    }

}
