package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.DiscussionDTO;
import com.tothemoon.common.entity.Discussion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PostMapper.class})
public interface DiscussionMapper {


//        @Mappings({
//            @Mapping(target = "user", source = "user", qualifiedByName = "toBasicUserInfoDTO"),
//            @Mapping(target = "firstPost", source = "firstPost", qualifiedByName = "toBasicPostDTO"),
//            @Mapping(target = "lastPostedUser", source = "lastPostedUser", qualifiedByName = "toBasicUserInfoDTO"),
//            @Mapping(target = "lastPost", source = "lastPost", qualifiedByName = "toBasicPostDTO")
//    })
    DiscussionDTO toDTO(Discussion discussion);

    Discussion toEntity(DiscussionDTO discussionDTO);

    List<DiscussionDTO> toDTOList(List<Discussion> discussions);


}
