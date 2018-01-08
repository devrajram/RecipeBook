package com.biharidelights.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biharidelights.model.Ingredient;
import com.biharidelights.repository.IngredientJpaRepository;

@Service
public class IngredientService {
	@Autowired
	IngredientJpaRepository ingJpaRepo;
	
	public Long addIngredient(Ingredient ing) {
		Ingredient savedIngredient = ingJpaRepo.saveAndFlush(ing);
		return savedIngredient.getId();
		
	}

}
