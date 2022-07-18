package com.exam.services.Impl;


import com.exam.helper.UserFoundException;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.net.UnknownServiceException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;



    //Creating User
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local =  this.userRepository.findByUsername(user.getUsername());
       if(local != null){
           System.out.println("User is already there!!");
           throw new UserFoundException();
       }
       else{
           //Create User
           for(UserRole ur : userRoles){
               roleRepository.save(ur.getRole());
           }

           user.getUserRoles().addAll(userRoles);
           local = this.userRepository.save(user);
       }
        return local;
    }

    //Getting User by UserName
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    //Deleting User by User Id
    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    //update
}
