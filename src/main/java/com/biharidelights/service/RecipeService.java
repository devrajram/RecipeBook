package com.biharidelights.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.biharidelights.model.Recipe;
import com.biharidelights.repository.RecipeJpaRepository;

@Service
public class RecipeService {

	@Autowired
	RecipeJpaRepository recipeJpaRepository;

	public Recipe viewRecipe(Long recipeID) {
		Recipe recipe = recipeJpaRepository.findOne(recipeID);
		if (recipe == null) {
			throw new DataAccessException("Recipe not found for id: " + recipeID) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
			};
		}
		return recipeJpaRepository.findOne(recipeID);
	}

	public List<Recipe> viewAllRecipe() {
		return recipeJpaRepository.findAll();
	}

	public List<Recipe> searchRecipe(String keyword) {
		List<Recipe> recipes = recipeJpaRepository.findAll();
		return recipes;
	}

	@Transactional
	public Recipe addRecipe(Recipe recipe) {
		Recipe r = recipeJpaRepository.saveAndFlush(recipe);
		handleException(r, "Error while creating a new recipe.");
		return r;
	}

	private void handleException(Recipe recipe, String msg) {
		if (recipe == null) {
			throw new DataAccessException(msg) {
				private static final long serialVersionUID = 1L;
			};
		}
	}

	@Transactional
	public Recipe updateRecipe(Recipe recipe) {
		Recipe recipeUpdated = recipeJpaRepository.saveAndFlush(recipe);
		handleException(recipeUpdated, "Error while updating recipe.");
		return recipeUpdated;
	}
	
	@Transactional
	public void deleteRecipe(Recipe recipe) {
		recipeJpaRepository.delete(recipe.getId());
	}

}
