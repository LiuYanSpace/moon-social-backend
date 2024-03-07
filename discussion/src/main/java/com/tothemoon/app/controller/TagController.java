package com.tothemoon.app.controller;

import com.bird.dto.Pagination;
import com.tothemoon.app.dto.BasicTagDTO;
import com.tothemoon.app.dto.TagTreeDTO;
import com.tothemoon.app.mapper.DiscussionMapper;
import com.tothemoon.app.mapper.TagMapper;
import com.tothemoon.app.service.TagService;
import com.tothemoon.common.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Pagination> getDiscussionsByTagId(
            @PathVariable("tagId") Long tagId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "DESC") String sortOrder
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return ResponseEntity.ok(tagService.getDiscussionsByTagId(tagId,pageable));
    }


    @GetMapping("/parent")
    public ResponseEntity<List<BasicTagDTO>> getParentTags() {

        return ResponseEntity.ok(tagService.getParentTags());
    }


    @GetMapping("/children")
    public ResponseEntity<List<TagTreeDTO>> getAllTagsTree() {

        return ResponseEntity.ok(tagService.getAllTagsTree());
    }
}
