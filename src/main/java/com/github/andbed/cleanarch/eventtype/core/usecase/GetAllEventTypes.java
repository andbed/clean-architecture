package com.github.andbed.cleanarch.eventtype.core.usecase;

import java.util.List;
import java.util.stream.Collectors;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListPresenter;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesProvider;
import com.github.andbed.cleanarch.eventtype.core.usecase.entity.EventType;

public class GetAllEventTypes implements Command {

	private final EventTypesProvider provider;
	private final EventTypesListPresenter presenter;

	public GetAllEventTypes(EventTypesProvider provider, EventTypesListPresenter reciever) {
		this.provider = provider;
		this.presenter = reciever;
	}

	@Override
	public void execute() {
		try {
			List<EventType> allEventTypes = provider.findAll();

			if (allEventTypes.size() > 0) {
				List<EventTypeResponseModel> eventTypes = allEventTypes.stream()
						.map(e -> convert(e))
						.collect(Collectors.toList());

				presenter.sendResult(eventTypes);

			} else {
				presenter.sendMessage(MessageCode.NOT_FOUND);
			}
		} catch (Exception e) {
			presenter.sendMessage(MessageCode.INTERNAL_SERVER_ERROR);
		}
	}

	private EventTypeResponseModel convert(EventType e) {
		return new EventTypeResponseModel();
	}
}
