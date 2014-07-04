package com.github.andbed.cleanarch.eventtype.core.boundary;

import java.util.List;

import com.github.andbed.cleanarch.util.common.MessageCode;

public interface EventTypesListPresenter {

	void sendMessage(MessageCode code);

	void sendResult(List<EventTypeResponseModel> events);
}
