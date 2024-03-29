package com.tothemoon.app.dto;

import com.bird.dto.BasicUserInfoDTO;
import lombok.Data;

import java.util.List;

/**
 * @author birdyyoung
 * @ClassName:LikePostDTO
 * @Description:
 * @Date: 26/02/2024 19:49
 * @Version: v1.0
 */
@Data
public class PostDetailDTO {
    BasicPostDTO basicPost;
    BasicUserInfoDTO user;
    BasicUserInfoDTO editUser;
    List<BasicUserInfoDTO> likeUsers;
    List<BasicUserInfoDTO> replyUsers;
}
