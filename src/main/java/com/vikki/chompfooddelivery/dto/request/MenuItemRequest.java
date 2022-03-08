package com.vikki.chompfooddelivery.dto.request;

import lombok.Data;

@Data
public class MenuItemRequest {

    private String name;
    private Long price;
    private String description;
    private String image;
    private String category;
}
