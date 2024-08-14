package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.User;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    // Get all Categories
    @GetMapping("/get")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    // Add new category
    @PostMapping("/add")
    public ResponseEntity addNewCategory (@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category Successfully added!"));
    }

    // Update User
    @PutMapping("/update/{categoryId}")
    public  ResponseEntity updateCategory(@PathVariable Integer categoryId,@Valid @RequestBody Category category , Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.updateCategory(categoryId,category);
        return ResponseEntity.status(200).body(new ApiResponse("Category Successfully updated!"));
    }

    // Delete User
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(200).body(new ApiResponse("Category Successfully deleted!"));
    }


    @GetMapping("getByName/{name}")
    public ResponseEntity getCategoryByName (@PathVariable String name){
        return ResponseEntity.status(200).body(categoryService.findCategoryByname(name));
    }
}
