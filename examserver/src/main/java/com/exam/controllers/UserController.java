package com.exam.controllers;


import com.exam.Config.JwtUtils;
import com.exam.helper.UserFoundException;
import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

     @Autowired
     private UserService userService;

     @Autowired
     UserRepository userRepository;

     @Autowired
     RoleRepository roleRepository;

     @Autowired
     JwtUtils jwtUtils;

     //creating User
     @PostMapping("/")
     public User createUser(@RequestBody User user) throws Exception {

       user.setProfile("default.png");

       Set<UserRole> roles = new HashSet<>();
       Role role = new Role();
       role.setRoleId(45L);
       role.setRoleName("NORMAL");
       roles.add(new UserRole());

       UserRole userRole = new UserRole();
       userRole.setUser(user);
       userRole.setRole(role);

       roles.add(userRole);

       return this.userService.createUser(user, roles);

     }

    //get the user by username
    @GetMapping("/{username}")
      public User getUser(@PathVariable("username") String username) {
      return this.userService.getUser(username);
    }

    //delete the user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
       this.userService.deleteUser(userId);
    }

    //Update user

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
         return exceptionHandler(ex);
    }


   }

