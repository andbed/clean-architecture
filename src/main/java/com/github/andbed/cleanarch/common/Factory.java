package com.github.andbed.cleanarch.common;

import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesReceiver;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesProvider;
import com.github.andbed.cleanarch.eventtype.core.usecase.GetAllEventTypes;
import com.github.andbed.cleanarch.eventtype.external.repository.db.EventTypeRepository;

public class Factory {

	public static Command createGetAllEventTypesCommand(EventTypesReceiver receiver) {
		return new GetAllEventTypes(createEventTypesProvider(), receiver);
	}

	private static EventTypesProvider createEventTypesProvider() {
		return new EventTypeRepository();
	}

}
