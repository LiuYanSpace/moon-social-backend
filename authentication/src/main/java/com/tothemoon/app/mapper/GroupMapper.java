package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.GroupDTO;
import com.tothemoon.common.entity.Group;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface GroupMapper {

    GroupDTO toDTO(Group groupUser);
    Group toEntity(GroupDTO groupDTO);
    List<GroupDTO> toDTOList(List<Group> groups);
}
