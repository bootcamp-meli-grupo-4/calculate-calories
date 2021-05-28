package com.mercadolivre.calculadoracalorias.controller;

import com.mercadolivre.calculadoracalorias.domain.Ingredient;
import com.mercadolivre.calculadoracalorias.domain.dto.PlateCalorieDTO;
import com.mercadolivre.calculadoracalorias.domain.dto.PlateIngredientsDTO;
import com.mercadolivre.calculadoracalorias.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService){
        this.ingredientService = ingredientService;
    }

    @PostMapping("/calories")
    public ResponseEntity<PlateCalorieDTO> getCalories(@RequestBody PlateIngredientsDTO plateIngredientsDTO){
        ingredientService.getCalories(plateIngredientsDTO);
        PlateCalorieDTO plateCalorieDTO = ingredientService.getCalories(plateIngredientsDTO);
        return ResponseEntity.ok().body(plateCalorieDTO);
    }

    @PostMapping
    public ResponseEntity<List<Ingredient>> getIngredients(@RequestBody PlateIngredientsDTO plateIgredientDTO){
        List<Ingredient> ingredient = ingredientService.findIngredient(plateIgredientDTO);
        return ResponseEntity.ok().body(ingredient);
    }

    @PostMapping("/bigger")
    public ResponseEntity<Ingredient> getBiggerIngredient(@RequestBody PlateIngredientsDTO plateIgredientDTO){
        Ingredient ingredient = ingredientService.findBigger(plateIgredientDTO);
        return ResponseEntity.ok().body(ingredient);
    }
}
