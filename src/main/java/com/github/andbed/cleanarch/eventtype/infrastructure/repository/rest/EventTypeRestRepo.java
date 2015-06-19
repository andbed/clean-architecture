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
    public List<EventType> findAll(Optional<EventTypeRequestModel> request) {

        return events.stream()
                .filter(e -> nameIsEqualToOrAll(e, request.map(EventTypeRequestModel::getSearchTerm)))
                .sorted(Comparator.comparing(EventTypeFromRest::getName))
                .limit(100)
                .collect(Collectors.toList());
    }


    private boolean nameIsEqualToOrAll(EventTypeFromRest e, Optional<String> search) {
        return search.isPresent() ? e.getName().equals(search.get()) : true;

    }


    @Override
    public void persist(List<EventType> eventTypes) {

    }
}
