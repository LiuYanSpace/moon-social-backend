package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DiscussionViews;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface DiscussionViewsRepository extends ListCrudRepository<DiscussionViews, Long> {

    List<DiscussionViews> findByUserId(Long userId);
}