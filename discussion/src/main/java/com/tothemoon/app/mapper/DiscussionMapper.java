package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.DiscussionDTO;
import com.tothemoon.common.entity.Discussion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface DiscussionMapper {
    DiscussionDTO toDTO(Discussion discussion);

    Discussion toEntity(DiscussionDTO discussionDTO);

    List<DiscussionDTO> toDTOList(List<Discussion> discussions);


}
