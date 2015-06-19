package com.github.andbed.cleanarch.eventtype.core.boundary.provide;

import java.util.Optional;

public interface EventTypeFactory {

	Command createGetAllEventTypesCommand(EventTypesListPresenter presenter,
			Optional<EventTypeRequestModel> requestModel);

}
