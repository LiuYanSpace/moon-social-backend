package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.BasicTagDTO;
import com.tothemoon.app.dto.TagDTO;
import com.tothemoon.common.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface TagMapper {
    TagDTO toDTO(Tag tag);
    Tag toEntity(TagDTO tagDTO);
    List<TagDTO> toDTOList(List<Tag> tags);
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "slug", source = "slug"),
            @Mapping(target = "color", source = "color"),
            @Mapping(target = "icon", source = "icon")
    })
    BasicTagDTO toBasicDTO(Tag tag);
    List<BasicTagDTO> toBasicDTOList(List<Tag> tags);
}
