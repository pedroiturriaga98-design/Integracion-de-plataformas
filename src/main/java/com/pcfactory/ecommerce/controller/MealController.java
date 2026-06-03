package com.pcfactory.ecommerce.controller;

import com.pcfactory.ecommerce.dto.Category;
import com.pcfactory.ecommerce.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping("/categories")
    public Mono<List<Category>> getCategories() {

        return mealService.getCategories();
    }
}
