package com.exam.Config;

import com.exam.services.Impl.UserDetailServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailServiceImpl userDetailService;


    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestTokenHeader = request.getHeader("Authorization");
        System.out.println(requestTokenHeader);
        String username = null;
        String jwtToken = null;

        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){

            jwtToken = requestTokenHeader.substring(7);

            try{
                username =  jwtUtils.extractUsername(jwtToken);

            }catch(ExpiredJwtException err){
                err.printStackTrace();
                System.out.println("jwt token has expired");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("error");
            }
        }else{
            System.out.println("Invalid Token, Not start with Bearer");
        }

        //Validate
        if(username != null && SecurityContextHolder.getContext().getAuthentication() ==null){

           UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

           if(jwtUtils.validateToken(jwtToken , userDetails)){

               //token is Valid
               UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails , null, userDetails.getAuthorities());
               usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
           }
        } else {

            System.out.println("Token is Not Valid");

        }

        filterChain.doFilter(request , response);
    }
}
