package com.github.andbed.cleanarch.eventtype.core.boundary;

import java.util.List;

import com.github.andbed.cleanarch.common.MessageCode;

public interface EventTypesReciever {

	void sendMessage(MessageCode code);

	void displayValues(List<EventTypeDTO> dtos);
}
