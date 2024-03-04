package com.bird.utils;

import com.bird.dto.Pagination;
import org.springframework.data.domain.Page;
import java.util.List;

public class PaginationUtils {

    public static <T> Pagination wrapPagination(Page<T> page, List<?> content) {
        Pagination pagination = new Pagination();
        pagination.setSize(page.getSize());
        pagination.setCurrPage(page.getNumber());
        pagination.setTotalElements(page.getTotalElements());
        pagination.setTotalPages(page.getTotalPages());
        pagination.setContent(content);
        return pagination;
    }

}
