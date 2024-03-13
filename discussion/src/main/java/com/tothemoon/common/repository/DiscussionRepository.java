package com.tothemoon.common.repository;

import com.tothemoon.common.entity.Discussion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @ClassName:DiscussionRepository
 * @Auther: yyj
 * @Description:
 * @Date: 19/02/2024 19:43
 * @Version: v1.0
 */
public interface DiscussionRepository extends CrudRepository<Discussion, Long> {
    Page<Discussion> findByIsStickyFalseAndIsPrivateFalseAndIsApprovedTrue(Pageable pageable);

    List<Discussion> findByIsStickyTrueAndIsPrivateFalseAndIsApprovedTrueOrderByLastPostedAtDesc();
}
