package com.zerog.aircraftapi.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.zerog.aircraft.AircraftApplication;

@SpringBootTest(classes = { AircraftApplication.class })
@RunWith(SpringRunner.class)
public class AircraftControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getAllAircraftTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/aircraft").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").isNotEmpty());
	}

	@Test
	public void postAircraftTest() throws Exception {
		String todayAsString = TestUtil.getTodayAsString();

		mockMvc.perform(MockMvcRequestBuilders.post("/aircraft").accept(MediaType.APPLICATION_JSON)
				.param("name", "New Aircraft Name").param("serialNumber", "100").param("capacity", "20")
				.param("manufactureDate", todayAsString).param("weight", "900")).andDo(print())
				.andExpect(status().isCreated());
	}

	@Test
	public void postAircraftShouldReturnBadRequestTest() throws Exception {
		String todayAsString = TestUtil.getTodayAsString();

		mockMvc.perform(MockMvcRequestBuilders.post("/aircraft").accept(MediaType.APPLICATION_JSON)
				.param("name", "New Aircraft Name").param("manufactureDate", todayAsString)).andDo(print())
				.andExpect(status().isBadRequest());
	}

}
