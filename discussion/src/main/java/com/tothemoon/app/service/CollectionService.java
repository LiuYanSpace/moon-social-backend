package com.tothemoon.app.service;

import com.bird.dto.Pagination;
import com.tothemoon.app.dto.DiscussionCollectionDTO;
import com.tothemoon.app.mapper.DiscussionListMapper;
import com.tothemoon.common.config.SecurityUtil;
import com.tothemoon.common.entity.DiscussionCollection;
import com.tothemoon.common.entity.DiscussionCollectionItem;
import com.tothemoon.common.repository.DiscussionCollectionItemRepository;
import com.tothemoon.common.repository.DiscussionCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {


    private final DiscussionCollectionRepository discussionCollectionRepository;
    private final DiscussionCollectionItemRepository discussionCollectionItemRepository;
    private final DiscussionListMapper discussionListMapper;

    public Pagination getDiscussionCollections(Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        Page<DiscussionCollection> collections = getDiscussionListsByUserId(userId, pageable, false);
        return cleanUpDiscussionCollections(collections);

    }

    public Pagination getDiscussionCollectionsByUserId(Long userId, Pageable pageable) {
        Page<DiscussionCollection> collections = getDiscussionListsByUserId(userId, pageable, true);
        return cleanUpDiscussionCollections(collections);
    }

    public Pagination getDiscussionCollectionsItems(Long listId, Pageable pageable) {
        List<DiscussionCollectionItem> discussionCollectionItems =  discussionCollectionItemRepository.findByListId(listId);
        Pagination pagination = new Pagination();
        pagination.setSize(0);
        pagination.setCurrPage(10);
        pagination.setTotalElements(2);
        pagination.setTotalPages(1);
        pagination.setContent(discussionCollectionItems);
        return pagination;


    }
    private Pagination cleanUpDiscussionCollections(Page<DiscussionCollection> collections) {
        List<DiscussionCollectionDTO> collectionDTOList = discussionListMapper.toDTOList(collections.getContent());
        Pagination pagination = new Pagination();
        pagination.setSize(collections.getSize());
        pagination.setCurrPage(collections.getNumber());
        pagination.setTotalElements(collections.getTotalElements());
        pagination.setTotalPages(collections.getTotalPages());
        pagination.setContent(collectionDTOList);
        return pagination;
    }


    private Page<DiscussionCollection> getDiscussionListsByUserId(Long userId, Pageable pageable, boolean onlyPublic) {
        if (onlyPublic) {
            return discussionCollectionRepository.findByUserIdAndVisibility(userId, "public", pageable);
        } else {
            return discussionCollectionRepository.findByUserId(userId, pageable);
        }
    }

}
