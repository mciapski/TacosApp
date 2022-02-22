package com.Taco.Taco.data;

import com.Taco.Taco.Ingredient;

public interface IngredientRepository {
  Iterable<Ingredient> findAll();
  Ingredient findById(String id);
  Ingredient save(Ingredient ingredient);

}
