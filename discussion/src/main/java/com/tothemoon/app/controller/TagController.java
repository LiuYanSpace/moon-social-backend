package com.tothemoon.app.controller;

import com.tothemoon.app.dto.TagDTO;
import com.tothemoon.app.mapper.DiscussionMapper;
import com.tothemoon.app.mapper.TagMapper;
import com.tothemoon.app.service.DiscussionService;
import com.tothemoon.app.service.TagService;
import com.tothemoon.common.entity.Tag;
import com.tothemoon.common.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:TagController
 * @Auther: yyj
 * @Description:
 * @Date: 02/03/2024 12:35
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/discussions/tags")
public class TagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private DiscussionMapper discussionMapper;
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/{tagId}")
    public ResponseEntity<?> getDiscussionsByTagId(@PathVariable Long tagId) {
        Pageable pageable = PageRequest.of(0, 10);
        return ResponseEntity.ok(tagService.getDiscussionsByTagId(tagId, pageable));
    }

    @GetMapping("/parent")
    public List<TagDTO> getParentTags() {

        return tagService.getParentTags();
    }


    @GetMapping("/children")
    public List<TagDTO> getAllTagsTree() {

        return tagService.getAllTagsTree();
    }
}
