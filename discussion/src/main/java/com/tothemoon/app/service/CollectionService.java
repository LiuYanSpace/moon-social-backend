package com.tothemoon.app.service;

import com.bird.dto.Pagination;
import com.tothemoon.common.config.SecurityUtil;
import com.tothemoon.common.entity.DiscussionList;
import com.tothemoon.common.repository.DiscussionListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {


    private final DiscussionListRepository discussionListRepository;

    public Pagination getDiscussionCollections(Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        List<DiscussionList> collections =getDiscussionListsByUserId(userId);



    }


    private List<DiscussionList> getDiscussionListsByUserId(Long userId) {
        return discussionListRepository.findByUserId(userId);
    }
}
