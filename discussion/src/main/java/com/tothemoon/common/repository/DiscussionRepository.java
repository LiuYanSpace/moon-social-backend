package com.tothemoon.common.repository;

import com.tothemoon.common.entity.Discussion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName:DiscussionRepository
 * @Auther: yyj
 * @Description:
 * @Date: 19/02/2024 19:43
 * @Version: v1.0
 */
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    Page<Discussion> findByIsStickyFalseAndIsPrivateFalseAndIsApprovedTrue(Pageable pageable);

    List<Discussion> findByIsStickyTrueAndIsPrivateFalseAndIsApprovedTrueOrderByLastPostedAtDesc();
}
