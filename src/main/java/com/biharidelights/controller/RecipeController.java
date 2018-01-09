package com.biharidelights.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biharidelights.error.ServiceError;
import com.biharidelights.model.Recipe;
import com.biharidelights.service.RecipeService;

@RestController
@SessionAttributes("recipe")
public class RecipeController {
	
	Logger LOGGER = Logger.getLogger(RecipeController.class.getName());
	
	@Autowired
	RecipeService recipeService;
	
	@PostMapping(value = "/recipes")
	public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe)  {
		Recipe recipeCreated = recipeService.addRecipe(recipe);
		return ResponseEntity.ok().body(recipeCreated);
		
	}
	
	@PostMapping(value = "/recipes/{recipeId}/ingredients")
	public String addIngredient() {
		
		return "";
	}
			
	@GetMapping(value = "/recipes/{recipeID}")
	public ResponseEntity<Recipe> viewRecipe(@Valid @PathVariable("recipeID") Long recipeID)  {
		LOGGER.info("****request recieved to get recipe for id: " + recipeID);
		Recipe recipe = recipeService.viewRecipe(recipeID);
		return ResponseEntity.ok().body(recipe);
	}
	
	@PutMapping(value = "/recipes/{recipeID}")
	public ResponseEntity<Recipe> updateRecipe(@Valid @PathVariable("recipeID") Long recipeID,
			@Valid @RequestBody Recipe recipe)  {
		LOGGER.info("****request recieved to get recipe for id: " + recipeID);
		Recipe recipeFromDB = recipeService.viewRecipe(recipeID);
		if(recipeFromDB == null) {
			LOGGER.info("Recipe Not Found: " + recipeID);
			return ResponseEntity.notFound().build();
		}
		recipeFromDB.setDescription(recipe.getDescription());
		recipeFromDB.setIngredients(recipe.getIngredients());
		recipeFromDB.setName(recipe.getName());
		recipeFromDB.setServing(recipe.getServing());
		Recipe updatedRecipe = recipeService.updateRecipe(recipeFromDB);
		return ResponseEntity.ok(updatedRecipe);
	}
	
	@DeleteMapping(value = "/recipes/{recipeID}")
	public ResponseEntity<Recipe> deleteRecipe(@Valid @PathVariable("recipeID") Long recipeID)  {
		LOGGER.info("****request recieved to get recipe for id: " + recipeID);
		Recipe recipeFromDB = recipeService.viewRecipe(recipeID);
		if(recipeFromDB == null) {
			LOGGER.info("Recipe Not Found: " + recipeID);
			return ResponseEntity.notFound().build();
		}
		
		recipeService.deleteRecipe(recipeFromDB);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/recipes")
	public List<Recipe> viewRecipe()  {
		return recipeService.viewAllRecipe();
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ServiceError> handle(RuntimeException ex) {
		ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
}
