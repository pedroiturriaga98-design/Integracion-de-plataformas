package com.pcfactory.ecommerce.service;

import com.pcfactory.ecommerce.dto.Category;
import com.pcfactory.ecommerce.dto.CategoryResponse;
import com.pcfactory.ecommerce.dto.MealResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final WebClient webClient;

    public Mono<List<Category>> getCategories() {

        return webClient.get()
                .uri("/categories.php")
                .retrieve()
                .bodyToMono(CategoryResponse.class)
                .map(CategoryResponse::getCategories);
    }


    public Mono<MealResponseWrapper> buscarPorNombre(String nombre) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("search.php").queryParam("s",nombre).build())
                .retrieve()
                .bodyToMono(MealResponseWrapper.class);
    }


    public Mono<MealResponseWrapper> buscarPorPrimeraLetra(String letra) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("search.php").queryParam("f",letra).build())
                .retrieve()
                .bodyToMono(MealResponseWrapper.class);

    }


    public Mono<MealResponseWrapper> buscarPorId(String id) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("lookup.php").queryParam("i",id).build())
                .retrieve()
                .bodyToMono(MealResponseWrapper.class);
    }


    public Mono<MealResponseWrapper> buscarPorIngrediente(String ingrediente) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("filter.php").queryParam("i",ingrediente).build())
                .retrieve()
                .bodyToMono(MealResponseWrapper.class);
    }

}
