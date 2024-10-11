package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(private val postService: PostService) {

    @GetMapping
    fun getPosts(@RequestParam(value = "page", defaultValue = "0") page: Int): List<Post> {
        return postService.getPosts(page)
    }

    @GetMapping("/post")
    fun getAllPosts(): List<Post> {
        return postService.getAllPosts()
    }
}