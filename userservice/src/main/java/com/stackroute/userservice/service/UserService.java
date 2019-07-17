package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;

public interface UserService {

	 public abstract User findByUserNameAndPassword(String userName, String password) throws UserNotFoundException;

	 public abstract User saveUser(User user) throws UserAlreadyExistsException;
}
