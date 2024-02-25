package com.tothemoon.app.mapper;

import com.tothemoon.app.dto.FileInfoDTO;
import com.tothemoon.common.entity.FileInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface FileInfoMapper {
    FileInfoDTO toDTO(FileInfo fileInfo);

    FileInfo toEntity(FileInfoDTO fileInfoDTO);

    List<FileInfoDTO> toDTOList(List<FileInfo> fileInfoList);
}
