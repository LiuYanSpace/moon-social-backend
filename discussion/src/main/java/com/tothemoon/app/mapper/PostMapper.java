package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.BasicPostDTO;
import com.tothemoon.app.dto.PostDTO;
import com.tothemoon.common.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses ={ UserMapper.class})
public interface PostMapper {
    PostDTO toDTO(Post post);
    BasicPostDTO toBasicPostDTO(Post post);
    Post toEntity(PostDTO postDTO);
    List<PostDTO> toDTOList(List<Post> posts);


    @Named("toBasicPostDTO")
    default BasicPostDTO toBasicPostDTOWithNamed(Post post) {
        if (post == null) {
            return null;
        }
        BasicPostDTO dto = new BasicPostDTO();
        dto.setContent(post.getContent());
        dto.setId(post.getId());
        dto.setNumber(post.getNumber());
        dto.setType(post.getType());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setEditedAt(post.getEditedAt());
        dto.setEditedUser(UserMapper.INSTANCE.toBasicUserInfoDTO(post.getEditedUser()));
        dto.setUser(UserMapper.INSTANCE.toBasicUserInfoDTO(post.getUser()));
        return dto;
    }
}
