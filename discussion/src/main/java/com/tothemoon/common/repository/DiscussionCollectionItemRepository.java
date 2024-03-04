package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DiscussionCollectionId;
import com.tothemoon.common.entity.DiscussionCollectionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionCollectionItemRepository extends JpaRepository<DiscussionCollectionItem, DiscussionCollectionId> {

    List<DiscussionCollectionItem> findByListId(Long listId);
}
