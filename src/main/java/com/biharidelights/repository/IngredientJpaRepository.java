package com.biharidelights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biharidelights.model.Ingredient;

@Repository
public interface IngredientJpaRepository extends JpaRepository<Ingredient, Long> {

}
