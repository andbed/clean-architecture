package com.github.andbed.cleanarch.eventtype.delivery.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.Factory;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.ImportPresenter;

@Controller
@RequestMapping(ImportEventTypesController.URL)
public class ImportEventTypesController {

	public static final String URL = "/eventimport";
	private final Factory factory;

	public ImportEventTypesController(Factory factory) {
		this.factory = factory;
	}

	@RequestMapping(method = RequestMethod.GET)
	public boolean importEventTypes() {
		Presenter presenter = new Presenter();
		Command importEventTypes = factory.createImportEventTypesCommand(presenter);
		importEventTypes.execute();
		return presenter.generateResponse();
	}

	class Presenter implements ImportPresenter {

		private boolean result;

		@Override
		public void sendMessage(MessageCode xmlNotValid) {
		}

		public boolean generateResponse() {
			return result;
		}

		@Override
		public void sendResult(boolean result) {
			this.result = result;
		}

	};
}
