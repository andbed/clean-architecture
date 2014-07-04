package com.github.andbed.cleanarch.eventtype.delivery.rest;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.Factory;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.ImportPresenter;

public class ImportEventTypesController {

	public boolean importEventTypes() {

		Presenter presenter = new Presenter();
		Command importEventTypes = Factory.createImportEventTypesCommand(presenter);
		importEventTypes.execute();
		return presenter.generateResponse();
	}

	public static class Presenter implements ImportPresenter {

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
