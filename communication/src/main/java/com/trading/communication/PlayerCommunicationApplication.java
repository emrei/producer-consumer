package com.trading.communication;

import com.trading.communication.engine.CommunicationEngine;
import com.trading.communication.engine.impl.PlayerCommunicationEngine;

public class PlayerCommunicationApplication {

    public static void main(String[] args) {
	CommunicationEngine playerCommunicationEngine = new PlayerCommunicationEngine();
	playerCommunicationEngine.communicate();
    }

}
