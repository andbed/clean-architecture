package com.github.andbed.cleanarch.eventtype.infrastructure.repository.rest;

import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesPersister;
import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventTypeRestRepo implements EventTypesFinder, EventTypesPersister {

    List<EventTypeFromRest> events = new ArrayList<>();


    @Override
    public List<EventType> findAll(Optional<EventTypeRequestModel> requestModel) {
        return events.stream()
                .filter(e -> e.getName().equals(requestModel.get().getSearchTerm().get()))
                .sorted(Comparator.comparing(EventTypeFromRest::getName))
                .limit(100)
                .collect(Collectors.toList());
    }

    @Override
    public void persist(List<EventType> eventTypes) {

    }
}
