package com.biharidelights.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biharidelights.model.Recipe;
import com.biharidelights.service.RecipeService;

@RestController
public class RecipeController {
	
	@Autowired
	RecipeService recipeService;
	
	@PostMapping(value = "/recipes")
	public String addRecipe(@RequestParam Recipe recipe)  {
		
		return recipe.getName() + "recipe added";
		
	}
	
	@GetMapping(value = "/recipes/{recipeID}")
	public Recipe viewRecipe(@PathParam(value = "recipeID") Long recipeID)  {
		
		return recipeService.viewRecipe(recipeID);
		
	}
	
	@GetMapping(value = "/recipes")
	public List<Recipe> viewRecipe()  {
		return recipeService.viewAllRecipe();
	}
	
	

}
