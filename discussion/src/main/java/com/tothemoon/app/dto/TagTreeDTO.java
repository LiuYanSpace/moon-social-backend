package com.tothemoon.app.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TagTreeDTO {
    private Long id;
    private Long parentId;
    private String name;
    private String slug;
    private String description;
    private String color;
    private String icon;
    private Integer position;
    private List<TagTreeDTO> children;
}
