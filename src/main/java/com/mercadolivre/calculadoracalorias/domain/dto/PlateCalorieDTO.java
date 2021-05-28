package com.mercadolivre.calculadoracalorias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlateCalorieDTO {
    private String name;
    private Integer calories;
}
