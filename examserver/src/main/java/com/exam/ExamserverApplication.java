package com.exam;

import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting code");

//		User user = new User();
//
//		user.setFirstname("prabhanshu");
//		user.setLastname("kansal");
//		user.setUsername("prabhanshu123");
//		user.setPassword("Lucent");
//		user.setEmail("chatmolie222@gmail.com");
//		user.setProfile("default.png");
//
//		Role role1 = new Role();
//		role1.setRoleId(34L);
//		role1.setRoleName("ADMIN");
//
//		Set<UserRole> userRoleSet =  new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//
//
//		userRoleSet.add(userRole);
//
//		User user1 = this.userService.createUser(user , userRoleSet);
//		System.out.println(user1.getUsername());
	}


}
