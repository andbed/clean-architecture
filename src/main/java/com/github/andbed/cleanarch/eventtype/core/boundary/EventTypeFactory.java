package com.github.andbed.cleanarch.eventtype.core.boundary;

import java.util.Optional;

import com.github.andbed.cleanarch.common.Command;

public interface EventTypeFactory {

	Command createGetAllEventTypesCommand(EventTypesListReceiver presenter, Optional<EventTypeRequestModel> requestModel);

}
