package com.tothemoon.app.dto;

import lombok.Data;
import java.util.List;

@Data
public class DiscussionDetailDTO {
    private DiscussionDTO discussion;
    private List<BasicTagDTO> tags;
    private List<PostDetailDTO> postList;
}
