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

import com.github.andbed.cleanarch.eventtype.core.boundary.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.Command;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.EventTypeFactory;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.EventTypeShortResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.provide.EventTypesListPresenter;

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
	public ResponseEntity<Resources<EventTypeShortResponseModel>> getAllEventTypes(Optional<EventTypeRequestModel> requestModel) {

		EventTypesPresenter presenter = new EventTypesPresenter();
		Command getAllEventTypesCommand =
                factory.createGetAllEventTypesCommand(presenter, requestModel);

		getAllEventTypesCommand.execute();

		return presenter.generateResponse();

	}

	@Slf4j
	public static class EventTypesPresenter implements EventTypesListPresenter {

        private Optional<List<EventTypeShortResponseModel>> eventTypes;
		private Optional<MessageCode> serverErrorCode;
        private Optional<MessageCode> clientErrorCode;

		public EventTypesPresenter() {
			this.eventTypes = Optional.empty();
			this.serverErrorCode = Optional.empty();
            this.clientErrorCode = Optional.empty();
		}

		public ResponseEntity<Resources<EventTypeShortResponseModel>> generateResponse() {
			return eventTypes.isPresent() ?
                    new ResponseEntity<>(new Resources<EventTypeShortResponseModel>(eventTypes.orElse(new ArrayList<>())),
                            HttpStatus.OK) :
                    createErrorResponse();
		}

        private ResponseEntity createErrorResponse() {

            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @Override
        public void sendServerErrorMessage(MessageCode code) {
            this.serverErrorCode = Optional.ofNullable(code);
            log.debug(code.toString());
        }

        @Override
        public void sendClientErrorMessage(MessageCode code) {
            this.clientErrorCode = Optional.ofNullable(code);
            log.debug(code.toString());
        }

        @Override
		public void sendResult(List<EventTypeShortResponseModel> eventTypes) {
			this.eventTypes = Optional.ofNullable(eventTypes);
		}
	};
}
