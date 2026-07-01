package com.pcfactory.ecommerce.dto;

import lombok.Data;

@Data
public class WebpayCreateRequest {
    private String buy_order;
    private String session_id;
    private Double amount;
    private String return_url;
}