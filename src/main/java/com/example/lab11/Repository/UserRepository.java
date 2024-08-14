package com.example.lab11.Repository;

import com.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserId(Integer userId);


    User findUserByEmail(String email);
}
