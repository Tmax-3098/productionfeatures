package com.sketch.productionfeatures.services.servicesImpl;


import com.sketch.productionfeatures.dto.PostDto;
import com.sketch.productionfeatures.entities.PostEntity;
import com.sketch.productionfeatures.exceptions.ResourceNotFoundException;
import com.sketch.productionfeatures.repositories.PostRepo;
import com.sketch.productionfeatures.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final ModelMapper mapper;
    private final PostRepo postRepo;

    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = postRepo.findAll();
        return posts.stream().map(post -> mapper.map(post, PostDto.class)).toList();
    }

    @Override
    public PostDto createPost(PostDto post) {
        PostEntity postToCreate = mapper.map(post, PostEntity.class);
        PostEntity savedPost = postRepo.save(postToCreate);
        return mapper.map(savedPost, PostDto.class);

    }

    @Override
    public PostDto getPostById(Long id) {
        PostEntity post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No post exist for id "+id));
        return mapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updateById(Long id, PostDto updates) {
        PostEntity post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No post exist for id "+id));
        mapper.map(updates, post);
        return mapper.map(postRepo.save(post), PostDto.class);


    }
}
