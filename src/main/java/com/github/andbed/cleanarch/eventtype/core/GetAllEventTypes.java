package com.github.andbed.cleanarch.eventtype.core;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import com.github.andbed.cleanarch.eventtype.core.boundary.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.Command;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.EventTypeShortResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.EventTypesListPresenter;
import com.github.andbed.cleanarch.eventtype.core.boundary.require.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

@Slf4j
public class GetAllEventTypes implements Command {

	protected final EventTypesFinder provider;
	protected final EventTypesListPresenter presenter;
	protected final Optional<EventTypeRequestModel> requestModel;

	public GetAllEventTypes(EventTypesFinder provider, EventTypesListPresenter presenter,
			Optional<EventTypeRequestModel> requestModel) {
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
			log.debug(e.getMessage(), e);
			presenter.sendServerErrorMessage(MessageCode.INTERNAL_SERVER_ERROR);
		}
	}

}
