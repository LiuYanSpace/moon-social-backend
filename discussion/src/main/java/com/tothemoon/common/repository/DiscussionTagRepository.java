package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DiscussionTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Auther: yyj
 * @Description:
 * @Date: 19/02/2024 19:44
 * @Version: v1.0
 */
public interface DiscussionTagRepository extends ListCrudRepository<DiscussionTag, DiscussionTag.Id> {
    List<DiscussionTag> findByDiscussionId(Long discussionId);
    Page<DiscussionTag> findByTagId(Long tagId, Pageable pageable);
}
