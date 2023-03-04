package com.user.userops.service;

import com.user.userops.controller.UserNotFoundException;
import com.user.userops.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	public static ArrayList<User> users=new ArrayList<User>();

	public static int id=0;

	static {
		users.add(new User(++id, "Roger", LocalDate.now().minusYears(20),"Address#1,Kochi"));
		users.add(new User(++id, "Rafa", LocalDate.now().minusYears(18),"Address#1,Chennai"));
		users.add(new User(++id, "Novak", LocalDate.now().minusYears(15),"Address#1,Hyd"));
	}

	public List<User> retrieveAllUsers() {
		return users;
	}

	public User getUser(int userId){

		for (User user:users){
			if(user.getId()==userId)return user;
		}
		return null;
	}

	public User addUser(User user) {
		//User newuser = User.builder().id(user.setId(++id)).name(user.getName()).dob(user.getDob()).build();
		user.setId(++id);
		users.add(user);
		return user;
	}

	public void deleteUser(int userId){
		users.removeIf(x->x.getId()==userId);
	}

	public User updateUser(int userId, User user) {
		User updateUser = users.stream().filter(x->x.getId()== userId).findFirst().orElse(null);
		if(updateUser == null) throw new UserNotFoundException("user with "+userId+" not found"); 
		String name = user.getName();
		LocalDate dob = user.getDob();
		String addr = user.getAddress();
		updateUser.setName(name);
		updateUser.setDob(dob);
		updateUser.setAddress(addr);
		return updateUser;
	}

	public User patchUser(int userId, User user) {
		User updateUser = users.stream().filter(x->x.getId()== userId).findFirst().orElse(null);
		if(updateUser == null) throw new UserNotFoundException("user with "+userId+" not found"); 
		if(user.getName() != null)
			updateUser.setName(user.getName());
		if(user.getDob() != null)
			updateUser.setDob(user.getDob());
		if(user.getAddress() != null)
			updateUser.setAddress(user.getAddress());
		return updateUser;
	}

	public User findUserByName(String name) {
		for(User user:users) {
			if(user.getName().equalsIgnoreCase(name))return user;
		}
		return null;	
	}
}
