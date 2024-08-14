package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    // Get
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Add
    public void addCategory (Category category) {
        categoryRepository.save(category);
    }


    // Update
    public void updateCategory (Integer categoryId,Category category) {
        Category c = categoryRepository.findCategoryByCategoryId(categoryId);
        if (c == null) {
            throw new ApiException("Category not found");
        }
        c.setName(category.getName());
        categoryRepository.save(c);

    }

    // Delete User
    public void deleteCategory (Integer categoryId) {
        Category c = categoryRepository.findCategoryByCategoryId(categoryId);
        if (c == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(c);
    }


    public Category findCategoryByname (String name) {
        Category c = categoryRepository.findCategoryByNameStartingWith(name);
        if (c == null) {
            throw new ApiException("Category name not found");
        }
        return c;
    }

}
