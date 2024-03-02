package com.tothemoon.app.service;

import com.tothemoon.app.dto.TagDTO;
import com.tothemoon.app.mapper.TagMapper;
import com.tothemoon.common.entity.Tag;
import com.tothemoon.common.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TagMapper tagMapper;

    public List<TagDTO> getAllTagsTree() {
        List<Tag> allTags = tagRepository.findAll();
        List<TagDTO> rootTags = buildTagTree(null, tagMapper.toDTOList(allTags));

        return rootTags;
    }

    private List<TagDTO> buildTagTree(Long parentId, List<TagDTO> allTags) {
        List<TagDTO> children = new ArrayList<>();

        for (TagDTO tag : allTags) {
            if (Objects.equals(parentId, tag.getParentTag() != null ? tag.getParentTag().getId() : null)) {
                List<TagDTO> grandchildren = buildTagTree(tag.getId(), allTags);
//                tag.setChildren(grandchildren);
                children.add(tag);
            }
        }

        return children;
    }
}
