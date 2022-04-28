package com.qa.food_review_project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.food_review_project.dto.NewRestaurantDTO;
import com.qa.food_review_project.dto.RestaurantDTO;
import com.qa.food_review_project.service.RestaurantService;

@WebMvcTest(RestaurantController.class)
@ActiveProfiles({ "test" })
public class RestaurantControllerWebLayerIntegrationTest {
	@MockBean 
	private RestaurantService restaurantService;

	@Autowired
	private RestaurantController restaurantController;

	private List<RestaurantDTO> restaurantDTOs;

	@BeforeEach
	public void init() {
		restaurantDTOs = List.of(new RestaurantDTO(1, "GBK", "Dessert", "Milkshake", 5.00),
				new RestaurantDTO(2, "Weatherspoons", "British", "Fish&Chips", 13.00));
	}

	@Test
	public void getAllRestaurantsTest() {
		
		ResponseEntity<?> expected = ResponseEntity.ok(restaurantDTOs);
		when(restaurantService.getRestaurant()).thenReturn(restaurantDTOs);

		
		ResponseEntity<?> actual = restaurantController.getRestaurant();

		
		assertEquals(expected, actual);
		verify(restaurantService).getRestaurant();
	}

	@Test
	public void createRestaurantTest() {
		
		NewRestaurantDTO newRestaurant = new NewRestaurantDTO();
		newRestaurant.setRestaurant("Hardrock");
		newRestaurant.setFoodType("American");
		newRestaurant.setFood("Burger");
		newRestaurant.setPrice(20.00);

		RestaurantDTO expectedRestaurant = new RestaurantDTO(1, newRestaurant.getRestaurant(), newRestaurant.getFoodType(), newRestaurant.getFood(),
				newRestaurant.getPrice());
		ResponseEntity<?> expected = ResponseEntity
				.created(URI.create("http://localhost:8080/restaurant/" + expectedRestaurant.getId())).body(expectedRestaurant);

		when(restaurantService.createRestaurant(newRestaurant)).thenReturn(expectedRestaurant);

		
		ResponseEntity<?> actual = restaurantController.createRestaurant(newRestaurant);

		
		assertEquals(expected, actual);
		verify(restaurantService).createRestaurant(newRestaurant);
	}
}
