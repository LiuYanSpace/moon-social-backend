package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DiscussionCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DiscussionCollectionRepository extends CrudRepository<DiscussionCollection, Long> {
    Page<DiscussionCollection> findByUserId(Long userId, Pageable pageable);
    Page<DiscussionCollection> findByUserIdAndVisibility(Long userId, String visibility, Pageable pageable);
}
