package com.sketch.productionfeatures.controllers;

import com.sketch.productionfeatures.dto.PostDto;
import com.sketch.productionfeatures.entities.PostEntity;
import com.sketch.productionfeatures.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping(path = "/post")
    ResponseEntity<List<PostDto>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    ResponseEntity<PostDto> createPost(@RequestBody PostDto post){
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @GetMapping(path="/post/{id}")
    ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping(path = "/post/{id}")
    ResponseEntity<PostDto> updatePostById(@PathVariable Long id, @RequestBody PostDto updates){
        return new ResponseEntity<>(postService.updateById(id, updates), HttpStatus.OK);
    }




}
