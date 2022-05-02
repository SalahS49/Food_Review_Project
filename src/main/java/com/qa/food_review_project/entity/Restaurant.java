package com.qa.food_review_project.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	// @NotBlank
	private String restaurant;

	@NotNull
	private String foodType;

	@NotNull
	private String food;

	@NotNull
	private double price;

	@OneToMany
	private List<Review> reviews;

	public Restaurant() {
		super();
		this.reviews = new ArrayList<>();
	}

	public Restaurant(String restaurant, String foodType, String food, double price) {
		super();
		this.restaurant = restaurant;
		this.foodType = foodType;
		this.food = food;
		this.price = price;
		this.reviews = new ArrayList<>();
	}

	public Restaurant(int id, String restaurant, String foodType, String food, double price) {
		super();
		this.id = id;
		this.restaurant = restaurant;
		this.foodType = foodType;
		this.food = food;
		this.price = price;
		this.reviews = new ArrayList<>();
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", restaurant=" + restaurant + ", foodType=" + foodType + ", food=" + food
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
		Restaurant other = (Restaurant) obj;
		return Objects.equals(food, other.food) && Objects.equals(foodType, other.foodType) && id == other.id
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(restaurant, other.restaurant);
	}

	

}
