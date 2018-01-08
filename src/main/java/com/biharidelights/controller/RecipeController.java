package com.biharidelights.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biharidelights.model.Recipe;
import com.biharidelights.service.RecipeService;

@RestController
@SessionAttributes("recipe")
public class RecipeController {
	
	Logger LOGGER = Logger.getLogger(RecipeController.class.getName());
	
	@Autowired
	RecipeService recipeService;
	
	@PostMapping(value = "/recipes")
	public String addRecipe(@RequestParam Recipe recipe)  {
		
		return recipe.getName() + "recipe added";
		
	}
	
	@PostMapping(value = "/recipes/{recipeId}/ingredients")
	public String addIngredient() {
		
		return "";
	}
			
	@GetMapping(value = "/recipes/{recipeID}")
	public Recipe viewRecipe(@PathVariable("recipeID") String recipeID)  {
		LOGGER.info("****request recieved to get recipe for id: " + recipeID);
		return recipeService.viewRecipe(Long.valueOf(recipeID));
		
	}
	
	@GetMapping(value = "/recipes")
	public List<Recipe> viewRecipe()  {
		return recipeService.viewAllRecipe();
	}
	
	

}
