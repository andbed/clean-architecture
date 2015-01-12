package com.github.andbed.cleanarch.eventtype.core.boundary;

import java.io.IOException;

public interface EventTypesFileProvider {

	String findEventTypesFile() throws IOException;

	String findEventTypesXSD() throws  IOException;

}
