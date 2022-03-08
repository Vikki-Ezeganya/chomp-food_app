package com.vikki.chompfooddelivery.dto.response;

import lombok.Data;

@Data
public class MenuItemResponse {
    private String name;
    private Long price;
    private String description;
    private String image;
    private String category;

}
