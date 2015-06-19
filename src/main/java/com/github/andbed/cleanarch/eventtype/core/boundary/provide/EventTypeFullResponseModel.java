package com.github.andbed.cleanarch.eventtype.core.boundary.provide;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class EventTypeFullResponseModel {
    Long id;
    String name;
    List<String> attributeNames;
}
