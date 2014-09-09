package com.github.andbed.cleanarch.eventtype.core;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Named;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListReceiver;
import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

@Named
public class GetAllEventTypes implements Command {

	private final EventTypesFinder provider;
	private final EventTypesListReceiver receiver;
	private final Optional<EventTypeRequestModel> requestModel;

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
				List<EventTypeResponseModel> eventTypes = allEventTypes.stream()
						.filter(e -> e.isDisplayed())
						.map(e -> convert(e))
						.collect(Collectors.toList());

				receiver.sendResult(eventTypes);

			} else {
				receiver.sendMessage(MessageCode.NOT_FOUND);
			}
		} catch (Exception e) {
			receiver.sendMessage(MessageCode.INTERNAL_SERVER_ERROR);
		}
	}

	private EventTypeResponseModel convert(EventType e) {
		return new EventTypeResponseModel();
	}
}
