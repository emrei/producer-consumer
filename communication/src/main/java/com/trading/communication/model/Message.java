package com.trading.communication.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Message model for sending and receiving operations
 * 
 * @author YunusEmre
 *
 */
public class Message {
    private final UUID id;
    private final UUID sourcePlayerId;
    private final UUID targetPlayerId;
    private String content;

    public Message(UUID id, UUID sourcePlayerId, UUID targetPlayerId) {
	this.id = id;
	this.sourcePlayerId = sourcePlayerId;
	this.targetPlayerId = targetPlayerId;
    }

    public UUID getId() {
	return id;
    }

    public UUID getSourcePlayerId() {
	return sourcePlayerId;
    }

    public UUID getTargetPlayerId() {
	return targetPlayerId;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    @Override
    public int hashCode() {
	return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Message))
	    return false;
	Message other = (Message) obj;
	return Objects.equals(getId(), other.getId());
    }

}
