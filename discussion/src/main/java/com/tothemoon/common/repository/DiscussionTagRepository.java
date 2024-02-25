package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DiscussionTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: yyj
 * @Description:
 * @Date: 19/02/2024 19:44
 * @Version: v1.0
 */
public interface DiscussionTagRepository extends JpaRepository<DiscussionTag, DiscussionTag.Id> {
    List<DiscussionTag> findByDiscussionId(Long discussionId);
}
