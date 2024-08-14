package com.example.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Comments")
public class Comment {
//• comment_id
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
private Integer commentId;

// • user_id
@NotNull(message = "User Id should not be Null!")
@Column(columnDefinition = "int not null ")
private Integer userId;

//• post_id
@NotNull(message = "Post Id should not be Null!")
@Column(columnDefinition = "int not null ")
private Integer postId;

//• content
@NotEmpty(message = "Content should not be Empty!")
@Column(columnDefinition = "varchar(50) not null")
private String content;

//• comment_date
@Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate commentDate;



}
