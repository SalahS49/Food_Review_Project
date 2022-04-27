package com.qa.food_review_project.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.food_review_project.dto.NewRestaurantDTO;
import com.qa.food_review_project.dto.RestaurantDTO;
import com.qa.food_review_project.dto.ReviewDTO;
import com.qa.food_review_project.service.RestaurantService;

@RestController
@RequestMapping(path = "restaurant")
public class RestaurantController {
	private RestaurantService restaurantService;

	@Autowired
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@GetMapping
	public ResponseEntity<List<RestaurantDTO>> getRestaurant() {
		List<RestaurantDTO> responseBody = this.restaurantService.getRestaurant();
		return new ResponseEntity<List<RestaurantDTO>>(responseBody, HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable(name = "id") int id) {
		RestaurantDTO restaurant = restaurantService.getRestaurant(id);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}

	@GetMapping(path = "{id}reviews")
	public ResponseEntity<List<ReviewDTO>> getRestaurantReviews(@PathVariable(name = "id") int restaurantId) {
		return ResponseEntity.ok(restaurantService.getRestaurantReviews(restaurantId));
	}

	@PostMapping
	public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody NewRestaurantDTO restaurant) {
		RestaurantDTO newRestaurant = restaurantService.createRestaurant(restaurant);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/restaurant/" + newRestaurant.getId());

		return new ResponseEntity<>(newRestaurant, headers, HttpStatus.CREATED);
	}

	@PutMapping(path = "{id}")
	public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestBody NewRestaurantDTO newRestaurantDTO,
			@PathVariable(name = "id") int id) {
		RestaurantDTO restaurantChange = this.restaurantService.updateRestaurant(newRestaurantDTO, id);
		return new ResponseEntity<RestaurantDTO>(restaurantChange, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<RestaurantDTO> deleteRestaurant(@PathVariable(name = "id") int id) {
		RestaurantDTO deletedRestaurant = restaurantService.getRestaurant(id);
		restaurantService.deleteRestaurant(id);
		return ResponseEntity.ok(deletedRestaurant);
	}


}

