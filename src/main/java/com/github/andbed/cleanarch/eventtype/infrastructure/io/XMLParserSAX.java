package com.github.andbed.cleanarch.eventtype.infrastructure.io;

import java.util.ArrayList;
import java.util.List;

import com.github.andbed.cleanarch.eventtype.core.boundary.XMLParser;

public class XMLParserSAX implements XMLParser {

	@Override
	public boolean isValid(String xmlPath, String xsdPath) {
		return true;
	}

	@Override
	public <E> List<E> bind(Class<E> className) {
		return new ArrayList<E>();
	}

}
