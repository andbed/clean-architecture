package com.github.andbed.cleanarch.eventtype.core.usecase;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.GetAllEventTypes;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeResponseModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesFinder;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListReceiver;
import com.github.andbed.cleanarch.eventtype.core.entity.EventAttribute;
import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

public class GetAllEventTypesTest {

	@Test
	public void shouldGetCorrectItemsToDisplay() {
		// given
		EventTypesFinder finderStub = new EventTypesFinder() {
			@Override
			public List<EventType> findAll(Optional<EventTypeRequestModel> requestModel) {
				return newArrayList(createDisplayableEventType(), createNonDisplayableEventType());
			}

		};
		TestPresenter presenterSpy = new TestPresenter();
		GetAllEventTypes command = new GetAllEventTypes(finderStub, presenterSpy, Optional.empty());

		// when
		command.execute();

		// then
		assertThat(presenterSpy.numberOfDisplayedItems()).isEqualTo(1);
	}

	@Test
	public void shouldHandleDBDown() {
		// given
		EventTypesFinder providerStub = new EventTypesFinder() {
			@Override
			public List<EventType> findAll(Optional<EventTypeRequestModel> requestModel) {
				throw new RuntimeException("DB down");
			}
		};
		TestPresenter presenterSpy = new TestPresenter();
		GetAllEventTypes command = new GetAllEventTypes(providerStub, presenterSpy, Optional.empty());

		// when
		command.execute();

		// then
		assertThat(presenterSpy.numberOfDisplayedItems()).isEqualTo(0);
		assertThat(presenterSpy.code).isEqualTo(MessageCode.INTERNAL_SERVER_ERROR);
	}

	class TestPresenter implements EventTypesListReceiver {

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

		public int numberOfDisplayedItems() {
			return isEmpty(events) ? 0 : events.size();
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

	boolean nonDisplayable = false;
	boolean displayable = true;
	long anyId1 = 1L;
	long anyId2 = 2L;
	String anyDesc = "lorem ipsum";

	private EventType createDisplayableEventType() {
		return createEventType(anyId1, anyDesc, displayable);
	}

	private EventType createNonDisplayableEventType() {
		return createEventType(anyId2, anyDesc, nonDisplayable);
	}

}
