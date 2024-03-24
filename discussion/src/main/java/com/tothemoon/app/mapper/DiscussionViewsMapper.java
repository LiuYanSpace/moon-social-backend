package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.DiscussionViewsDTO;
import com.tothemoon.common.entity.DiscussionViews;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface DiscussionViewsMapper {
    DiscussionViewsDTO toDTO(DiscussionViews discussionViews);
    DiscussionViews toEntity(DiscussionViewsDTO discussionViewsDTO);
    List<DiscussionViewsDTO> toDTOList(List<DiscussionViews> discussionViews);
}
