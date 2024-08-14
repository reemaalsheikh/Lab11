package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Get all the Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Add new User
    public void addUser (User user) {
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);
    }


    // Update User
    public void updateUser (Integer userId,User user) {
        User u = userRepository.findUserByUserId(userId);
        if (u == null) {
            throw new ApiException("User not found");
        }
        u.setUserName(user.getUserName());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        userRepository.save(u);
    }

    // Delete User
    public void deleteUser(Integer userId) {
        User u = userRepository.findUserByUserId(userId);
        if (u == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new ApiException("User not found");
        }
        return user;
    }


}
