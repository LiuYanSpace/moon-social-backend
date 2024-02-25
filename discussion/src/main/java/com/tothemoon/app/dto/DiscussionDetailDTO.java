package com.tothemoon.app.dto;

import com.tothemoon.common.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class DiscussionDetailDTO  extends DiscussionDTO{
    private List<Post> postList;

}
