package com.github.andbed.cleanarch.eventtype.delivery.rest;

import java.util.List;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.Factory;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListPresenter;

public class EventTypesListController {

	public List<EventTypeResponseModel> getAllEventTypes() {

		Presenter presenter = new Presenter();
		Command getAllEventTypes = Factory.createGetAllEventTypesCommand(presenter);
		getAllEventTypes.execute();
		return presenter.generateResponse();
	}

	public static class Presenter implements EventTypesListPresenter {

		private List<EventTypeResponseModel> eventTypes;

		public List<EventTypeResponseModel> generateResponse() {
			return eventTypes;
		}

		@Override
		public void sendMessage(MessageCode code) {
		}

		@Override
		public void sendResult(List<EventTypeResponseModel> dtos) {

		}
	};
}
