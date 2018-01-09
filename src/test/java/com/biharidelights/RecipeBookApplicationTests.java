package com.biharidelights;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.biharidelights.model.Ingredient;
import com.biharidelights.model.Recipe;

import org.junit.Assert;

@RunWith(SpringRunner.class)
// @SpringBootTest
public class RecipeBookApplicationTests {

	// @Test
	public void contextLoads() {
	}

	@Test
	public void testView() {
		RestTemplate template = new RestTemplate();
		Recipe r = template.getForObject("http://localhost:8080/recipes/1", Recipe.class);
		Assert.assertEquals("Recipe not found", "1", String.valueOf(r.getId()));
	}

	@Test
	public void testCreate() {
		RestTemplate template = new RestTemplate();
		Recipe recipe = getRecipe();
		Recipe recipeCreated = template.postForObject("http://localhost:8080/recipes", recipe, Recipe.class);
		Assert.assertEquals("***Recipe not saved to database", recipe.getName(), recipeCreated.getName());
	}
	
	//@Test
	public void testUpdate() {
		RestTemplate template = new RestTemplate();
		Recipe recipe = getRecipe();
		Recipe recipeCreated = template.postForObject("http://localhost:8080/recipes/1", recipe, Recipe.class);
		Assert.assertEquals("***Recipe not saved to database", recipe.getName(), recipeCreated.getName());
	}

	private Recipe getRecipe() {
		// insert value in table
		// First Recipe and its ingredient
		Ingredient ing1 = new Ingredient();
		ing1.setName("Mango");
		ing1.setQuantity("1");
		ing1.setUnit("pc");

		Ingredient ing2 = new Ingredient();
		ing2.setName("Yogurt");
		ing2.setQuantity("2");
		ing2.setUnit("tbs");

		Ingredient ing3 = new Ingredient();
		ing2.setName("Sugar");
		ing2.setQuantity("2");
		ing2.setUnit("tsp");

		Recipe recipe = new Recipe();
		recipe.setName("Mango Lassi");
		recipe.setDescription("Peel Mango, mix all ingredient and grind. Put it in fridge for an hour. Serve cold.");
		// recipe.setId(1L);
		recipe.getIngredients().add(ing1);
		recipe.getIngredients().add(ing2);
		recipe.getIngredients().add(ing3);

		ing1.setRecipe(recipe);
		ing2.setRecipe(recipe);
		ing3.setRecipe(recipe);

		recipe.setServing("2");

		return recipe;
	}

}
