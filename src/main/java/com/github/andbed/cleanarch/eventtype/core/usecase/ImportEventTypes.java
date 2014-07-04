package com.github.andbed.cleanarch.eventtype.core.usecase;

import java.util.List;

import com.github.andbed.cleanarch.eventtype.core.boundary.ImportPresenter;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesFileProvider;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesProvider;
import com.github.andbed.cleanarch.eventtype.core.gateway.Notifier;
import com.github.andbed.cleanarch.eventtype.core.gateway.XMLParser;
import com.github.andbed.cleanarch.eventtype.core.usecase.entity.EventType;
import com.github.andbed.cleanarch.util.common.Command;
import com.github.andbed.cleanarch.util.common.MessageCode;

public class ImportEventTypes implements Command {

	private static final String MESSAGE = "EventTypes were successfuly parsed and persisted";
	private static final boolean SUCCESS = true;
	private final EventTypesFileProvider fileProvider;
	private final EventTypesProvider eventTypesProvider;
	private final Notifier notificator;
	private final XMLParser xmlParser;
	private final ImportPresenter presenter;

	public ImportEventTypes(EventTypesFileProvider fileProvider, EventTypesProvider eventTypesProvider, Notifier notificator, XMLParser xmlParser, ImportPresenter receiver) {
		this.fileProvider = fileProvider;
		this.eventTypesProvider = eventTypesProvider;
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

		if (eventTypes.size() > 0) {

			eventTypes.forEach(
					e -> e.calculateInheritedAttributes());

			eventTypesProvider.persist(eventTypes);

			notificator.notifyAdministrator(MESSAGE);

			presenter.sendResult(SUCCESS);

		} else {
			presenter.sendMessage(MessageCode.NOT_FOUND);
		}
	}

}
