package com.tothemoon.app.service;

import com.bird.dto.Pagination;
import com.bird.exception.ErrorReasonCode;
import com.bird.exception.ForbiddenRequestException;
import com.bird.utils.PaginationUtils;
import com.tothemoon.app.dto.DiscussionCollectionDTO;
import com.tothemoon.app.feign.client.ExternalAuthClient;
import com.tothemoon.app.mapper.DiscussionListMapper;
import com.tothemoon.app.mapper.DiscussionMapper;
import com.tothemoon.common.config.SecurityUtil;
import com.tothemoon.common.entity.Discussion;
import com.tothemoon.common.entity.DiscussionCollection;
import com.tothemoon.common.entity.DiscussionCollectionItem;
import com.tothemoon.common.entity.User;
import com.tothemoon.common.repository.DiscussionCollectionItemRepository;
import com.tothemoon.common.repository.DiscussionCollectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {


    private final DiscussionCollectionRepository discussionCollectionRepository;
    private final DiscussionCollectionItemRepository discussionCollectionItemRepository;
    private final DiscussionListMapper discussionListMapper;
    private final DiscussionMapper discussionMapper;
    private final ExternalAuthClient externalAuthClient;

    public Pagination getDiscussionCollections(Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        Page<DiscussionCollection> collections = getDiscussionListsByUserId(userId, pageable, false);
        return cleanUpDiscussionCollections(collections);

    }

    public Pagination getDiscussionCollectionsByUserId(Long userId, Pageable pageable) {
        Page<DiscussionCollection> collections = getDiscussionListsByUserId(userId, pageable, true);
        return cleanUpDiscussionCollections(collections);
    }


    public void createDiscussionCollection(DiscussionCollectionDTO collectionDTO) {
        DiscussionCollection discussionCollection = discussionListMapper.toEntity(collectionDTO);
        Long userId = SecurityUtil.getCurrentUserId();
        // feign to get userId
        User user =
                externalAuthClient.getUserByUserId(userId);
        discussionCollection.setUser(user);
        discussionCollection.setDiscussionCount(0L);
//        discussionCollectionRepository.save(discussionCollection);
    }

    public Pagination getDiscussionCollectionsItems(Long listId, Pageable pageable) {
        DiscussionCollection discussionList = discussionCollectionRepository.findById(listId).orElseThrow(() -> new ForbiddenRequestException(ErrorReasonCode.ACCESS_Denied));

        Long userId = SecurityUtil.getCurrentUserId();
        assert discussionList != null;
        if (!Objects.equals(discussionList.getUser().getId(), userId) && Objects.equals(discussionList.getVisibility(), "private")) {
            throw new ForbiddenRequestException(ErrorReasonCode.ACCESS_Denied);
        }

        Page<DiscussionCollectionItem> discussionCollectionItems = discussionCollectionItemRepository.findByDiscussionList(discussionList, pageable);
        List<Discussion> discussions = new ArrayList<>();
        for (DiscussionCollectionItem item : discussionCollectionItems.getContent()) {
            discussions.add(item.getDiscussion());
        }
        return PaginationUtils.wrapPagination(discussionCollectionItems, discussionMapper.toDTOList(discussions));
    }

    private Pagination cleanUpDiscussionCollections(Page<DiscussionCollection> collections) {
        List<DiscussionCollectionDTO> collectionDTOList = discussionListMapper.toDTOList(collections.getContent());
        return PaginationUtils.wrapPagination(collections, collectionDTOList);
    }


    private Page<DiscussionCollection> getDiscussionListsByUserId(Long userId, Pageable pageable, boolean onlyPublic) {
        if (onlyPublic) {
            return discussionCollectionRepository.findByUserIdAndVisibility(userId, "public", pageable);
        } else {
            return discussionCollectionRepository.findByUserId(userId, pageable);
        }
    }

}
