package com.tothemoon.app.dto;

import com.bird.dto.BasicUserInfoDTO;
import lombok.Data;
import java.util.List;

@Data
public class DiscussionPageDTO {
    private DiscussionDTO discussion;
    private List<BasicTagDTO> tags;
}
