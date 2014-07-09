package com.github.andbed.cleanarch.eventtype.external.notification.email;

import lombok.extern.slf4j.Slf4j;

import com.github.andbed.cleanarch.eventtype.core.gateway.Notifier;

@Slf4j
public class EmailSender implements Notifier {

	@Override
	public void notifyAdministrator(String message) {
		log.debug("Email sent to admin");
	}

}
