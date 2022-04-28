package com.qa.food_review_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.food_review_project.dto.NewRestaurantDTO;
import com.qa.food_review_project.dto.RestaurantDTO;
import com.qa.food_review_project.entity.Restaurant;
import com.qa.food_review_project.repo.RestaurantRepo;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({ "test" })
@Sql(scripts = { "classpath:schema.sql"})
public class RestaurantControllerSystemIntegrationTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestaurantRepo restaurantRepo;

	private List<Restaurant> savedRestaurant;
	private List<RestaurantDTO> savedRestaurantDTOs = new ArrayList<>();
	private int nextId;
	private String uri;

	@BeforeEach
	public void init() {
		savedRestaurant = restaurantRepo.findAll();
		savedRestaurant.forEach(restaurant -> savedRestaurantDTOs.add(modelMapper.map(restaurant, RestaurantDTO.class)));
		nextId = savedRestaurant.get(savedRestaurant.size() - 1).getId() + 1;
		uri = "/restaurant";
	}

	@Test
	public void getAllUsersTest() throws Exception {

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.request(HttpMethod.GET, uri);

		request.accept(MediaType.APPLICATION_JSON);

		String users = objectMapper.writeValueAsString(savedRestaurantDTOs);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(users);

		mockMvc.perform(request).andExpectAll(statusMatcher, contentMatcher);
	}

	@Test
	public void createRestaurantTest() throws Exception {
		NewRestaurantDTO newRestaurant = new NewRestaurantDTO();
		newRestaurant.setRestaurant("Pizza_Express");
		newRestaurant.setFoodType("Italian");
		newRestaurant.setFood("Pizza");
		newRestaurant.setPrice(15.00);
		RestaurantDTO expectedRestaurant = new RestaurantDTO(nextId, newRestaurant.getRestaurant(), newRestaurant.getFoodType(),
				newRestaurant.getFood(), newRestaurant.getPrice());

		var request = MockMvcRequestBuilders.request(HttpMethod.POST, uri);

		request.accept(MediaType.APPLICATION_JSON);

		request.content(objectMapper.writeValueAsString(newRestaurant));
		request.contentType(MediaType.APPLICATION_JSON);

		String expectedBody = objectMapper.writeValueAsString(expectedRestaurant);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher locationMatcher = MockMvcResultMatchers.header().string("Location",
				"http://localhost:8080/restaurant/" + expectedRestaurant.getId());
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(expectedBody);

		mockMvc.perform(request).andExpectAll(statusMatcher, locationMatcher, contentMatcher);
	}

}

