package com.github.andbed.cleanarch.eventtype.core.boundary;

import java.util.List;

import com.github.andbed.cleanarch.common.MessageCode;

public interface EventTypesListReceiver {

	void sendMessage(MessageCode code);

	void sendResult(List<EventTypeResponseModel> events);
}
