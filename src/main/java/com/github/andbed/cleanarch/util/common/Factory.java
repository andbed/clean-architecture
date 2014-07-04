package com.github.andbed.cleanarch.util.common;

import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListPresenter;
import com.github.andbed.cleanarch.eventtype.core.boundary.ImportPresenter;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesFileProvider;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesProvider;
import com.github.andbed.cleanarch.eventtype.core.gateway.Notifier;
import com.github.andbed.cleanarch.eventtype.core.gateway.XMLParser;
import com.github.andbed.cleanarch.eventtype.core.usecase.GetAllEventTypes;
import com.github.andbed.cleanarch.eventtype.core.usecase.ImportEventTypes;
import com.github.andbed.cleanarch.eventtype.external.io.EventTypeFileManager;
import com.github.andbed.cleanarch.eventtype.external.io.XMLParserSAX;
import com.github.andbed.cleanarch.eventtype.external.notification.email.EmailSender;
import com.github.andbed.cleanarch.eventtype.external.repository.db.EventTypeRepository;

public class Factory {

	public static Command createGetAllEventTypesCommand(EventTypesListPresenter presenter, EventTypeRequestModel requestModel) {
		return new GetAllEventTypes(createEventTypesProvider(requestModel), presenter);
	}

	private static EventTypesProvider createEventTypesProvider(EventTypeRequestModel requestModel) {
		return new EventTypeRepository(requestModel);
	}

	public static Command createImportEventTypesCommand(ImportPresenter presenter) {
		return new ImportEventTypes(createFileProvider(), createEventTypesProvider(null), createNotifier(), createXMLParser(), presenter);
	}

	private static XMLParser createXMLParser() {
		return new XMLParserSAX();
	}

	private static Notifier createNotifier() {
		return new EmailSender();
	}

	private static EventTypesFileProvider createFileProvider() {
		return new EventTypeFileManager();
	}

}
