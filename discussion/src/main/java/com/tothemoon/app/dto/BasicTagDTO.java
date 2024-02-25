package com.tothemoon.app.dto;

import lombok.Data;


@Data
public class BasicTagDTO {
    private Long id;
    private String name;
    private String slug;
    private String color;
    private String icon;
}
