package com.exam.services;

import com.exam.models.User;
import com.exam.models.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    //Creating User
    public User createUser(User user , Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);

    //delete User by Id
    public void deleteUser(Long userId);
}
