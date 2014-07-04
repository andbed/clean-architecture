package com.github.andbed.cleanarch.eventtype.core.usecase.entity;

import java.util.List;

public class EventType {

	String name;

	List<EventAttribute> attributes;

	public void calculateInheritedAttributes() {
	}

	public boolean isDisplayed() {
		return true;
	}
}
