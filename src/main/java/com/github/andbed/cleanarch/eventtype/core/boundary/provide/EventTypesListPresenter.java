package com.github.andbed.cleanarch.eventtype.core.boundary.provide;

import java.util.List;

import com.github.andbed.cleanarch.eventtype.core.boundary.MessageCode;

public interface EventTypesListPresenter {

	void sendServerErrorMessage(MessageCode code);

	void sendClientErrorMessage(MessageCode code);

	void sendResult(List<EventTypeShortResponseModel> events);
}
