package com.tothemoon.app.controller;

import com.bird.dto.Pagination;
import com.tothemoon.app.dto.DiscussionDetailDTO;
import com.tothemoon.app.dto.DiscussionListDTO;
import com.tothemoon.app.dto.PostDetailDTO;
import com.tothemoon.app.mapper.DiscussionMapper;
import com.tothemoon.app.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:DiscussionController
 * @Author: yyj
 * @Description:
 * @Date: 19/02/2024 19:45
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/discussions")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private  DiscussionMapper discussionMapper;



    @GetMapping
    public ResponseEntity<Pagination> getDiscussionList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "lastPostedAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Pagination discussions = discussionService.getDiscussionList(pageable);
        return ResponseEntity.ok(discussions);
    }

    @GetMapping("/sticky")
    public ResponseEntity<List<DiscussionListDTO>> getTopDiscussionList() {
        List<DiscussionListDTO> discussions = discussionService.getTopDiscussionList();
        return ResponseEntity.ok(discussions);
    }


    /***
     *
     * @param discussionId
     * @param page
     * @param size
     * @param sortBy
     * @param sortOrder
     * @return
     * TODO
     *  need to fetch the posts (pagination),
     *  hit-like of each post
     *  who reply the post
     */
    @GetMapping("/posts/{discussionId}")
    public ResponseEntity<Page<PostDetailDTO>> getPostsByDiscussionId(
            @PathVariable Long discussionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return ResponseEntity.ok(discussionService.getPostsByDiscussionId(discussionId, pageable));
    }

    @GetMapping("/{discussionId}")
    public ResponseEntity<DiscussionDetailDTO> getDiscussionById(@PathVariable Long discussionId) {
        return ResponseEntity.ok(discussionService.getDiscussionWithTagsById(discussionId));
    }

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId, @RequestParam Long userId){
        return null;
    }
}
