package com.tothemoon.common.repository;

import com.tothemoon.common.entity.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

/**
 * @ClassName:PostRepository
 * @Auther: yyj
 * @Description:
 * @Date: 19/02/2024 19:44
 * @Version: v1.0
 */
public interface TagRepository extends ListCrudRepository<Tag, Long> {
    List<Tag> findByParentTagIsNullAndPositionIsNotNull(Sort sort);

    List<Tag> findByPositionIsNotNull(Sort position);
}
