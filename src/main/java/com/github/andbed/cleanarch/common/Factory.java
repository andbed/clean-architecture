package com.github.andbed.cleanarch.common;

import com.github.andbed.cleanarch.eventtype.core.GetAllEventTypes;
import com.github.andbed.cleanarch.eventtype.core.ImportEventTypes;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesFileProvider;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListPresenter;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesPersister;
import com.github.andbed.cleanarch.eventtype.core.boundary.ImportPresenter;
import com.github.andbed.cleanarch.eventtype.core.boundary.Notifier;
import com.github.andbed.cleanarch.eventtype.core.boundary.XMLParser;
import com.github.andbed.cleanarch.eventtype.infrastructure.io.EventTypeFileManager;
import com.github.andbed.cleanarch.eventtype.infrastructure.io.XMLParserSAX;
import com.github.andbed.cleanarch.eventtype.infrastructure.notification.email.EmailSender;
import com.github.andbed.cleanarch.eventtype.infrastructure.repository.db.EventTypeRepository;

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
