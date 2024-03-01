package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DiscussionList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionListRepository extends JpaRepository<DiscussionList, Long> {
    List<DiscussionList> findByUserId(Long userId);
}
