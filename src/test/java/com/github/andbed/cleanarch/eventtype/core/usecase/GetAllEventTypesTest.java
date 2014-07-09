package com.github.andbed.cleanarch.eventtype.core.usecase;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListPresenter;
import com.github.andbed.cleanarch.eventtype.core.gateway.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.usecase.entity.EventType;

public class GetAllEventTypesTest {

	private Presenter presenter;

	@Before
	public void setUp() {
		presenter = new Presenter();
	}

	@Test
	public void shouldGetFilterCorrectItems() {
		// given
		EventTypesFinder provider =
				() -> newArrayList(new EventType(), new EventType());
		GetAllEventTypes command = new GetAllEventTypes(provider, presenter);

		// when
		command.execute();

		// then
		assertThat(presenter.events).hasSize(2);
	}

	@Test
	public void shouldHandleDBDown() {
		// given
		EventTypesFinder provider =
				() -> {
					throw new RuntimeException("DB down");
				};

		GetAllEventTypes command = new GetAllEventTypes(provider, presenter);

		// when
		command.execute();

		// then
		assertThat(presenter.events).isNull();
		assertThat(presenter.code).isEqualTo(MessageCode.INTERNAL_SERVER_ERROR);
	}

	class Presenter implements EventTypesListPresenter {

		List<EventTypeResponseModel> events;
		MessageCode code;

		@Override
		public void sendResult(List<EventTypeResponseModel> events) {
			this.events = events;

		}

		@Override
		public void sendMessage(MessageCode code) {
			this.code = code;

		}
	};

}
