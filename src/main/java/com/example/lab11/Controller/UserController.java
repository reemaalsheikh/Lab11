package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;


    // Get all the Users
    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    // Add new User
    @PostMapping("/add")
    public ResponseEntity addNewUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Successfully added!"));
    }

    // Update User
    @PutMapping("/update/{userId}")
    public  ResponseEntity updateUser(@PathVariable Integer userId,@Valid @RequestBody User user , Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.updateUser(userId,user);
        return ResponseEntity.status(200).body(new ApiResponse("User Successfully updated!"));
    }

    // Delete User
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body(new ApiResponse("User Successfully deleted!"));
    }

    @GetMapping("/getByE/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }



}
