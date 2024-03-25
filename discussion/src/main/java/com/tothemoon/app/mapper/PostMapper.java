package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.BasicPostDTO;
import com.tothemoon.app.dto.PostDTO;
import com.tothemoon.common.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDTO toDTO(Post post);
    BasicPostDTO toBasicPostDTO(Post post);
    Post toEntity(PostDTO postDTO);
    List<PostDTO> toDTOList(List<Post> posts);
    List<BasicPostDTO> toBasicPostList(List<Post> posts);

}
