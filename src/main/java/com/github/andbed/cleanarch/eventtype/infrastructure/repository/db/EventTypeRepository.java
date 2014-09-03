package com.github.andbed.cleanarch.eventtype.infrastructure.repository.db;

import java.util.ArrayList;
import java.util.List;

import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesPersister;
import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

public class EventTypeRepository implements EventTypesFinder, EventTypesPersister {

	private EventTypeRequestModel requestModel;

	public EventTypeRepository(EventTypeRequestModel requestModel) {
		this.requestModel = requestModel;
	}

	public EventTypeRepository() {
	}

	@Override
	public List<EventType> findAll() {
		String query = createQueryBasedOnParams(requestModel);
		return runQueryOnDB(query);
	}

	private List<EventType> runQueryOnDB(String query) {
		return new ArrayList<EventType>();
	}

	private String createQueryBasedOnParams(EventTypeRequestModel params) {
		return "";
	}

	@Override
	public void persist(List<EventType> eventTypes) {
	}

}
