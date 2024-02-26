package com.tothemoon.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class DiscussionListDTO {
    private DiscussionDTO discussion;
    private List<BasicTagDTO> tags;

}
