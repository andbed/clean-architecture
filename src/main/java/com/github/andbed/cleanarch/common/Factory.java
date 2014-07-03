package com.github.andbed.cleanarch.common;

import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesReciever;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesProvider;
import com.github.andbed.cleanarch.eventtype.core.usecase.GetAllEventTypes;
import com.github.andbed.cleanarch.eventtype.external.repository.EventTypeRepository;

public class Factory {

	public static Command createGetAllEventTypesCommand(EventTypesReciever reciever) {
		return new GetAllEventTypes(createEventTypesProvider(), reciever);
	}

	private static EventTypesProvider createEventTypesProvider() {
		return new EventTypeRepository();
	}

}
