package com.github.andbed.cleanarch.eventtype.core.boundary.require;

import java.io.IOException;

public interface EventTypesFileProvider {

	String findEventTypesFile() throws IOException;

	String findEventTypesXSD() throws  IOException;

}
