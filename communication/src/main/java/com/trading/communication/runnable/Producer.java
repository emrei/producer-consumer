package com.trading.communication.runnable;

import com.trading.communication.communicator.Communicator;

/**
 * Producer runnable class which runs communicator interface methods
 * @author YunusEmre
 *
 */
public class Producer implements Runnable {

    private final Communicator communicator;

    public Producer(Communicator communicator) {
	this.communicator = communicator;
    }

    @Override
    public void run() {
	while (communicator.isRunning()) {
	    try {
		communicator.produceMessage();
		communicator.receiveResponse();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}

    }


}
