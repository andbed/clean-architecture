package com.github.andbed.cleanarch.eventtype.core;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Named;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeShortResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListReceiver;
import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

public class GetAllEventTypes implements Command {

	protected final EventTypesFinder provider;
	protected final EventTypesListReceiver presenter;
	protected final Optional<EventTypeRequestModel> requestModel;

	public GetAllEventTypes(EventTypesFinder provider, EventTypesListReceiver presenter, Optional<EventTypeRequestModel> requestModel) {
		this.provider = provider;
		this.presenter = presenter;
		this.requestModel = requestModel;
	}

	@Override
	public void execute() {
		try {
			List<EventType> allEventTypes = provider.findAll(requestModel);

			if (!allEventTypes.isEmpty()) {
				List<EventTypeShortResponseModel> eventTypes = allEventTypes.stream()
						.filter(EventType::isVisible)
						.map(e -> e.toShortEventType())
						.collect(Collectors.toList());

				presenter.sendResult(eventTypes);

			} else {
				presenter.sendClientErrorMessage(MessageCode.NOT_FOUND);
			}

		} catch (Exception e) {
			presenter.sendServerErrorMessage(MessageCode.INTERNAL_SERVER_ERROR);
		}
	}

}
