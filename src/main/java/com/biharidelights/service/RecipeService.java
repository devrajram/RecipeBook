package com.biharidelights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biharidelights.model.Recipe;
import com.biharidelights.repository.RecipeJpaRepository;

@Service
public class RecipeService {
	
	@Autowired
	RecipeJpaRepository recipeJpaRepository;
	
	public Recipe viewRecipe(Long recipeID) {
		return recipeJpaRepository.findOne(recipeID);
	}
	
	public List<Recipe> viewAllRecipe() {
		return recipeJpaRepository.findAll();
	}
	
	public List<Recipe> searchRecipe(String keyword) {
		List<Recipe> recipes = recipeJpaRepository.findAll();
		return recipes;
	}
	
	public Long addRecipe(Recipe recipe) {
		Recipe r = recipeJpaRepository.saveAndFlush(recipe);
		return r.getId();
	}
	
	public Long updateRecipe(Recipe recipe) {
		Recipe r = recipeJpaRepository.saveAndFlush(recipe);
		return r.getId();
	}
	
	public void deleteRecipe(Recipe recipe) {
		recipeJpaRepository.delete(recipe.getId());
	}
	
}
