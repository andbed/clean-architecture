package com.github.andbed.cleanarch.eventtype.core.boundary;

import java.util.List;

import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

public interface EventTypesPersister {

	void persist(List<EventType> eventTypes);

}
