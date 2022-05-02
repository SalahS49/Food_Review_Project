package com.qa.food_review_project.dto;

import java.util.Objects;

public class ReviewDTO {
	
	private int id;
	

	private int taste;
	
	
	private int appearance;
	
	
	private int rating;
	
	private RestaurantDTO restaurant;
	
	public ReviewDTO() {
		
	}
	
	public ReviewDTO(int id, int taste, int appearance, int rating, RestaurantDTO restaurant) {
		super();
		this.id = id;
		this.taste = taste;
		this.appearance = appearance;
		this.rating = rating;
		this.restaurant = restaurant;
	}

	public ReviewDTO(int taste, int appearance, int rating) {
		super();
		this.taste = taste;
		this.appearance = appearance;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaste() {
		return taste;
	}

	public void setTaste(int taste) {
		this.taste = taste;
	}

	public int getAppearance() {
		return appearance;
	}

	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public RestaurantDTO getRestaurantDTO() {
		return restaurant;
	}

	public void setRestaurantDTO(RestaurantDTO restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", taste=" + taste + ", appearance=" + appearance + ", rating=" + rating
				+ ", restaurantDTO=" + restaurant + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(appearance, id, rating, restaurant, taste);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewDTO other = (ReviewDTO) obj;
		return appearance == other.appearance && id == other.id && rating == other.rating
				&& Objects.equals(restaurant, other.restaurant) && taste == other.taste;
	}
	
	
	
	
}
