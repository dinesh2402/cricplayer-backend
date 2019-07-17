package com.stackroute.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userAuthenticationRepository;

	@Autowired
	public UserServiceImpl(UserRepository userAuthenticationRepository) {
		super();
		this.userAuthenticationRepository = userAuthenticationRepository;
	}

	@Override
	public User findByUserNameAndPassword(String userName, String password) throws UserNotFoundException {
		User userFound = this.userAuthenticationRepository.findByUserNameAndPassword(userName, password);

		if (userFound == null) {
			throw new UserNotFoundException("User Not Available");
		}

		return userFound;
	}

	@Override
	public User saveUser(User user) throws UserAlreadyExistsException {

		User userNew = this.userAuthenticationRepository.findByUserName(user.getUserName());

		if (userNew == null)
			this.userAuthenticationRepository.save(user);

		else
			throw new UserAlreadyExistsException("User Already Exist");

		return user;
	}

}
