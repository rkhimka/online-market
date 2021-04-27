package com.test.shop.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestBuilder {
    private String name;
    private int price;
    private int quantity;
    private boolean available;
}
