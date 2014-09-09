package com.github.andbed.cleanarch.eventtype.delivery.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeFactory;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListReceiver;

@Controller
@RequestMapping(EventTypesController.URL)
public class EventTypesController {

	public static final String URL = "/event";
	private final EventTypeFactory factory;

	@Inject
	public EventTypesController(EventTypeFactory factory) {
		this.factory = factory;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Resources<EventTypeResponseModel>> getAllEventTypes(Optional<EventTypeRequestModel> requestModel) {

		EventTypesPresenter presenter = new EventTypesPresenter();
		Command getAllEventTypes = factory.createGetAllEventTypesCommand(presenter, requestModel);

		getAllEventTypes.execute();

		return presenter.generateResponse();

	}

	@Slf4j
	static class EventTypesPresenter implements EventTypesListReceiver {

		private Optional<List<EventTypeResponseModel>> eventTypes;
		private Optional<MessageCode> code;

		public EventTypesPresenter() {
			this.eventTypes = Optional.empty();
			this.code = Optional.empty();
		}

		public ResponseEntity<Resources<EventTypeResponseModel>> generateResponse() {
			return code.isPresent() ?
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) :
					new ResponseEntity<>(new Resources<>(eventTypes.orElse(new ArrayList<>())), HttpStatus.OK);
		}

		@Override
		public void sendMessage(MessageCode code) {
			this.code = Optional.ofNullable(code);
			log.debug(code.toString());
		}

		@Override
		public void sendResult(List<EventTypeResponseModel> eventTypes) {
			this.eventTypes = Optional.ofNullable(eventTypes);
		}
	};
}
