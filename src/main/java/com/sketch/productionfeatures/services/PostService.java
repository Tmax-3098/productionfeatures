package com.sketch.productionfeatures.services;

import com.sketch.productionfeatures.dto.PostDto;
import com.sketch.productionfeatures.entities.PostEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createPost(PostDto post);

    PostDto getPostById(Long id);
}
