package com.github.andbed.cleanarch.eventtype.infrastructure.repository.rest;

import com.github.andbed.cleanarch.eventtype.core.entity.EventAttribute;
import com.github.andbed.cleanarch.eventtype.core.entity.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventTypeFromRest extends EventType {

        Long id;

        String name;

        String commaSeparatedAttributes;

        boolean visible;

        @Override
        public List<EventAttribute> getAttributes() {
            return createAttributesFromString(commaSeparatedAttributes);
        }

        private List<EventAttribute> createAttributesFromString(String atts) {
            return newArrayList();
        }

    }
