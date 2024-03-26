package com.tothemoon.app.controller;

import com.bird.dto.Pagination;
import com.tothemoon.app.dto.DiscussionPageDTO;
import com.tothemoon.app.dto.DiscussionListDTO;
import com.tothemoon.app.dto.DiscussionPostDTO;
import com.tothemoon.app.dto.PostDetailDTO;
import com.tothemoon.app.mapper.DiscussionMapper;
import com.tothemoon.app.mapper.PostLikeMapper;
import com.tothemoon.app.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
    private DiscussionMapper discussionMapper;

    @Autowired
    private PostLikeMapper postLikeMapper;


    @PostMapping("/post")
    public ResponseEntity<?> createNewDiscussion(  @RequestHeader("X-UserId") Long userId, @RequestBody DiscussionPostDTO discussionPostDTO) {
        discussionService.createNewDiscussion(discussionPostDTO, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<?> updateNewDiscussion( @RequestHeader("X-UserId") Long userId, @PathVariable("postId") Long postId , @RequestBody DiscussionPostDTO discussionPostDTO)  {
        discussionService.updateNewDiscussion(discussionPostDTO,postId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Pagination> getDiscussionList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "lastPostedAt") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "DESC") String sortOrder) {

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
    public ResponseEntity<Pagination> getPostsByDiscussionId(
            @PathVariable("discussionId") Long discussionId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return ResponseEntity.ok(discussionService.getPostsByDiscussionId(discussionId, pageable));
    }

    @GetMapping("/{discussionId}")
    public ResponseEntity<DiscussionPageDTO> getDiscussionById(@PathVariable("discussionId") Long discussionId) {
        return ResponseEntity.ok(discussionService.getDiscussionWithTagsById(discussionId));
    }


    @PostMapping("/posts/{postId}/like/{userId}")
    public ResponseEntity<String> likePost(@PathVariable Long postId, @RequestParam Long userId){
        return null;
        discussionService.getPostLike(postId, userId);
        return ResponseEntity.ok("已点赞");
    }

    @DeleteMapping("/posts/{postId}/delete")
    public ResponseEntity<String> deleteLikePost(@PathVariable Long postId, @RequestParam Long userId){
        discussionService.unlikePost(postId,userId);
        return ResponseEntity.ok("取消点赞");
    }

    @GetMapping ("/posts/{postId}/count")
    public ResponseEntity<Integer> getLikesCount(@PathVariable Long postId) {
        int likesCount = discussionService.getLikesCount(postId);
        return ResponseEntity.ok(likesCount);
    }

    @GetMapping("/users/{userId}/liked")
    public ResponseEntity<List<PostLikeDTO>> getLikedPosts(@PathVariable Long userId) {

        return ResponseEntity.ok(discussionService.getLikedPosts(userId));
    }

    @GetMapping("/discussion/discussion_views/{userId}")
    public ResponseEntity<List<DiscussionViewsDTO>> getListDiscussionViews(@PathVariable Long userId) {

        return ResponseEntity.ok(discussionService.getDiscussionList(userId));
    }

}
