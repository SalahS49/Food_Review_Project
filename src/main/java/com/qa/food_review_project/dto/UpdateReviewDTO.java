package com.qa.food_review_project.dto;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateReviewDTO {


	@NotNull
	@Min(1)
	@Max(10)
	private int taste;

	@Size(min = 0, max = 250)
	private int appearance;

	@NotNull
	@Min(1)
	@Max(10)
	private int rating;

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

	@Override
	public String toString() {
		return "UpdateReviewDTO [taste=" + taste + ", appearance=" + appearance + ", rating=" + rating + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(appearance, rating, taste);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateReviewDTO other = (UpdateReviewDTO) obj;
		return appearance == other.appearance && rating == other.rating && taste == other.taste;
	}

}
