package com.github.andbed.cleanarch.eventtype.core.boundary;

import java.util.List;

import com.github.andbed.cleanarch.common.MessageCode;

public interface EventTypesReceiver {

	void sendMessage(MessageCode code);

	void displayValues(List<EventTypeDTO> dtos);
}
