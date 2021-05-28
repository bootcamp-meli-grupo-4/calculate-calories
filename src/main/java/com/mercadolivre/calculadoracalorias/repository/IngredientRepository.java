package com.mercadolivre.calculadoracalorias.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.calculadoracalorias.domain.Ingredient;
import com.mercadolivre.calculadoracalorias.excpetions.IngredientNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepository {

    private List<Ingredient> ingredients;

    @PostConstruct
    private void loadDataBase() throws IOException {
        File pratoDataBase = getDabaBase();
        if(pratoDataBase.length() != 0) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Ingredient>> pratoReference = new TypeReference<>() {
            };
            this.ingredients = mapper.readValue(pratoDataBase, pratoReference);
        }
    }

    public Ingredient findIngredient(String name){
        Optional<Ingredient> first = ingredients.stream()
                .filter(ingredient -> name.equals(ingredient.getName()))
                .findFirst();
        return first.orElseThrow(IngredientNotFoundException::new);
    }

    private File getDabaBase() throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:static/food.json");
    }
}
