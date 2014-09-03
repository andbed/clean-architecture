package com.github.andbed.cleanarch.eventtype.core.entity;

import java.util.List;

public abstract class EventType {

	public abstract Long getId();

	public abstract String getName();

	public abstract List<EventAttribute> getAttributes();

	public abstract boolean isDisplayed();

	public void calculateInheritedAttributes() {
	}

}