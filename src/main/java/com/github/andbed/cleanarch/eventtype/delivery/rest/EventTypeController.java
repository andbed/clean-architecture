package com.github.andbed.cleanarch.eventtype.delivery.rest;

import java.util.List;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.Factory;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeDTO;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesReciever;

public class EventTypeController {

	public List<EventTypeDTO> getAllEventTypes() {

		EventTypesPresenter presenter = new EventTypesPresenter();
		Command getAllEventTypes = Factory.createGetAllEventTypesCommand(presenter);
		getAllEventTypes.execute();
		return presenter.generateResponse();
	}

	public static class EventTypesPresenter implements EventTypesReciever {

		private List<EventTypeDTO> eventTypes;

		public List<EventTypeDTO> generateResponse() {
			return eventTypes;
		}

		@Override
		public void sendMessage(MessageCode code) {
		}

		@Override
		public void displayValues(List<EventTypeDTO> dtos) {
			// TODO Auto-generated method stub

		}
	};
}
