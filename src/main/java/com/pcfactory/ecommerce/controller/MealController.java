package com.pcfactory.ecommerce.controller;

import com.pcfactory.ecommerce.dto.Category;
import com.pcfactory.ecommerce.dto.MealResponseWrapper;
import com.pcfactory.ecommerce.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @GetMapping("/buscar")
    public Mono<MealResponseWrapper> BuscarPorNombre(@RequestParam String nombre) {
        return mealService.buscarPorNombre(nombre);
    }

    @GetMapping("/letra")
    public Mono<MealResponseWrapper> BuscarPorPrimeraLetra(@RequestParam String letra) {
        return mealService.buscarPorPrimeraLetra(letra);
    }

    @GetMapping("/detalle")
    public Mono<MealResponseWrapper> BuscarPorId(@RequestParam String id) {
        return mealService.buscarPorId(id);
    }

    @GetMapping("/ingredientes")
    public Mono<MealResponseWrapper> BuscarPorIngrediente(@RequestParam String ingrediente) {
        return mealService.buscarPorIngrediente(ingrediente);
    }
}
