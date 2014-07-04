package com.github.andbed.cleanarch.eventtype.external.repository.db;

import java.util.ArrayList;
import java.util.List;

import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesProvider;
import com.github.andbed.cleanarch.eventtype.core.usecase.entity.EventType;

public class EventTypeRepository implements EventTypesProvider {

	@Override
	public List<EventType> findAll() {
		return new ArrayList<EventType>();
	}

	@Override
	public void persist(List<EventType> eventTypes) {
	}

}
