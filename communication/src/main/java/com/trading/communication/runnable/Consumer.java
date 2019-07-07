package com.trading.communication.runnable;

import com.trading.communication.communicator.Communicator;
import com.trading.communication.model.Message;

/**
 * Consumer runnable class which runs communicator interface methods
 * @author YunusEmre
 *
 */
public class Consumer implements Runnable {
    
    private final Communicator communicator;

    public Consumer(Communicator communicator) {
	this.communicator = communicator;
    }

    @Override
    public void run() {
	while(communicator.isRunning()) {
	    try {
		Message message = communicator.consumeMessage();
		communicator.sendResponse(message);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	
    }

}
