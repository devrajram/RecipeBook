package com.biharidelights.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

//@Embeddable
@Entity
public class Ingredient {
	@Id
	@GeneratedValue
	private
	Long id;
	
	//@ManyToOne(cascade=CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "recipe_id")
	@JsonBackReference
	Recipe recipe;
	
	String name;
	String quantity;
	String unit;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
public String getQuantity() {
		return quantity;
	}
		public Recipe getRecipe() {
		return recipe;
	}
	public String getUnit() {
		return unit;
	}
	public void setId(Long id) {
		this.id = id;
	}
public void setName(String name) {
		this.name = name;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
		public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

}
