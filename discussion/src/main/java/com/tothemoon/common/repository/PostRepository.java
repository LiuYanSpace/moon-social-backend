package com.tothemoon.common.repository;

import com.tothemoon.common.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName:PostRepository
 * @Auther: yyj
 * @Description:
 * @Date: 19/02/2024 19:44
 * @Version: v1.0
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByDiscussionId(Long discussionId, Pageable pageable);
}
