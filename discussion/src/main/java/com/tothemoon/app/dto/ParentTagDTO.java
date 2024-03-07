package com.tothemoon.app.dto;

import lombok.Data;

import java.util.List;
@Data
public class ParentTagDTO {
    private Long id;
    private Long parentId;
    private String name;
    private String slug;
    private String description;
    private String color;
    private String icon;
    private Integer position;
}
