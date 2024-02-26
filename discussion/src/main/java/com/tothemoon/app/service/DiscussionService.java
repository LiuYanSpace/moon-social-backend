package com.tothemoon.app.service;

import com.bird.exception.ErrorReasonCode;
import com.bird.exception.NotFoundRequestException;
import com.tothemoon.app.dto.*;
import com.tothemoon.app.mapper.DiscussionMapper;
import com.tothemoon.app.mapper.PostMapper;
import com.tothemoon.app.mapper.TagMapper;
import com.tothemoon.app.mapper.UserMapper;
import com.tothemoon.common.entity.*;
import com.tothemoon.common.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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


    public DiscussionDetailDTO getDiscussionWithComments(Long discussionId, Pageable pageable) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));

        List<Post> comments = postRepository.findByDiscussionIdAndIsSpamFalseAndIsPrivateFalseAndIsApprovedTrue(discussionId, pageable);
        List<BasicTagDTO> tagDTOS =  getTagsByDiscussionId(discussionId);
        List<BasicPostDTO> basicPostDTOS  = postMapper.toBasicPostList(comments);
        List<PostDetailDTO> postList=new ArrayList<>();
        PostDetailDTO postDetailDTO = new PostDetailDTO();
        for(BasicPostDTO postDTO :basicPostDTOS){
            long userId = postDTO.getUser().getId();
            long postId = postDTO.getId();
           List<PostLike> postLike = postLikeRepository.findByUserIdAndPostId(userId,postId);
            postDetailDTO.setBasicPost(postDTO);
            List<BasicUserInfoDTO> likeUsers = new ArrayList<>();
            // TODO
            List<BasicUserInfoDTO> replyUsers =new ArrayList<>();
            for (PostLike like : postLike){
                likeUsers.add(userMapper.toBasicUserInfoDTO(like.getUser()));
            }
            postDetailDTO.setLikeUsers(likeUsers);
            postDetailDTO.setReplyUsers(replyUsers);
            postList.add(postDetailDTO);
        }
        DiscussionDetailDTO discussionDetailDTO = new DiscussionDetailDTO();
        discussionDetailDTO.setDiscussion(discussionMapper.toDTO(discussion));
        discussionDetailDTO.setTags(tagDTOS);
        discussionDetailDTO.setPostList(postList);
        return discussionDetailDTO;
    }

    public Page<DiscussionListDTO> getDiscussionList(Pageable pageable) {
        Page<Discussion> discussionPage = discussionRepository.findByIsStickyFalseAndIsPrivateFalseAndIsApprovedTrue(pageable);
        List<Discussion> discussions = discussionPage.getContent();
        List<DiscussionListDTO> discussionListDTOS = cleanUpDiscussions(discussions);
        return new PageImpl<>(discussionListDTOS, pageable, discussionPage.getTotalElements());
    }

    public List<DiscussionListDTO> getTopDiscussionList() {
        List<Discussion> discussions = discussionRepository.findByIsStickyTrueAndIsPrivateFalseAndIsApprovedTrueOrderByLastPostedAtDesc();
        return cleanUpDiscussions(discussions);
    }

    //
//    public Object getDiscussionListByTag(Long tagId) {
//    }

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
