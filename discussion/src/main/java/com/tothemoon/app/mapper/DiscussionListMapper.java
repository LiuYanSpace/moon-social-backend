package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.DiscussionCollectionDTO;
import com.tothemoon.common.entity.DiscussionCollection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface DiscussionListMapper {
    DiscussionCollectionDTO toDTO(DiscussionCollection discussionCollection);
    DiscussionCollection toEntity(DiscussionCollectionDTO discussionCollectionDTO);
    List<DiscussionCollectionDTO> toDTOList(List<DiscussionCollection> discussionCollections);
}
