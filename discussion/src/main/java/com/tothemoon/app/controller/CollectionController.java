package com.tothemoon.app.controller;

import com.bird.dto.Pagination;
import com.tothemoon.app.dto.DiscussionCollectionDTO;
import com.tothemoon.app.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;


    @GetMapping
    public ResponseEntity<Pagination> getDiscussionCollections(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder, @RequestHeader("X-UserId") String userId) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return ResponseEntity.ok(collectionService.getDiscussionCollections(pageable, userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Pagination> getDiscussionCollectionsByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return ResponseEntity.ok(collectionService.getDiscussionCollectionsByUserId(userId, pageable));
    }

    @GetMapping("/details/{listId}")
    public ResponseEntity<Pagination> getDiscussionCollectionsById(
            @PathVariable Long listId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder, @RequestHeader("X-UserId") String userId) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return ResponseEntity.ok(collectionService.getDiscussionCollectionsItems(listId, pageable, userId));
    }

    @PostMapping
    public ResponseEntity<?> createDiscussionCollection(@RequestBody DiscussionCollectionDTO collectionDTO, @RequestHeader("X-UserId") String userId) {
        collectionService.createDiscussionCollection(collectionDTO, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
