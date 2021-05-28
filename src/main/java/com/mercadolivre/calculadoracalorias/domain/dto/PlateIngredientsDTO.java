package com.mercadolivre.calculadoracalorias.domain.dto;

import lombok.Data;

import java.util.List;
@Data
public class PlateIngredientsDTO {
    private String name;
    private List<IngredientDTO> ingredients;
}
