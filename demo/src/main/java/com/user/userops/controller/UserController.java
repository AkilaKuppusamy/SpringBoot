package com.user.userops.controller;

import com.user.userops.model.User;
import com.user.userops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class UserController {

	// dependency injection
	@Autowired
	UserService userService;
	MessageSource messageSource;

	/* public UserController(UserService userService){
        this.userService=userService;
    }*/

	@GetMapping(path = "/users")
	public List<User> getAllUsers(){
		return userService.retrieveAllUsers();
	}

	// get a specific user
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getSpecificUser(@PathVariable int userId){
		User user = userService.getUser(userId);
		if(user==null) throw new UserNotFoundException("user with id"+ userId+" not found");
		//return user;
		return new ResponseEntity<User>(user,HttpStatusCode.valueOf(201));
	}

	// create a new user
	//request body , header,context url

	@PostMapping(path = "/users" , consumes = "application/json" , produces = "application/json")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User newUser = userService.addUser(user);
		if(newUser == null) {
			ResponseEntity.internalServerError().body(newUser);
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newUser.getId())
				.toUri();
		return ResponseEntity.created(uri).body(newUser);
		//return new ResponseEntity<User>(newUser,HttpStatusCode.valueOf(200));

	}

	@GetMapping(path = "/custom/message")
	public String welcomeMessage() {

		Locale locale = LocaleContextHolder.getLocale();
		System.out.println(locale.getDisplayName());
		return messageSource.getMessage("welcome.message", null, "Default Message", locale);


	}

	// delete a user
	@DeleteMapping(path="/users/{userId}")
	public ResponseEntity<User> deleteSpecificUser(@PathVariable int userId){
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();

	}

	// update a user
	@PutMapping(path="/users/{userId}")
	public ResponseEntity<User> updateSpecificUser(@PathVariable int userId,@RequestBody User user) {
		if(user.getName()== null && user.getDob()== null)
			return ResponseEntity.badRequest().build();
		return new ResponseEntity<User>(userService.updateUser(userId, user),HttpStatusCode.valueOf(201));
	}

	@PatchMapping("/users/{userId}")
	public ResponseEntity<User> updateSpecificInfo(@PathVariable int userId,@RequestBody User user) {
		if(user.getName()== null && user.getDob()== null && user.getAddress() == null)
			return ResponseEntity.badRequest().build();
		return new ResponseEntity<User>(userService.patchUser(userId,user),HttpStatusCode.valueOf(201));
	}

	@GetMapping(path = "/userbyname")
	public ResponseEntity<User> getUserByName(@RequestParam("name") String name) {
		Locale locale = LocaleContextHolder.getLocale();
		User user = userService.findUserByName(name);
		if(user == null)
			throw new UserNotFoundException(messageSource.getMessage("welcome.message", null, "Default Message", locale));
		return new ResponseEntity<User>(userService.findUserByName(name),HttpStatusCode.valueOf(201));
	}
}
