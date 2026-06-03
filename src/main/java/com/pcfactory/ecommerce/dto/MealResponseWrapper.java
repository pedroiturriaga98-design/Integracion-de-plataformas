package com.pcfactory.ecommerce.dto;

import java.util.List;

public class MealResponseWrapper {

    private List<MealDto> meals;

    public List<MealDto> getMeals() {return meals;}
    public void setMeals(List<MealDto> meals) {this.meals = meals;}

}
