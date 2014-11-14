package com.github.andbed.cleanarch.eventtype.core.boundary;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventTypeShortResponseModel {
    Long id;
    String name;
    Long number;
}
