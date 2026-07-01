package com.pcfactory.ecommerce.dto;

import lombok.Data;

@Data
public class WebpayCreateResponse {
    private String token;
    private String url;
}