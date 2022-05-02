package com.qa.food_review_project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.qa.food_review_project.dto.NewRestaurantDTO;
import com.qa.food_review_project.dto.RestaurantDTO;
import com.qa.food_review_project.entity.Restaurant;
import com.qa.food_review_project.repo.RestaurantRepo;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceUnitTest {
	@Mock
	private RestaurantRepo restaurantRepo;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private RestaurantService restaurantService;

	private List<Restaurant> restaurants;
	private List<RestaurantDTO> restaurantDTOs;

	@BeforeEach
	public void init() {
		restaurants = List.of(new Restaurant(1, "GBK", "Dessert", "Milkshake", 5.00),
				new Restaurant(2, "Weatherspoons", "British", "Fish&Chips", 13.00));
		restaurantDTOs = List.of(new RestaurantDTO(1, "GBK", "Dessert", "Milkshake", 5.00),
				new RestaurantDTO(2, "Weatherspoons", "British", "Fish&Chips", 13.00));
	}

	@Test
	public void getAllTest() {
		
		when(restaurantRepo.findAll()).thenReturn(restaurants);
		when(modelMapper.map(restaurants.get(0), RestaurantDTO.class)).thenReturn(restaurantDTOs.get(0));
		when(modelMapper.map(restaurants.get(1), RestaurantDTO.class)).thenReturn(restaurantDTOs.get(1));

		
		List<RestaurantDTO> actual = restaurantService.getRestaurant();

		
		assertEquals(restaurantDTOs, actual);
		verify(restaurantRepo).findAll();
		verify(modelMapper).map(restaurants.get(0), RestaurantDTO.class);
		verify(modelMapper).map(restaurants.get(1), RestaurantDTO.class);
	}

	@Test
	public void getByIdTest() {
		
		Restaurant restaurant = restaurants.get(0);
		RestaurantDTO restaurantDTO = restaurantDTOs.get(0);
		int id = restaurant.getId();

		when(restaurantRepo.findById(id)).thenReturn(Optional.of(restaurant));
		when(modelMapper.map(restaurant, RestaurantDTO.class)).thenReturn(restaurantDTO);

		
		RestaurantDTO actual = restaurantService.getRestaurant(id);

		
		assertEquals(restaurantDTO, actual);
		verify(restaurantRepo).findById(id);
		verify(modelMapper).map(restaurant, RestaurantDTO.class);
	}

	@Test
	public void getByInvalidIdTest() {
		
		int id = 67;
		when(restaurantRepo.findById(id)).thenReturn(Optional.empty());

		
		EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			restaurantService.getRestaurant(id);
		});

		
		String expectedMessage = "Restaurant not found with id " + id;
		assertEquals(expectedMessage, exception.getMessage());
		verify(restaurantRepo).findById(id);
	}

	@Test
	public void createTest() {
		
		Restaurant restaurant = restaurants.get(0);

		NewRestaurantDTO restaurantDTO = new NewRestaurantDTO();
		restaurantDTO.setRestaurant(restaurant.getRestaurant());
		restaurantDTO.setFoodType(restaurant.getFoodType());
		restaurantDTO.setFood(restaurant.getFood());
		restaurantDTO.setPrice(restaurant.getPrice());

		RestaurantDTO newRestaurant = new RestaurantDTO(restaurant.getId(), restaurant.getRestaurant(), restaurant.getFoodType(), restaurant.getFood(),
				restaurant.getPrice());

		when(modelMapper.map(restaurantDTO, Restaurant.class)).thenReturn(restaurant);
		when(restaurantRepo.save(restaurant)).thenReturn(restaurant);
		when(modelMapper.map(restaurant, RestaurantDTO.class)).thenReturn(newRestaurant);

		
		RestaurantDTO actual = restaurantService.createRestaurant(restaurantDTO);

		
		assertEquals(newRestaurant, actual);
		verify(modelMapper).map(restaurantDTO, Restaurant.class);
		verify(restaurantRepo).save(restaurant);
		verify(modelMapper).map(restaurant, RestaurantDTO.class);
	}

	@Test
	public void updateTest() {

		Restaurant restaurant = restaurants.get(0);
		int id = restaurant.getId();
		NewRestaurantDTO newRestaurantDTO = new NewRestaurantDTO(restaurant.getRestaurant(), restaurant.getFoodType(), restaurant.getFood(),
				restaurant.getPrice());
		RestaurantDTO expected = new RestaurantDTO(restaurant.getId(), restaurant.getRestaurant(), restaurant.getFoodType(), restaurant.getFood(),
				restaurant.getPrice());

		when(restaurantRepo.existsById(id)).thenReturn(true);
		when(restaurantRepo.getById(id)).thenReturn(restaurant);
		when(restaurantRepo.save(restaurant)).thenReturn(restaurant);
		when(modelMapper.map(restaurant, RestaurantDTO.class)).thenReturn(expected);

		RestaurantDTO updated = restaurantService.updateRestaurant(newRestaurantDTO, id);

		assertEquals(expected, updated);
		verify(restaurantRepo).existsById(id);
		verify(restaurantRepo).getById(id);
		verify(restaurantRepo).save(restaurant);
		verify(modelMapper).map(restaurant, RestaurantDTO.class);

	}
}
