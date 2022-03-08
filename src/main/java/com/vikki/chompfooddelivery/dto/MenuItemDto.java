package com.vikki.chompfooddelivery.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MenuItemDto {

    private String menuId;
    private String name;
    private Long price;
    private String description;
    private String image;
    private String category;
    private Date dateCreated;

}
