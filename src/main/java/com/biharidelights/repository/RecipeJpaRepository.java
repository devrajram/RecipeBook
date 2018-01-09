package com.biharidelights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biharidelights.model.Recipe;

@Repository
public interface RecipeJpaRepository extends JpaRepository<Recipe, Long> {

}
