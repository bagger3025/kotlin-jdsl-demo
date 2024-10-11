package com.example.demo

import jakarta.annotation.PostConstruct
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(private val postRepository: PostRepository) {
    fun getPosts(page: Int): List<Post> {
        val pageable = PageRequest.of(page, 5)
        val ret = postRepository.findPage(pageable) {
            select(
                entity(Post::class)
            ).from(
                entity(Post::class)
            ).orderBy(
                path(Post::priority).plus(3).desc()
            )
        }

        return ret.content.mapNotNull { it }
    }

    fun getAllPosts(): List<Post> {
        val ret = postRepository.findAll()
        return ret.mapNotNull { it }
    }

    @PostConstruct
    @Transactional
    fun insertData() {
        postRepository.save(Post(title = "Title1", priority = 12, id = null))
        postRepository.save(Post(title = "Title2", priority = 11, id = null))
        postRepository.save(Post(title = "Title3", priority = 10, id = null))
        postRepository.save(Post(title = "Title4", priority = 9, id = null))
        postRepository.save(Post(title = "Title5", priority = 8, id = null))
        postRepository.save(Post(title = "Title6", priority = 7, id = null))
        postRepository.save(Post(title = "Title7", priority = 6, id = null))
        postRepository.save(Post(title = "Title8", priority = 5, id = null))
        postRepository.save(Post(title = "Title9", priority = 4, id = null))
        postRepository.save(Post(title = "Title10", priority = 3, id = null))
        postRepository.save(Post(title = "Title11", priority = 2, id = null))
        postRepository.save(Post(title = "Title12", priority = 1, id = null))
    }
}