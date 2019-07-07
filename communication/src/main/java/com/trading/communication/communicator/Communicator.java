package com.trading.communication.communicator;

import com.trading.communication.model.Message;

/**
 * communicator interface for communicating via message model
 * 
 * @author YunusEmre
 *
 */
public interface Communicator {

    /**
     * Produce a message and return
     * 
     * @return
     * @throws InterruptedException
     */
    Message produceMessage() throws InterruptedException;

    /**
     * consume a message and return
     * 
     * @return
     * @throws InterruptedException
     */
    Message consumeMessage() throws InterruptedException;

    /**
     * receive a message and return
     * 
     * @return
     * @throws InterruptedException
     */
    Message receiveResponse() throws InterruptedException;

    /**
     * send response from message and return created response
     * @param message
     * @return
     * @throws InterruptedException
     */
    Message sendResponse(Message message) throws InterruptedException;

    /**
     * check the conversation is still alive
     * 
     * @return
     */
    boolean isRunning();

}
