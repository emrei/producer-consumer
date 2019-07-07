package com.trading.communication.engine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.trading.communication.communicator.Communicator;
import com.trading.communication.communicator.impl.OneToOneCommunicator;
import com.trading.communication.engine.CommunicationEngine;
import com.trading.communication.model.MessageQueue;
import com.trading.communication.model.Player;
import com.trading.communication.runnable.Consumer;
import com.trading.communication.runnable.Producer;

/**
 * Class which manages player communications
 * 
 * @author YunusEmre
 *
 */
public class PlayerCommunicationEngine implements CommunicationEngine {

    private static final int MAX_MESSAGE_NUMBER = 10;
    @Override
    public void communicate() {
	Player initiator = new Player(UUID.randomUUID(), "Morpheus", "");
	Player target = new Player(UUID.randomUUID(), "Neo", "");
	List<UUID> playerIdList = new ArrayList<UUID>();
	playerIdList.add(initiator.getId());
	playerIdList.add(target.getId());
	
	MessageQueue messageQueue = new MessageQueue(playerIdList, MAX_MESSAGE_NUMBER);
	MessageQueue responseQueue = new MessageQueue(playerIdList, MAX_MESSAGE_NUMBER);

	Communicator communicator = new OneToOneCommunicator(messageQueue, responseQueue, initiator, target, MAX_MESSAGE_NUMBER);

	ExecutorService executor = Executors.newFixedThreadPool(2);
	Producer producer = new Producer(communicator);
	Consumer consumer = new Consumer(communicator);
	executor.execute(producer);
	executor.execute(consumer);
	executor.shutdown();

    }

}
