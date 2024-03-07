package com.tothemoon.app.service;

import com.bird.dto.Pagination;
import com.tothemoon.app.dto.DiscussionDTO;
import com.tothemoon.app.dto.DiscussionListDTO;
import com.tothemoon.app.dto.TagDTO;
import com.tothemoon.app.mapper.DiscussionMapper;
import com.tothemoon.app.mapper.TagMapper;
import com.tothemoon.common.entity.Discussion;
import com.tothemoon.common.entity.DiscussionTag;
import com.tothemoon.common.entity.Tag;
import com.tothemoon.common.repository.DiscussionTagRepository;
import com.tothemoon.common.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private DiscussionTagRepository discussionTagRepository;
    @Autowired
    private DiscussionMapper discussionMapper;

    public List<TagDTO> getAllTagsTree() {
        List<Tag> allTags = tagRepository.findAll();
        List<TagDTO> rootTags = buildTagTree(null, allTags);

        return rootTags;
    }

    private List<TagDTO> buildTagTree(Long parentId, List<Tag> allTags) {
        List<TagDTO> children = new ArrayList<>();

        for (Tag tag : allTags) {
            if (Objects.equals(parentId, tag.getParentTag() != null ? tag.getParentTag().getId() : null)) {
                TagDTO tagDTO = tagMapper.toDTO(tag);
                List<TagDTO> grandchildren = buildTagTree(tag.getId(), allTags);
                tagDTO.setChildren(grandchildren);
                children.add(tagDTO);
            }
        }

        return children;
    }

    public Pagination getDiscussionsByTagId(Long tagId, Pageable pageable) {
        Page<DiscussionTag> discussionTags = discussionTagRepository.findByTagId(tagId, pageable);
        List<DiscussionDTO> list = new ArrayList<>();
        //if (discussionTags != null && discussionTags.getContent() != null) {
            for (DiscussionTag discussionTag : discussionTags.getContent()) {
                list.add(discussionMapper.toDTO(discussionTag.getDiscussion()));
            }
        //}
        Pagination pagination = new Pagination();
        pagination.setSize(discussionTags.getSize());
        pagination.setCurrPage(discussionTags.getNumber());
        pagination.setTotalElements(discussionTags.getTotalElements());
        pagination.setTotalPages(discussionTags.getTotalPages());
        pagination.setContent(list);
        return pagination;
    }


    public List<TagDTO> getParentTags() {
        List<Tag> tags = tagRepository.findByParentTagIsNull();
        return tagMapper.toDTOList(tags);
    }


}
