package com.bird.dto;

import lombok.Data;

import java.util.List;

@Data
public class Pagination {
    private int currPage;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<?> content;
}
