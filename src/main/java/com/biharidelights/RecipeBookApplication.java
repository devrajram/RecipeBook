package com.biharidelights;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.biharidelights.model.Ingredient;
import com.biharidelights.model.Recipe;
import com.biharidelights.service.RecipeService;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EnableTransactionManagement
public class RecipeBookApplication implements CommandLineRunner {
	
	@Autowired
	RecipeService recipeService;
	
//	@Bean
//	@Autowired
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(String persistenceUnitName,
//			DataSource dataSource, JpaVendorAdapter jpaVendorAdapter, Map<String, ?> jpaPropertyMap) {
//		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
//		bean.setPersistenceUnitName(persistenceUnitName);
//		bean.setDataSource(dataSource);
//		bean.setJpaVendorAdapter(jpaVendorAdapter);
//		bean.setJpaPropertyMap(jpaPropertyMap);
//		
//		//bean.setPersistenceUnitName("punit");
//		return bean;
//	}
	
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;
	}
	
	
//	@Autowired
//	IngredientService ingredientService;
	
	public static void main(String[] args) {
		SpringApplication.run(RecipeBookApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		//insert value in table
		//First Recipe and its ingredient
		Ingredient ing1 = new Ingredient();
		ing1.setName("Chicken");
		ing1.setQuantity("1");
		ing1.setUnit("lb");
		
		Ingredient ing2 = new Ingredient();
		ing2.setName("Yogurt");
		ing2.setQuantity("1");
		ing2.setUnit("tsp");
		
		Recipe recipe = new Recipe();
		recipe.setName("Tandoori Chicken");
		recipe.setDescription("Marinate Chicken and put into oven for 20 mins.");
		//recipe.setId(1L);
		recipe.getIngredients().add(ing1);
		recipe.getIngredients().add(ing2);
		
		ing1.setRecipe(recipe);
		ing2.setRecipe(recipe);
		
		recipe.setServing("5");
		//recipe.setCreateDate(LocalDate.now().toString());
		//recipe.setLastUpdated(LocalDate.now().toString());
		
		
		recipeService.addRecipe(recipe);
		
		//ingredientService.addIngredient(ing1);
		//ingredientService.addIngredient(ing2);
		
		//second recipe and its ingredients
		Ingredient ing3 = new Ingredient();
		ing3.setName("Medium size boiled Potato");
		ing3.setQuantity("4");
		ing3.setUnit("pc");
		
		Ingredient ing4 = new Ingredient();
		ing4.setName("Chili");
		ing4.setQuantity("2");
		ing4.setUnit("pc");
		
		Recipe recipe2 = new Recipe();
		recipe2.setName("Aloo Paratha");
		recipe2.setDescription("Mash all potatoes. Add all ingredients and mix well. Fill this mixture in wheat dough and roll into a paratha. Put the rolled paratha on tava util it is cooked.");
		//recipe.setId(1L);
		recipe2.getIngredients().add(ing3);
		recipe2.getIngredients().add(ing4);
		
		ing3.setRecipe(recipe2);
		ing4.setRecipe(recipe2);
		
		recipe2.setServing("2");
		//recipe2.setCreateDate(LocalDate.now().toString());
		//recipe2.setLastUpdated(LocalDate.now().toString());
		//save recipe
		recipeService.addRecipe(recipe2);
		
		//Now save ingredients
		//ingredientService.addIngredient(ing3);
		//ingredientService.addIngredient(ing4);
		
	}
}
