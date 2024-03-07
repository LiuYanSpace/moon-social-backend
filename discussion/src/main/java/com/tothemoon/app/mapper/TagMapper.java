package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.BasicTagDTO;
import com.tothemoon.app.dto.TagDTO;
import com.tothemoon.app.dto.TagTreeDTO;
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

    /**
     * Maps a Tag entity to a BasicTagDTO.
     *
     * @param tag The Tag entity to be mapped.
     * @return The corresponding BasicTagDTO.
     */
//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "slug", source = "slug")
//    @Mapping(target = "color", source = "color")
//    @Mapping(target = "icon", source = "icon")
    BasicTagDTO toBasicDTO(Tag tag);

    List<BasicTagDTO> toBasicDTOList(List<Tag> tags);

    TagTreeDTO toTagTreeDTO(Tag tag);
    List<TagTreeDTO> toTagTreeDTO(List<Tag> tags);

}