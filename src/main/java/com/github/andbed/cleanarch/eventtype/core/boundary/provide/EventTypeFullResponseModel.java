package com.github.andbed.cleanarch.eventtype.core.boundary.provide;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventTypeFullResponseModel {
	Long id;
	String name;
	List<String> attributeNames;
}
