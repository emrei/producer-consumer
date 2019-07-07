package com.trading.communication.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Player model
 * 
 * @author YunusEmre
 *
 */
public class Player {
    private final UUID id;
    private final String name;
    private final String lastName;
    private int age;

    public Player(UUID id, String name, String lastName) {
	this.id = id;
	this.name = name;
	this.lastName = lastName;
    }

    public UUID getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getLastName() {
	return lastName;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public int getAge() {
	return age;
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Player))
	    return false;
	Player other = (Player) obj;
	return Objects.equals(getId(), other.getId());
    }

}
