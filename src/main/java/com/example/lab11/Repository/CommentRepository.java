package com.example.lab11.Repository;

import com.example.lab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Comment findCommentBycommentId(Integer commentId);

//    get all comment for one post by post_id
   @Query("select c from Comment c where c.postId=?1")
    List<Comment> findCommentByPostId(Integer postId);

    @Query("select c from Comment c where c.commentDate=?1")
    List <Comment> getCommentByCommentDate(LocalDate commentDate);



}

