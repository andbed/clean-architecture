package com.github.andbed.cleanarch.eventtype.core.usecase;

import java.util.List;
import java.util.stream.Collectors;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeDTO;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesReceiver;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesProvider;
import com.github.andbed.cleanarch.eventtype.core.usecase.entity.EventType;

public class GetAllEventTypes implements Command {

	private final EventTypesProvider provider;
	private final EventTypesReceiver reciever;

	public GetAllEventTypes(EventTypesProvider provider, EventTypesReceiver reciever) {
		this.provider = provider;
		this.reciever = reciever;
	}

	@Override
	public void execute() {
		try {
			List<EventType> allEventTypes = provider.findAll();

			if (allEventTypes.size() > 0) {
				List<EventTypeDTO> dtos = allEventTypes.stream()
						.map(e -> convert(e))
						.collect(Collectors.toList());

				reciever.displayValues(dtos);

			} else {
				reciever.sendMessage(MessageCode.NOT_FOUND);
			}
		} catch (Exception e) {
			reciever.sendMessage(MessageCode.INTERNAL_SERVER_ERROR);
		}
	}

	private EventTypeDTO convert(EventType e) {
		return new EventTypeDTO();
	}
}
