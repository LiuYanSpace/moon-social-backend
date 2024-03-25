package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.PostLikeDTO;
import com.tothemoon.common.entity.PostLike;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring", uses = {})
public interface PostLikeMapper {

    PostLikeDTO toDTO(PostLike postLike);
    PostLike toEntity(PostLikeDTO postLikeDTO);
    List<PostLikeDTO> toDTOList(List<PostLike> postLike);
}