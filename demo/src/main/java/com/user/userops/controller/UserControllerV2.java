package com.user.userops.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userops.model.User;
import com.user.userops.model.UserV2;
import com.user.userops.service.UserService;

@RestController
@RequestMapping("/api")
public class UserControllerV2 {

	public static ArrayList<User> users=new ArrayList<User>();
	public static ArrayList<UserV2> usersv2=new ArrayList<UserV2>();

	public static int id=0;


	// dependency injection
	@Autowired
	UserService userService;


	@GetMapping(path = "/users/v1", produces = "application/json" )
	public List<User> getAllUsersV1(){

		users.add(new User(++id, "Roger", LocalDate.now().minusYears(20),"Address info"));
		return users;

	}

	@GetMapping(path = "/users/v2" , produces = "application/xml")
	public List<UserV2> getAllUsersV2(){

		UserV2.Address addr = UserV2.Address.builder().AddressLine1("Line1")
				.AddressLine2("Line2").Street("Street#5").City("Chennai").State("TN").build();

		usersv2.add(new UserV2(++id, "Roger", LocalDate.now().minusYears(20),addr));
		return usersv2;

	}
	

}
