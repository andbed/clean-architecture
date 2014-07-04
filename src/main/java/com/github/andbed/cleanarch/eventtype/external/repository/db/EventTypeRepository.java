package com.github.andbed.cleanarch.eventtype.external.repository.db;

import java.util.ArrayList;
import java.util.List;

import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesProvider;
import com.github.andbed.cleanarch.eventtype.core.usecase.entity.EventType;

public class EventTypeRepository implements EventTypesProvider {

	private final EventTypeRequestModel requestModel;

	public EventTypeRepository(EventTypeRequestModel requestModel) {
		this.requestModel = requestModel;
	}

	@Override
	public List<EventType> findAll() {
		String query = createQueryBasedOnParams(requestModel);
		return runQueryOnDB(query);
	}

	private List<EventType> runQueryOnDB(String query) {
		return new ArrayList<EventType>();
	}

	private String createQueryBasedOnParams(EventTypeRequestModel requestModel2) {
		return "";
	}

	@Override
	public void persist(List<EventType> eventTypes) {
	}

}
