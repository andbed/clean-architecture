package com.github.andbed.cleanarch.eventtype.core.gateway;

import java.util.List;

import com.github.andbed.cleanarch.eventtype.core.usecase.entity.EventType;

public interface EventTypesFinder {

	List<EventType> findAll();

}
