package com.github.andbed.cleanarch.eventtype.core.usecase;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.GetAllEventTypes;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListPresenter;
import com.github.andbed.cleanarch.eventtype.core.entity.EventAttribute;
import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

public class GetAllEventTypesTest {

	private Presenter presenter;

	@Before
	public void setUp() {
		presenter = new Presenter();
	}

	@Test
	public void shouldGetCorrectItemsToDisplay() {
		// given
		EventTypesFinder provider =
				() -> newArrayList(createEventType(1L, "et1", true), createEventType(2L, "et2", false));
		GetAllEventTypes command = new GetAllEventTypes(provider, presenter);

		// when
		command.execute();

		// then
		assertThat(presenter.numberOfEventTypesToDisplay()).isEqualTo(1);
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
		assertThat(presenter.noEventsReturned());
		assertThat(presenter.code).isEqualTo(MessageCode.INTERNAL_SERVER_ERROR);
	}

	class Presenter implements EventTypesListPresenter {

		List<EventTypeResponseModel> events;
		MessageCode code;

		@Override
		public void sendResult(List<EventTypeResponseModel> events) {
			this.events = events;

		}

		public int numberOfEventTypesToDisplay() {
			return isEmpty(events) ? 0 : events.size();
		}

		public boolean noEventsReturned() {
			return events == null;
		}

		@Override
		public void sendMessage(MessageCode code) {
			this.code = code;

		}
	};

	private EventType createEventType(Long id, String name, boolean isDisplayed) {
		return new EventType() {

			@Override
			public String getName() {
				return name;
			}

			@Override
			public Long getId() {
				return id;
			}

			@Override
			public List<EventAttribute> getAttributes() {
				return null;
			}

			@Override
			public boolean isDisplayed() {
				return isDisplayed;
			}
		};
	}

}
