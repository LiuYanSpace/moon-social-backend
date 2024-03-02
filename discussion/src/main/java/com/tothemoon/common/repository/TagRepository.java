package com.tothemoon.common.repository;

import com.tothemoon.common.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName:PostRepository
 * @Auther: yyj
 * @Description:
 * @Date: 19/02/2024 19:44
 * @Version: v1.0
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("SELECT t FROM Tag t WHERE t.parentTag IS NULL")
    List<Tag> getParentTag();
}
