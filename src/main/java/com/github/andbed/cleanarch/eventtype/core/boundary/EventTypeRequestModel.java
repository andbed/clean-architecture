package com.github.andbed.cleanarch.eventtype.core.boundary;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventTypeRequestModel {
	String searchTerm;
	PageRequest page;
}
