package com.github.andbed.cleanarch.eventtype.infrastructure.di;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.andbed.cleanarch.eventtype.core.GetAllEventTypes;
import com.github.andbed.cleanarch.eventtype.core.ImportEventTypes;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.Command;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.EventTypeFactory;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.EventTypesListPresenter;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.ImportPresenter;
import com.github.andbed.cleanarch.eventtype.core.boundary.require.EventTypesFileProvider;
import com.github.andbed.cleanarch.eventtype.core.boundary.require.Notifier;
import com.github.andbed.cleanarch.eventtype.core.boundary.require.XMLParser;
import com.github.andbed.cleanarch.eventtype.infrastructure.repository.db.jpa.EventTypeRepository;

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
	public Command createGetAllEventTypesCommand(EventTypesListPresenter presenter, Optional<EventTypeRequestModel> requestModel) {
		return new GetAllEventTypes(repository, presenter, requestModel);
	}

	public Command createImportEventTypesCommand(ImportPresenter presenter, EventTypeRequestModel params) {
		return new ImportEventTypes(fileProvider, repository, notifier, xmlParser, presenter);
	}

}
