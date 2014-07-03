package com.github.andbed.cleanarch.eventtype.core.boundary;

import java.util.List;

import com.github.andbed.cleanarch.common.MessageCode;

public interface ImportReceiver {

	void sendMessage(MessageCode xmlNotValid);

	void displayResult(List<EventTypeDTO> dtos);

}
