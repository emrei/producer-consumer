package com.trading.communication.runnable;

import com.trading.communication.communicator.impl.OneToOneCommunicator;
import com.trading.communication.model.Message;
import com.trading.communication.model.Player;

/**
 * Consumer runnable class which runs communicator interface methods. This class
 * is used to read messages from message queue and send responses.
 * @author YunusEmre
 *
 */
public class Consumer implements Runnable {
    private final OneToOneCommunicator communicator;
    private final Player player;

    public Consumer(OneToOneCommunicator communicator, Player player) {
	this.communicator = communicator;
	this.player = player;
    }

    @Override
    public void run() {
	while (communicator.isRunning()) {
	    try {
		communicator.increaseMessageCount(player);
		Message message = communicator.consumeMessage(player);
		communicator.sendResponse(message);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

    }
}
