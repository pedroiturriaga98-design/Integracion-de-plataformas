package com.pcfactory.ecommerce.dto;


import lombok.Data;
import java.util.List;


@Data
public class MealResponseWrapper {

    private List<MealDto> meals;

}
