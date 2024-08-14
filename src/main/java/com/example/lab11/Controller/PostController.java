package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;


    // Get all the Posts
    @GetMapping("/get")
    public ResponseEntity getAllPosts() {
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    // Add new Post
    @PostMapping("/add")
    public ResponseEntity addNewPost(@Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.addNewPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Post Successfully added!"));
    }

    // Update Post
    @PutMapping("/update/{postId}")
    public  ResponseEntity updatePost(@PathVariable Integer postId,@Valid @RequestBody Post post , Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.updatePost(postId,post);
        return ResponseEntity.status(200).body(new ApiResponse("Post Successfully updated!"));
    }

    // Delete Post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity deletePost (@PathVariable Integer postId){
        postService.deletePost(postId);
        return ResponseEntity.status(200).body(new ApiResponse("Post Successfully deleted!"));
    }

    // 1.get all posts by user_id
    @GetMapping("/findP/{userId}")
    public ResponseEntity findpostByUserId(@PathVariable Integer userId){
        List<Post> findPosts = postService.getPostsByUserId(userId);
        return ResponseEntity.status(200).body(findPosts);
    }

   //2.get post by title
    @GetMapping("/findT/{title}")
    public ResponseEntity findpostByTitle(@PathVariable String title){
        List<Post> findPostsByTitle = postService.getPostsByTitle(title);
        return ResponseEntity.status(200).body(findPostsByTitle);
    }

  //3.get all post before date by date
    @GetMapping("/findD/{publishDate}/{userId}")
    public ResponseEntity findPostsbefreDate (@PathVariable LocalDate publishDate, @PathVariable Integer userId){
        List<Post> PDates = postService.getPostsBeforeDate(publishDate,userId);
        return ResponseEntity.status(200).body(PDates);
    }

    //4.find post by Category id
    @GetMapping("/PC/{categoryId}")
    public ResponseEntity findPostsByCategoryId(@PathVariable Integer categoryId){
        List <Post>  pc = postService.findPostsByCategoryId(categoryId);
        return ResponseEntity.status(200).body(pc);
    }



}
