package com.tothemoon.common.repository;

import com.tothemoon.common.entity.PostLike;
import com.tothemoon.common.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName:PostRepository
 * @Auther: yyj
 * @Description:
 * @Date: 19/02/2024 19:44
 * @Version: v1.0
 */
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    List<PostLike> findByUserIdAndPostId(long userId, long postId);
}
