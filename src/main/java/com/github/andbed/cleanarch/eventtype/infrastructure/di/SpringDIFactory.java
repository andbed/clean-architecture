package com.github.andbed.cleanarch.eventtype.infrastructure.di;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.eventtype.core.GetAllEventTypes;
import com.github.andbed.cleanarch.eventtype.core.ImportEventTypes;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeFactory;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesFileProvider;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListReceiver;
import com.github.andbed.cleanarch.eventtype.core.boundary.ImportReceiver;
import com.github.andbed.cleanarch.eventtype.core.boundary.Notifier;
import com.github.andbed.cleanarch.eventtype.core.boundary.XMLParser;
import com.github.andbed.cleanarch.eventtype.infrastructure.repository.db.EventTypeRepository;

@Named
public class SpringDIFactory implements EventTypeFactory {

	private final EventTypeRepository repository;
	private final XMLParser xmlParser;
	private final Notifier notifier;
	private final EventTypesFileProvider fileProvider;

	@Inject
	public SpringDIFactory(EventTypeRepository repository, XMLParser xmlParser, Notifier notifier, EventTypesFileProvider fileProvider) {
		this.repository = repository;
		this.xmlParser = xmlParser;
		this.notifier = notifier;
		this.fileProvider = fileProvider;
	}

	@Override
	public Command createGetAllEventTypesCommand(EventTypesListReceiver presenter, Optional<EventTypeRequestModel> requestModel) {
		return new GetAllEventTypes(repository, presenter, requestModel);
	}

	public Command createImportEventTypesCommand(ImportReceiver presenter) {
		return new ImportEventTypes(fileProvider, repository, notifier, xmlParser, presenter);
	}

}
