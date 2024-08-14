package com.example.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Users")
public class User {

//• user_id
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
private Integer userId;

//• username
@NotEmpty(message = "User Name should not be Empty!")
@Size(min=5 , message ="Name Length should be more than 4")
@Column(columnDefinition = "varchar(30) not null unique")
private String userName;

//• password
@NotEmpty(message = "Password should not be Empty!")
@Column(columnDefinition = "varchar(50) not null")
private String password;

//• email
@Email
@Column(columnDefinition = "varchar(50) not null unique ")
private String email;

//• registration_date
@Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate registrationDate;

}
