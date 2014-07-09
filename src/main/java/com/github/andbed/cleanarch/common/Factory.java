package com.github.andbed.cleanarch.common;

import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListPresenter;
import com.github.andbed.cleanarch.eventtype.core.boundary.ImportPresenter;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesFileProvider;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesPersister;
import com.github.andbed.cleanarch.eventtype.core.gateway.Notifier;
import com.github.andbed.cleanarch.eventtype.core.gateway.XMLParser;
import com.github.andbed.cleanarch.eventtype.core.usecase.GetAllEventTypes;
import com.github.andbed.cleanarch.eventtype.core.usecase.ImportEventTypes;
import com.github.andbed.cleanarch.eventtype.external.io.EventTypeFileManager;
import com.github.andbed.cleanarch.eventtype.external.io.XMLParserSAX;
import com.github.andbed.cleanarch.eventtype.external.notification.email.EmailSender;
import com.github.andbed.cleanarch.eventtype.external.repository.db.EventTypeRepository;

public class Factory {

	public Command createGetAllEventTypesCommand(EventTypesListPresenter presenter, EventTypeRequestModel requestModel) {
		return new GetAllEventTypes(createEventTypesProvider(requestModel), presenter);
	}

	private EventTypesFinder createEventTypesProvider(EventTypeRequestModel requestModel) {
		return new EventTypeRepository(requestModel);
	}

	public Command createImportEventTypesCommand(ImportPresenter presenter) {
		return new ImportEventTypes(createFileProvider(), createEventTypesPersister(), createNotifier(), createXMLParser(), presenter);
	}

	private EventTypesPersister createEventTypesPersister() {
		return new EventTypeRepository();
	}

	private XMLParser createXMLParser() {
		return new XMLParserSAX();
	}

	private Notifier createNotifier() {
		return new EmailSender();
	}

	private EventTypesFileProvider createFileProvider() {
		return new EventTypeFileManager();
	}

}
