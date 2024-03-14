package com.tothemoon.app.service;

import com.bird.dto.Pagination;
import com.bird.exception.ErrorReasonCode;
import com.bird.exception.ForbiddenRequestException;
import com.bird.feign.UserServiceFeignApi;
import com.bird.utils.PaginationUtils;
import com.tothemoon.app.dto.DiscussionCollectionDTO;
import com.tothemoon.app.mapper.DiscussionListMapper;
import com.tothemoon.app.mapper.DiscussionMapper;
import com.tothemoon.common.entity.Discussion;
import com.tothemoon.common.entity.DiscussionCollection;
import com.tothemoon.common.entity.DiscussionCollectionItem;
import com.tothemoon.common.repository.DiscussionCollectionItemRepository;
import com.tothemoon.common.repository.DiscussionCollectionRepository;
import jakarta.annotation.Resource;
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

    @Resource
    private UserServiceFeignApi userServiceFeignApi;

    public Pagination getDiscussionCollections(Pageable pageable, String userId) {

        Page<DiscussionCollection> collections = getDiscussionListsByUserId(Long.parseLong(userId), pageable, false);
        return cleanUpDiscussionCollections(collections);

    }

    public Pagination getDiscussionCollectionsByUserId(Long userId, Pageable pageable) {
        Page<DiscussionCollection> collections = getDiscussionListsByUserId(userId, pageable, true);
        return cleanUpDiscussionCollections(collections);
    }


    public void createDiscussionCollection(DiscussionCollectionDTO collectionDTO, String userId) {
        DiscussionCollection discussionCollection = discussionListMapper.toEntity(collectionDTO);
        discussionCollection.setUserId(Long.parseLong(userId));
        discussionCollection.setDiscussionCount(0L);
//        discussionCollectionRepository.save(discussionCollection);
    }

    public Pagination getDiscussionCollectionsItems(Long listId, Pageable pageable, String userId) {
        DiscussionCollection discussionList = discussionCollectionRepository.findById(listId).orElseThrow(() -> new ForbiddenRequestException(ErrorReasonCode.ACCESS_Denied));

        assert discussionList != null;
        if (!Objects.equals(discussionList.getUserId(), Long.parseLong(userId)) && Objects.equals(discussionList.getVisibility(), "private")) {
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
