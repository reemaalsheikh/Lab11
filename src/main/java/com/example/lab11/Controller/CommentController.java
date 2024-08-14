package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    // get All Comments
    @GetMapping("/get")
    public ResponseEntity getAllComments() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    // Add New Comment
    @PostMapping("/add")
    public ResponseEntity addNewComment(@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.addNewComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment Successfully added!"));
    }

    // Update comment
    @PutMapping("/update/{commentId}")
    public  ResponseEntity updatePost(@PathVariable Integer commentId,@Valid @RequestBody Comment comment , Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.updateComment(commentId,comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment Successfully updated!"));
    }

    // Delete comment
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity deletePost (@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.status(200).body(new ApiResponse("Comment Successfully deleted!"));
    }

    //4.get all comment for one post by post_id
    @GetMapping("find/CByP/{postId}")
    public ResponseEntity findCommentsByPostId(@PathVariable Integer postId){
        List<Comment> findCByP = commentService.getCommentsByPostId(postId);
        return ResponseEntity.status(200).body(findCByP);

    }

    @GetMapping("/date/{commentDate}")
    public ResponseEntity getPostsDate (@PathVariable LocalDate commentDate){
        return ResponseEntity.status(200).body(commentService.getCommentsDate(commentDate));
    }


}
