package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // Get all
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    //Add new post
    public void addNewComment(Comment comment) {
        Post p = postRepository.findPostByPostId(comment.getPostId());
        User u = userRepository.findUserByUserId(comment.getUserId());

        if (p == null || u == null) {
            throw new ApiException("Post or user id Not Found!");
        }
        comment.setCommentDate(LocalDate.now());
        commentRepository.save(comment);
    }

    //Update post
    public void updateComment(Integer commentId,Comment comment) {
        Comment c = commentRepository.findCommentBycommentId(commentId);
        if (c == null) {
            throw new ApiException("Comment Not Found!");
        }
       c.setContent(comment.getContent());
        commentRepository.save(c);
    }


    //delete post
    public void deleteComment(Integer commentId) {
       Comment c = commentRepository.findCommentBycommentId(commentId);
        if (c == null) {
            throw new ApiException("Comment Not Found!");
        }
        commentRepository.delete(c);
    }

    //    get all comment for one post by post_id
    public List<Comment> getCommentsByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findCommentByPostId(postId);
        if (comments.isEmpty()) {
            throw new ApiException("Post Id Not Found!");
        }
        return comments;
    }

    public List<Comment> getCommentsDate (LocalDate commentDate) {
        List<Comment> comments = commentRepository.getCommentByCommentDate(commentDate);
        if (comments.isEmpty()) {
            throw new ApiException("Comment date and Post Id Not Found!");
        }
        return comments;
    }


}
