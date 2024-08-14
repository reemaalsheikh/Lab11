package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Categories")
public class Category {

//• category_id
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
private Integer categoryId;

// • name
@NotEmpty(message = "Name should not be Empty!")
@Column(columnDefinition = "varchar(20) not null unique")
 private String name;


}
