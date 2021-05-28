package com.mercadolivre.calculadoracalorias.services;

import com.mercadolivre.calculadoracalorias.domain.Ingredient;
import com.mercadolivre.calculadoracalorias.domain.dto.PlateCalorieDTO;
import com.mercadolivre.calculadoracalorias.domain.dto.PlateIngredientsDTO;
import com.mercadolivre.calculadoracalorias.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public PlateCalorieDTO getCalories(PlateIngredientsDTO plateIngredientDTO) {
        Integer reduce = plateIngredientDTO.getIngredients().stream()
                .map(ingredientDTO -> ingredientRepository.findIngredient(ingredientDTO.getName()))
                .map(Ingredient::getCalories)
                .reduce(0, Integer::sum);
        return new PlateCalorieDTO(plateIngredientDTO.getName(),reduce);
    }

    public List<Ingredient> findIngredient(PlateIngredientsDTO plateIngredientDTO) {
        return plateIngredientDTO.getIngredients().stream()
                .map(ingredientDTO -> ingredientRepository.findIngredient(ingredientDTO.getName()))
                .collect(Collectors.toList());
    }

    public Ingredient findBigger(PlateIngredientsDTO plateIngredientDTO) {
        List<Ingredient> ingredients = findIngredient(plateIngredientDTO);
        return ingredients.stream().max(Comparator.comparing(Ingredient::getCalories)).get();
    }
}
