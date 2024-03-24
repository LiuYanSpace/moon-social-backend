package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DiscussionViews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionViewsRepository extends JpaRepository<DiscussionViews, Long> {

    List<DiscussionViews> findByUserId(Long userId);
}
