package com.github.andbed.cleanarch.eventtype.delivery.rest;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.github.andbed.cleanarch.common.Command;
import com.github.andbed.cleanarch.common.MessageCode;
import com.github.andbed.cleanarch.eventtype.core.GetAllEventTypes;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeFactory;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypeRequestModel;
import com.github.andbed.cleanarch.eventtype.core.boundary.EventTypesListReceiver;

public class EventTypesListControllerTest {

	String URL = "http://localhost" + EventTypesController.URL;

	@Test
	public void shouldReturnJSONList() throws Exception {
		// given
		EventTypesController controller = new EventTypesController(withCommandReturningCorrectResult());
		MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

		// when
		mvc.perform(get(URL).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				// then
				.andExpect(status().isOk())
		/*
		 * .andExpect(jsonPath("$.content[1].pk").value(2))
		 * .andExpect(jsonPath("$.content[1].name").exists())
		 */;
	}

	@Test
	public void shouldReturnProperErrorCode() throws Exception {
		// given
		EventTypesController controller = new EventTypesController(withCommandThrowingError());
		MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

		// when
		mvc.perform(get(URL).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				// then
				.andExpect(status().is5xxServerError());

	}

	private EventTypeFactory withCommandReturningCorrectResult() {
		return new EventTypeFactory() {
			@Override
			public Command createGetAllEventTypesCommand(EventTypesListReceiver presenter, Optional<EventTypeRequestModel> requestModel) {
				return new GetAllEventTypes(null, presenter, requestModel) {
					@Override
					public void execute() {
						receiver.sendResult(newArrayList());
					}

				};
			}
		};
	}

	private EventTypeFactory withCommandThrowingError() {
		return new EventTypeFactory() {
			@Override
			public Command createGetAllEventTypesCommand(EventTypesListReceiver presenter, Optional<EventTypeRequestModel> requestModel) {
				return new GetAllEventTypes(null, presenter, requestModel) {
					@Override
					public void execute() {
						receiver.sendMessage(MessageCode.INTERNAL_SERVER_ERROR);
					}

				};
			}
		};
	}

}
