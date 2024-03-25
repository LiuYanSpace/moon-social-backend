package com.tothemoon.app.service;

import com.bird.dto.BasicUserInfoDTO;
import com.bird.dto.Pagination;
import com.bird.exception.ErrorReasonCode;
import com.bird.exception.NotFoundRequestException;
import com.tothemoon.app.dto.*;
import com.tothemoon.app.feign.UserServiceFeignApi;
import com.tothemoon.app.mapper.DiscussionMapper;
import com.tothemoon.app.mapper.PostMapper;
import com.tothemoon.app.mapper.TagMapper;
import com.tothemoon.common.entity.*;
import com.tothemoon.common.repository.*;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Resource
    private UserServiceFeignApi userServiceFeignApi;

    //TODO Page to Pagination
    public Pagination getPostsByDiscussionId(Long discussionId, Pageable pageable) {
        Page<Post> comments = postRepository.findByDiscussionIdAndIsSpamFalseAndIsPrivateFalseAndIsApprovedTrue(discussionId, pageable);

        List<BasicPostDTO> basicPostDTOS = postMapper.toBasicPostList(comments.getContent());
        List<PostDetailDTO> postList = new ArrayList<>();

        Map<Long, BasicUserInfoDTO> basicUserInfoDTOMap = new HashMap<>();
        BasicUserInfoDTO basicUserInfoDTO = null;
        BasicUserInfoDTO user = null;
        BasicUserInfoDTO editUser = null;

        for (BasicPostDTO postDTO : basicPostDTOS) {
            long postId = postDTO.getId();

            List<PostLike> postLike = postLikeRepository.findByPostId(postId);
            List<BasicUserInfoDTO> likeUsers = new ArrayList<>();
            List<BasicUserInfoDTO> replyUsers = new ArrayList<>();
            for (PostLike like : postLike) {
                Long likeUserId = like.getUserId();
                if (basicUserInfoDTOMap.containsKey(likeUserId)) {
                    basicUserInfoDTO = basicUserInfoDTOMap.get(likeUserId);
                } else {
                    basicUserInfoDTO = userServiceFeignApi.getBasicUserinfoByUserId(like.getUserId()).getBody();
                    basicUserInfoDTOMap.put(likeUserId, basicUserInfoDTO);
                }
                likeUsers.add(basicUserInfoDTO);
            }
            //TODO reply


            long postUserId = postDTO.getUserId();

            if (basicUserInfoDTOMap.containsKey(postUserId)) {
                user = basicUserInfoDTOMap.get(postUserId);
            } else {
                user = userServiceFeignApi.getBasicUserinfoByUserId(postUserId).getBody();
                basicUserInfoDTOMap.put(postUserId, user);
            }


            if (postDTO.getEditedUserId() != null) {
                long postEditUserId = postDTO.getEditedUserId();
                if (basicUserInfoDTOMap.containsKey(postEditUserId)) {
                    editUser = basicUserInfoDTOMap.get(postEditUserId);
                } else {
                    editUser = userServiceFeignApi.getBasicUserinfoByUserId(postEditUserId).getBody();
                    basicUserInfoDTOMap.put(postUserId, editUser);
                }
            }


            PostDetailDTO postDetailDTO = new PostDetailDTO();
            postDetailDTO.setBasicPost(postDTO);
            postDetailDTO.setLikeUsers(likeUsers);
            postDetailDTO.setReplyUsers(replyUsers);
            postDetailDTO.setUser(user);
            postDetailDTO.setEditUser(editUser);
            postList.add(postDetailDTO);
        }

        Pagination pagination = new Pagination();
        pagination.setSize(comments.getSize());
        pagination.setCurrPage(comments.getNumber());
        pagination.setTotalElements(comments.getTotalElements());
        pagination.setTotalPages(comments.getTotalPages());
        pagination.setContent(postList);

        return pagination;
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

    public DiscussionPageDTO getDiscussionWithTagsById(Long discussionId) {
        DiscussionPageDTO discussionPageDTO = new DiscussionPageDTO();
        DiscussionDTO discussionDTO = getDiscussionById(discussionId);
        discussionPageDTO.setDiscussion(discussionDTO);
        discussionPageDTO.setTags(getTagsByDiscussionId(discussionId));
        return discussionPageDTO;
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
        List<DiscussionListDTO> discussionListDTOS = new ArrayList<>();
        for (Discussion discussion : discussions) {
            Long discussionId = discussion.getId();
            List<BasicTagDTO> basicTagDTOs = getTagsByDiscussionId(discussionId);
            DiscussionListDTO discussionListDTO = new DiscussionListDTO();
            Long userId = discussion.getUserId();
            Long lastUserId = discussion.getLastPostedUserId();
            BasicUserInfoDTO author = userServiceFeignApi.getBasicUserinfoByUserId(userId).getBody();
            BasicUserInfoDTO lastUser = author;
            if (!Objects.equals(userId, lastUserId)) {
                lastUser = userServiceFeignApi.getBasicUserinfoByUserId(lastUserId).getBody();
            }
            discussionListDTO.setDiscussion(discussionMapper.toDTO(discussion));
            discussionListDTO.setFirstPostUser(author);
            discussionListDTO.setLastPostUser(lastUser);
            discussionListDTO.setTags(basicTagDTOs);
            discussionListDTOS.add(discussionListDTO);
        }

        return discussionListDTOS;
    }

}
