package com.github.andbed.cleanarch.eventtype.core.boundary.require;

import java.util.List;

import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

public interface EventTypesPersister {

	void persist(List<EventType> eventTypes);

}
