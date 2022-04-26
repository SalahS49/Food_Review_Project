package com.qa.food_review_project.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RestaurantDTO {

	private int id;

	@NotNull
	@NotBlank
	private String restaurant;

	@NotNull
	private String foodType;

	@NotNull
	private String food;

	@NotNull
	private double price;
	
	public RestaurantDTO(int id, String restaurant, String foodType, String food, double price) {
		super();
		this.id = id;
		this.restaurant = restaurant;
		this.foodType = foodType;
		this.food = food;
		this.price = price;
		
	}

	public RestaurantDTO(String restaurant, String foodType, String food, double price) {
		super();
		this.restaurant = restaurant;
		this.foodType = foodType;
		this.food = food;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "RestaurantDTO [id=" + id + ", restaurant=" + restaurant + ", foodType=" + foodType + ", food=" + food
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(food, foodType, id, price, restaurant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantDTO other = (RestaurantDTO) obj;
		return Objects.equals(food, other.food) && Objects.equals(foodType, other.foodType) && id == other.id
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(restaurant, other.restaurant);
	}

	
	
	
}
