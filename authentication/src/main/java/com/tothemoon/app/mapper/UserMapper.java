package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.BasicUserInfoDTO;
import com.tothemoon.app.dto.UserDTO;
import com.tothemoon.common.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
    List<UserDTO> toDTOList(List<User> users);
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "avatarUrl", source = "avatarUrl"),
            @Mapping(target = "nickname", source = "nickname")
    })
    BasicUserInfoDTO toBasicUserInfoDTO(User user);

    @Named("toBasicUserInfoDTO")
    default BasicUserInfoDTO toBasicUserInfoDTOWithNamed(User user) {
        if (user == null) {
            return null;
        }
        BasicUserInfoDTO dto = new BasicUserInfoDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setNickname(user.getNickname());
        return dto;
    }
}
