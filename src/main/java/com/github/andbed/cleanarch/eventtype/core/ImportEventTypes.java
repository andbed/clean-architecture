package com.github.andbed.cleanarch.eventtype.core;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import javax.inject.Named;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesFileProvider;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesPersister;
import com.github.andbed.cleanarch.eventtype.core.boundary.ImportReceiver;
import com.github.andbed.cleanarch.eventtype.core.boundary.Notifier;
import com.github.andbed.cleanarch.eventtype.core.boundary.XMLParser;
import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

public class ImportEventTypes implements Command {

	private static final String MESSAGE = "EventTypes were successfuly parsed and persisted";
	private static final boolean SUCCESS = true;
	private final EventTypesFileProvider fileProvider;
	private final EventTypesPersister eventTypesPersister;
	private final Notifier notificator;
	private final XMLParser xmlParser;
	private final ImportReceiver presenter;

	public ImportEventTypes(EventTypesFileProvider fileProvider, EventTypesPersister eventTypesPersister, Notifier notificator, XMLParser xmlParser, ImportReceiver receiver) {
		this.fileProvider = fileProvider;
		this.eventTypesPersister = eventTypesPersister;
		this.notificator = notificator;
		this.xmlParser = xmlParser;
		this.presenter = receiver;
	}

	@Override
	public void execute() {
		String xmlPath = fileProvider.findEventTypesFile();
		String xsdPath = fileProvider.findEventTypesXSD();

		boolean isValidFile = xmlParser.isValid(xmlPath, xsdPath);
		if (!isValidFile) {
			presenter.sendMessage(MessageCode.XML_NOT_VALID);
			return;
		}

		List<EventType> eventTypes = xmlParser.bind(EventType.class);

		if (!isEmpty(eventTypes)) {

			eventTypes.forEach(
					e -> e.calculateInheritedAttributes());

			eventTypesPersister.persist(eventTypes);

			notificator.notifyAdministrator(MESSAGE);

			presenter.sendResult(SUCCESS);

		} else {
			presenter.sendMessage(MessageCode.NOT_FOUND);
		}
	}

}
