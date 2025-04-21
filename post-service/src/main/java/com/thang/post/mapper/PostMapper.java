package com.thang.post.mapper;

import com.thang.post.dto.response.PostResponse;
import com.thang.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}
