package com.tothemoon.app.service;

import com.bird.dto.Pagination;
import com.tothemoon.app.dto.*;
import com.tothemoon.app.mapper.DiscussionMapper;
import com.tothemoon.app.mapper.TagMapper;
import com.tothemoon.common.entity.Discussion;
import com.tothemoon.common.entity.DiscussionTag;
import com.tothemoon.common.entity.Tag;
import com.tothemoon.common.repository.DiscussionTagRepository;
import com.tothemoon.common.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName:TagService
 * @Auther: yyj
 * @Description:
 * @Date: 02/03/2024 12:13
 * @Version: v1.0
 */


@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final DiscussionTagRepository discussionTagRepository;
    private final DiscussionMapper discussionMapper;

    public List<TagTreeDTO> getAllTagsTree() {
        List<Tag> allTags = tagRepository.findByPositionIsNotNull(Sort.by("position"));
        return buildTagTree(null, allTags);
    }

    private List<TagTreeDTO> buildTagTree(Long parentId, List<Tag> allTags) {
        List<TagTreeDTO> children = new ArrayList<>();

        for (Tag tag : allTags) {
            if (Objects.equals(parentId, tag.getParentTag() != null ? tag.getParentTag().getId() : null)) {
                TagTreeDTO tagTreeDTO = tagMapper.toTagTreeDTO(tag);
                List<TagTreeDTO> grandchildren = buildTagTree(tag.getId(), allTags);
                tagTreeDTO.setChildren(grandchildren);
                children.add(tagTreeDTO);
            }
        }

        return children;
    }

    public Pagination getDiscussionsByTagId(Long tagId, Pageable pageable) {
        Page<DiscussionTag> discussionTags = discussionTagRepository.findByTagId(tagId, pageable);
        List<DiscussionDTO> discussions = new ArrayList<>();

        for (DiscussionTag discussionTag : discussionTags.getContent()) {
            discussions.add(discussionMapper.toDTO(discussionTag.getDiscussion()));
        }

        Pagination pagination = new Pagination();
        pagination.setSize(discussionTags.getSize());
        pagination.setCurrPage(discussionTags.getNumber());
        pagination.setTotalElements(discussionTags.getTotalElements());
        pagination.setTotalPages(discussionTags.getTotalPages());
        pagination.setContent(discussions);

        return pagination;
    }


    public List<BasicTagDTO> getParentTags() {
        List<Tag> tags = tagRepository.findByParentTagIsNullAndPositionIsNotNull(Sort.by("position"));
        return tagMapper.toBasicDTOList(tags);
    }

}
