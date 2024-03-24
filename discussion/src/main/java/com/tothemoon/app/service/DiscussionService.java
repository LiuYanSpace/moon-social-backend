package com.tothemoon.app.service;

import com.bird.dto.Pagination;
import com.bird.exception.ErrorReasonCode;
import com.bird.exception.NotFoundRequestException;
import com.tothemoon.app.dto.*;
import com.tothemoon.app.mapper.*;
import com.tothemoon.common.entity.*;
import com.tothemoon.common.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName:DiscussionService
 * @Auther: yyj
 * @Description:
 * @Date: 19/02/2024 19:42
 * @Version: v1.0
 */


@Service
@Transactional
@RequiredArgsConstructor
public class DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostLikeRepository postLikeRepository;
    private final DiscussionTagRepository discussionTagRepository;
    private final DiscussionMapper discussionMapper;
    private final TagMapper tagMapper;
    private final PostMapper postMapper;
    private final UserMapper userMapper;
    private final DiscussionViewsRepository discussionViewsRepository;

    //浏览

    public List<DiscussionViewsDTO> getDiscussionList(Long userId) {
        List<DiscussionViews> discussionViewsList = discussionViewsRepository.findByUserId(userId);
        List<DiscussionViewsDTO> discussionViewsDTOList = new ArrayList<>();
        for (DiscussionViews discussionViews : discussionViewsList) {
            DiscussionViewsDTO discussionViewsDTO = new DiscussionViewsDTO();
            discussionViewsDTO.getNickname(discussionViews.getUser().getNickname());
            discussionViewsDTO.setTitle(discussionViews.getDiscussion().getTitle());
            discussionViewsDTO.setVisitedAt(discussionViews.getVisitedAt());

            discussionViewsDTOList.add(discussionViewsDTO);

        }

        return discussionViewsDTOList;
    }

    //点赞
    public void getPostLike(Long postId, Long userId) {
        if (!postLikeRepository.existsByPostIdAndUserId(postId, userId)) {
            PostLike postLike = new PostLike(postId, userId);
            postLikeRepository.save(postLike);

        }
    }

    // 取消点赞帖子
    public void unlikePost(Long postId, Long userId) {

        postLikeRepository.deleteByPostIdAndUserId(postId, userId);

    }


    // 获取帖子点赞数
    public int getLikesCount(Long postId) {
        return postLikeRepository.countByPostId(postId);

    }


    //获取用户点赞列表
    public List<PostLikeDTO> getLikedPosts(Long userId) {

        List<PostLike> postlikes = postLikeRepository.findByUserId(userId);
        List<PostLikeDTO> likedPosts = new ArrayList<>();
        for (PostLike postLike : postlikes) {
            PostLikeDTO postLikeDTO = new PostLikeDTO();
            postLikeDTO.setPostId(postLike.getPost().getId());
            postLikeDTO.setContent(postLike.getPost().getContent());
            likedPosts.add(postLikeDTO);
        }

        return likedPosts;
    }

    //TODO Page to Pagination
    public Page<PostDetailDTO> getPostsByDiscussionId(Long discussionId, Pageable pageable) {
        Page<Post> comments = postRepository.findByDiscussionIdAndIsSpamFalseAndIsPrivateFalseAndIsApprovedTrue(discussionId, pageable);
        List<BasicPostDTO> basicPostDTOS = postMapper.toBasicPostList(comments.getContent());
        List<PostDetailDTO> postList = new ArrayList<>();
        for (BasicPostDTO postDTO : basicPostDTOS) {
            long postId = postDTO.getId();
            List<PostLike> postLike = postLikeRepository.findByPostId(postId);
            List<BasicUserInfoDTO> likeUsers = new ArrayList<>();
            // TODO
            List<BasicUserInfoDTO> replyUsers = new ArrayList<>();
            for (PostLike like : postLike) {
                likeUsers.add(userMapper.toBasicUserInfoDTO(like.getUser()));
            }
            PostDetailDTO postDetailDTO = new PostDetailDTO();
            postDetailDTO.setBasicPost(postDTO);
            postDetailDTO.setLikeUsers(likeUsers);
            postDetailDTO.setReplyUsers(replyUsers);
            postList.add(postDetailDTO);
        }
        return new PageImpl<>(postList, pageable, comments.getTotalElements());
    }

    public Pagination getDiscussionList(Pageable pageable) {
        Page<Discussion> discussionPage = discussionRepository.findByIsStickyFalseAndIsPrivateFalseAndIsApprovedTrue(pageable);
        List<Discussion> discussions = discussionPage.getContent();
        List<DiscussionListDTO> discussionListDTOS = cleanUpDiscussions(discussions);
        Pagination pagination = new Pagination();
        pagination.setSize(discussionPage.getSize());
        pagination.setCurrPage(discussionPage.getNumber());
        pagination.setTotalElements(discussionPage.getTotalElements());
        pagination.setTotalPages(discussionPage.getTotalPages());
        pagination.setContent(discussionListDTOS);
        return pagination;
    }

    public List<DiscussionListDTO> getTopDiscussionList() {
        List<Discussion> discussions = discussionRepository.findByIsStickyTrueAndIsPrivateFalseAndIsApprovedTrueOrderByLastPostedAtDesc();
        return cleanUpDiscussions(discussions);
    }

    public DiscussionDetailDTO getDiscussionWithTagsById(Long discussionId) {
        DiscussionDetailDTO discussionDetailDTO = new DiscussionDetailDTO();
        discussionDetailDTO.setDiscussion(getDiscussionById(discussionId));
        discussionDetailDTO.setTags(getTagsByDiscussionId(discussionId));
        return discussionDetailDTO;
    }

    private DiscussionDTO getDiscussionById(Long discussionId) {
        Discussion discussion = discussionRepository.findById(discussionId).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));
        return discussionMapper.toDTO(discussion);
    }

    private List<BasicTagDTO> getTagsByDiscussionId(long discussionId) {
        List<DiscussionTag> discussionTags = discussionTagRepository.findByDiscussionId(discussionId);
        List<Tag> tagsForDiscussion = new ArrayList<>();
        for (DiscussionTag discussionTag : discussionTags) {
            tagsForDiscussion.add(discussionTag.getTag());
        }
        return tagMapper.toBasicDTOList(tagsForDiscussion);
    }

    private List<DiscussionListDTO> cleanUpDiscussions(List<Discussion> discussions) {
        List<DiscussionDTO> discussionDTOs = discussionMapper.toDTOList(discussions);
        List<DiscussionListDTO> discussionListDTOS = new ArrayList<>();
        for (DiscussionDTO discussionDTO : discussionDTOs) {
            Long discussionId = discussionDTO.getId();
            List<BasicTagDTO> basicTagDTOs = getTagsByDiscussionId(discussionId);
            DiscussionListDTO discussionListDTO = new DiscussionListDTO();
            discussionListDTO.setDiscussion(discussionDTO);
            discussionListDTO.setTags(basicTagDTOs);
            discussionListDTOS.add(discussionListDTO);
        }

        return discussionListDTOS;
    }


}
