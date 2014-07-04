package com.github.andbed.cleanarch.eventtype.core.usecase;

import java.util.List;
import java.util.stream.Collectors;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeDTO;
import com.github.andbed.cleanarch.eventtype.core.boundary.ImportReceiver;
import com.github.andbed.cleanarch.eventtype.core.gateway.Notificator;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesFileProvider;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesProvider;
import com.github.andbed.cleanarch.eventtype.core.gateway.XMLParser;
import com.github.andbed.cleanarch.eventtype.core.usecase.entity.EventType;

public class ImportEventTypes implements Command {

	private static final String MESSAGE = "EventTypes were successfuly parsed and persisted";
	private final EventTypesFileProvider fileProvider;
	private final EventTypesProvider eventTypesProvider;
	private final Notificator notificator;
	private final XMLParser xmlParser;
	private final ImportReceiver receiver;

	public ImportEventTypes(EventTypesFileProvider fileProvider, EventTypesProvider eventTypesProvider, Notificator notificator, XMLParser xmlParser, ImportReceiver receiver) {
		this.fileProvider = fileProvider;
		this.eventTypesProvider = eventTypesProvider;
		this.notificator = notificator;
		this.xmlParser = xmlParser;
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		String xmlPath = fileProvider.findEventTypesFile();
		String xsdPath = fileProvider.findEventTypesXSD();
		if (!xmlParser.isValid(xmlPath, xsdPath)) {
			receiver.sendMessage(MessageCode.XML_NOT_VALID);
		}
		List<EventType> eventTypes = xmlParser.bind(EventType.class);
		if (eventTypes.size() > 0) {

			eventTypes.forEach(e -> e.calculateInheritedAttributes());

			eventTypesProvider.persist(eventTypes);

			List<EventTypeDTO> dtos = eventTypes.stream()
					.filter(e -> e.isDisplayed)
					.map(e -> convert(e))
					.collect(Collectors.toList());

			notificator.notifyAdministrator(MESSAGE);

			receiver.displayResult(dtos);

		} else {

			receiver.sendMessage(MessageCode.NOT_FOUND);
		}
	}

	private EventTypeDTO convert(EventType e) {
		return new EventTypeDTO();
	}
}
