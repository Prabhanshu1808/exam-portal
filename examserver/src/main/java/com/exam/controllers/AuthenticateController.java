package com.exam.controllers;

import com.exam.Config.JwtUtils;
import com.exam.helper.UserNotFoundException;
import com.exam.models.JwtRequest;
import com.exam.models.JwtResponse;
import com.exam.services.Impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtils jwtUtils;

    //Generate Token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try{

            authenticate(jwtRequest.getUsername() , jwtRequest.getPassword());

        }catch (UserNotFoundException e){
             e.printStackTrace();
             throw new Exception("User not Found");
        }

        ///////////////authenticate

        UserDetails userDetails = this.userDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));

    }



    private void authenticate(String username , String password) throws Exception {

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username , password));

        }catch (DisabledException e){

            throw new Exception("USER DISABLED" + e.getMessage());
        }catch (BadCredentialsException e){

            throw new Exception("Invalid Credential" + e.getMessage());
        }


    }

}
