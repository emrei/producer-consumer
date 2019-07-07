package com.trading.communication.engine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.trading.communication.communicator.impl.OneToOneCommunicator;
import com.trading.communication.engine.CommunicationEngine;
import com.trading.communication.model.MessageQueue;
import com.trading.communication.model.Player;
import com.trading.communication.runnable.Consumer;

/**
 * Class which manages player communications
 * 
 * @author YunusEmre
 *
 */
public class PlayerCommunicationEngine implements CommunicationEngine {

    private static final int MAX_MESSAGE_NUMBER = 10;
    private Player initiator;
    private Player target;
    private MessageQueue messageQueue;
    OneToOneCommunicator communicator;

    @Override
    public void communicate() {
	createPlayers();
	createMessageQueue();
	createCommunicator();
	initiateCommunicator();
	executeCommunicator();

    }

    private void executeCommunicator() {
	ExecutorService executor = Executors.newFixedThreadPool(2);

	executor.execute(new Consumer(communicator, initiator));
	executor.execute(new Consumer(communicator, target));
	executor.shutdown();

    }

    private void initiateCommunicator() {
	try {
	    communicator.initiate();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

    }

    private void createCommunicator() {
	communicator = new OneToOneCommunicator(messageQueue, initiator, target, MAX_MESSAGE_NUMBER);

    }

    private void createMessageQueue() {
	List<UUID> playerIdList = new ArrayList<UUID>();
	playerIdList.add(initiator.getId());
	playerIdList.add(target.getId());
	messageQueue = new MessageQueue(playerIdList, MAX_MESSAGE_NUMBER);

    }

    private void createPlayers() {
	initiator = new Player(UUID.randomUUID(), "Morpheus", "");
	target = new Player(UUID.randomUUID(), "Neo", "");

    }

}
