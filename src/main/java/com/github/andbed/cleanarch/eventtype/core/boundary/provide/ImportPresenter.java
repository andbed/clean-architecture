package com.github.andbed.cleanarch.eventtype.core.boundary.provide;

import com.github.andbed.cleanarch.eventtype.core.boundary.MessageCode;



public interface ImportPresenter {

	void sendMessage(MessageCode xmlNotValid);

	void sendResult(boolean result);

}
