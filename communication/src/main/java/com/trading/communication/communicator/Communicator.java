package com.trading.communication.communicator;

import com.trading.communication.model.Message;
import com.trading.communication.model.Player;

/**
 * communicator interface for communicating via message model
 * 
 * @author YunusEmre
 *
 */
public interface Communicator {

    /**
     * start communication by sending first message
     * @throws InterruptedException 
     */
    void initiate() throws InterruptedException;

    /**
     * consume a message for given player and return
     * @param player 
     * 
     * @return
     * @throws InterruptedException
     */
    Message consumeMessage(Player player) throws InterruptedException;

    /**
     * send response from message and return created response.
     * this method creates a new message, it swaps target and source id for new message
     * @param message
     * @return
     * @throws InterruptedException
     */
    Message sendResponse(Message message) throws InterruptedException;

    /**
     * check the conversation is still alive
     * @param player
     * @return
     */
    boolean isRunning();
    
    /**
     * increase message count for given player
     * @param player
     */
    void increaseMessageCount(Player player);

}
