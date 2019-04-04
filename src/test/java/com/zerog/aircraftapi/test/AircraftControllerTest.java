package com.zerog.aircraftapi.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
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
import com.zerog.aircraft.model.Aircraft;
import com.zerog.aircraft.service.AircraftService;

@SpringBootTest(classes = { AircraftApplication.class })
@RunWith(SpringRunner.class)
public class AircraftControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private AircraftService aircraftService;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	// create ok
	@Test
	public void saveAircraftTest() throws Exception {
		int size = aircraftService.findAll().size();

		mockMvc.perform(MockMvcRequestBuilders.post("/aircrafts").content(TestUtil.fromPath("Aircraft/createAircraft"))
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated());

		mockMvc.perform(MockMvcRequestBuilders.get("/aircrafts"))
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(size + 1))).andExpect(status().isOk());
	}

	// created duplicated conflict
	@Test
	public void saveAircraftShouldReturnBadRequestTest() throws Exception {
		int size = aircraftService.findAll().size();

		mockMvc.perform(MockMvcRequestBuilders.post("/aircrafts").content(TestUtil.fromPath("Aircraft/updateAircraft"))
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isConflict());

		mockMvc.perform(MockMvcRequestBuilders.get("/aircrafts"))
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(size))).andExpect(status().isOk());
	}

	// find all ok
	@Test
	public void getAllAircraftTest() throws Exception {
		int size = aircraftService.findAll().size();

		mockMvc.perform(MockMvcRequestBuilders.get("/aircrafts"))
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(size))).andExpect(status().isOk());

	}

	// find by id ok
	@Test
	public void getByIdAircraftTest() throws Exception {
		if (aircraftService.findAll().size() > 0) {
			Aircraft aircraft = aircraftService.findAll().get(0);
			Long id = aircraft.getId();
			String name = aircraft.getName();

			mockMvc.perform(MockMvcRequestBuilders.get("/aircrafts/" + id))
					.andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo(name))).andExpect(status().isOk());
		}
	}

	// find by invalid id not found
	@Test
	public void getByInvalidIdAircraftTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/aircrafts/" + 12345)).andExpect(status().isNotFound());
	}

	// update ok
	@Test
	public void putAircraftTest() throws Exception {
		if (aircraftService.findAll().size() > 0) {
			Aircraft aircraft = aircraftService.findAll().get(0);
			String id = aircraft.getId().toString();

			mockMvc.perform(MockMvcRequestBuilders.put("/aircrafts/" + id)
					.content(TestUtil.fromPath("Aircraft/updateAircraft")).contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("bolicraft")))
					.andExpect(status().isOk());

		}
	}

	// update invalid id
	@Test
	public void updateAircraftShouldReturnNotFoundTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.put("/aircrafts/-1")
				.content(TestUtil.fromPath("Aircraft/updateAircraft")).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());

	}

	// delete ok
	@Test
	public void deleteAircraftOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/aircrafts/" + 1)).andExpect(status().isNoContent());
	}
	
	// delete invalid id
	@Test
	public void deleteAircraftShouldReturnNotFoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/aircrafts/-1")).andExpect(status().isNotFound());
	}
}
