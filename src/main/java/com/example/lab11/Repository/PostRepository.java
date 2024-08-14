package com.example.lab11.Repository;

import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostByPostId(Integer postId);

//    get all post by user_id
    List<Post> findPostByUserId(Integer userId);

//    get post by title,
    @Query("select p from Post p where p.title=?1")
    List<Post> findPostByTitle(String title);

  //get all post before date by date
   List <Post> findPostByPublishDateBeforeAndUserId(LocalDate publishDate, Integer userId);

   @Query("select p from Post p where p.categoryId=?1")
   List <Post> findPostByCategoryId(Integer categoryId);


}
