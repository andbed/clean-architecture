package com.github.andbed.cleanarch.eventtype.core;

import static org.springframework.util.CollectionUtils.isEmpty;

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

@Named
public class GetAllEventTypes implements Command {

	protected final EventTypesFinder provider;
	protected final EventTypesListReceiver receiver;
	protected final Optional<EventTypeRequestModel> requestModel;

	public GetAllEventTypes(EventTypesFinder provider, EventTypesListReceiver receiver, Optional<EventTypeRequestModel> requestModel) {
		this.provider = provider;
		this.receiver = receiver;
		this.requestModel = requestModel;
	}

	@Override
	public void execute() {
		try {
			List<EventType> allEventTypes = provider.findAll(requestModel);

			if (!isEmpty(allEventTypes)) {
				List<EventTypeShortResponseModel> eventTypes = allEventTypes.stream()
						.filter(EventType::isVisible)
						.map(e -> e.toShortEventType())
						.collect(Collectors.toList());

				receiver.sendResult(eventTypes);

			} else {
				receiver.sendClientErrorMessage(MessageCode.NOT_FOUND);
			}

		} catch (Exception e) {
			receiver.sendServerErrorMessage(MessageCode.INTERNAL_SERVER_ERROR);
		}
	}

}
