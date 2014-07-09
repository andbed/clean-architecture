package com.github.andbed.cleanarch.eventtype.core.usecase;

import java.util.List;
import java.util.stream.Collectors;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListPresenter;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.usecase.entity.EventType;

public class GetAllEventTypes implements Command {

	private final EventTypesFinder provider;
	private final EventTypesListPresenter presenter;

	public GetAllEventTypes(EventTypesFinder provider, EventTypesListPresenter presenter) {
		this.provider = provider;
		this.presenter = presenter;
	}

	@Override
	public void execute() {
		try {
			List<EventType> allEventTypes = provider.findAll();

			if (allEventTypes.size() > 0) {
				List<EventTypeResponseModel> eventTypes = allEventTypes.stream()
						.filter(e -> e.isDisplayed())
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
