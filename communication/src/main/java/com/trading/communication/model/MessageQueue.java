package com.trading.communication.model;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Holds a map which contains message queue for each player id
 * 
 * @author YunusEmre
 *
 */
public class MessageQueue {
    private final ConcurrentHashMap<UUID, BlockingQueue<Message>> playerMessageMap;

    public MessageQueue(List<UUID> players, int maxMessageSize) {
	playerMessageMap = new ConcurrentHashMap<UUID, BlockingQueue<Message>>();
	players.forEach(p -> {
	    playerMessageMap.put(p, new LinkedBlockingDeque<>(maxMessageSize));
	});
    }

    public Message getMessage(UUID playerId) throws InterruptedException {
	Thread.sleep(1000);
	if (playerMessageMap.containsKey(playerId)) {
	    return playerMessageMap.get(playerId).take();
	} else {
	    throw new IllegalArgumentException("Player " + playerId + " is not defined for this message queue");
	}

    }

    public void putMessage(UUID playerId, Message message) throws InterruptedException {
	Thread.sleep(1000);
	if (playerMessageMap.containsKey(playerId)) {
	    playerMessageMap.get(playerId).put(message);
	} else {
	    throw new IllegalArgumentException("Player " + playerId + " is not defined for this message queue");
	}

    }
}
