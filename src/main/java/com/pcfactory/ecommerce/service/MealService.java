package com.pcfactory.ecommerce.service;

import com.pcfactory.ecommerce.dto.Category;
import com.pcfactory.ecommerce.dto.CategoryResponse;
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
}
