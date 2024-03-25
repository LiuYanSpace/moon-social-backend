package com.tothemoon.app.dto;

import com.bird.dto.BasicUserInfoDTO;
import lombok.Data;

import java.util.List;

@Data
public class DiscussionListDTO {
    private DiscussionDTO discussion;
    private BasicUserInfoDTO firstPostUser;
    private BasicUserInfoDTO lastPostUser;
    private List<BasicTagDTO> tags;
}
