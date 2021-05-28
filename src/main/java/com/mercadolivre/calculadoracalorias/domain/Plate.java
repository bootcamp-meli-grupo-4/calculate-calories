package com.mercadolivre.calculadoracalorias.domain;

import lombok.Data;

import java.util.List;
@Data
public class Plate {
    private String nome;
    private List<Ingredient> ingredients;
}
