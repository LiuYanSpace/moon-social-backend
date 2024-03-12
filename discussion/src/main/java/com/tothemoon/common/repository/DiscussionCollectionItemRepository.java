package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DiscussionCollection;
import com.tothemoon.common.entity.DiscussionCollectionId;
import com.tothemoon.common.entity.DiscussionCollectionItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiscussionCollectionItemRepository extends CrudRepository<DiscussionCollectionItem, DiscussionCollectionId> {

    Page<DiscussionCollectionItem> findByDiscussionList(DiscussionCollection discussionList, Pageable pageable);
}
