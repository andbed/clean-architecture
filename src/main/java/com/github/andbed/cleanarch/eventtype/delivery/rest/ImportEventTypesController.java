package com.github.andbed.cleanarch.eventtype.delivery.rest;

import com.github.andbed.cleanarch.eventtype.core.boundary.ImportPresenter;
import com.github.andbed.cleanarch.util.common.Command;
import com.github.andbed.cleanarch.util.common.Factory;
import com.github.andbed.cleanarch.util.common.MessageCode;

public class ImportEventTypesController {

	public boolean importEventTypes() {

		Presenter presenter = new Presenter();
		Command importEventTypes = Factory.createImportEventTypesCommand(presenter);
		importEventTypes.execute();
		return presenter.generateResponse();
	}

	static class Presenter implements ImportPresenter {

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
