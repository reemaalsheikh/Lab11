package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // Get all
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    //Add new post
    public void addNewPost(Post post) {
        Category c = categoryRepository.findCategoryByCategoryId(post.getCategoryId());
        User u = userRepository.findUserByUserId(post.getUserId());
        if (c == null || u == null) {
        throw new ApiException("Category or user id Not Found!");
    }
     post.setPublishDate(LocalDate.now());
     postRepository.save(post);
    }

    //Update post
    public void updatePost(Integer postId,Post post) {
        Post p = postRepository.findPostByPostId(postId);
        if (p == null) {
            throw new ApiException("Post Not Found!");
        }
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        postRepository.save(p);
    }

    //delete post
    public void deletePost(Integer postId) {
        Post p = postRepository.findPostByPostId(postId);
        if (p == null) {
            throw new ApiException("Post Not Found!");
        }
        postRepository.delete(p);
    }

    //1. get all posts by user_id
    public List<Post> getPostsByUserId(Integer userId) {
       List<Post> posts = postRepository.findPostByUserId(userId);
       if (posts.isEmpty()) {
           throw new ApiException("Posts Not Found!");
       }
       return posts;
    }

    //2.get post by title
    public List<Post> getPostsByTitle(String title) {
        List<Post> posts = postRepository.findPostByTitle(title);
        if (posts.isEmpty()) {
            throw new ApiException("Title Not Found!");
        }
        return posts;
    }


// 3.get all post before date by date
    public List<Post> getPostsBeforeDate(LocalDate publishDate , Integer userId) {
        List<Post> postDate = postRepository.findPostByPublishDateBeforeAndUserId(publishDate, userId);
        if (postDate.isEmpty()) {
            throw new ApiException("Publish date and user id  Not Found!");
        }
        return postDate;

    }

    //5.find post by Category id
    public List<Post> findPostsByCategoryId(Integer categoryId) {
        List<Post> posts = postRepository.findPostByCategoryId(categoryId);
        if (posts.isEmpty()) {
            throw new ApiException("Category id Not Found!");
        }
        return posts;
    }








}
