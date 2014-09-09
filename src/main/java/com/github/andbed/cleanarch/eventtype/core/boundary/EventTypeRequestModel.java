package com.github.andbed.cleanarch.eventtype.core.boundary;

import java.util.Optional;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventTypeRequestModel {
	Optional<String> searchTerm;
	Optional<PageRequest> page;
}
