package com.stackroute.userservice;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

public class UserAuthenticationServiceTest {

    @Mock
    private UserRepository autheticationRepository;

    private User user;
    @InjectMocks
    private UserServiceImpl authenticationService;

    Optional<User> optional;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserName("testUser");
        user.setFirstName("TestFirstName");
        user.setLastName("TestLastName");
        user.setContactNo("100");
        user.setPassword("123456");
        user.setUserAddedDate(new Date());
        optional = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistsException {

        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        User flag = authenticationService.saveUser(user);
        Assert.assertEquals("Register User", user, flag);

    }


    @Test()
    public void testSaveUserFailure() throws UserAlreadyExistsException {

        Mockito.when(autheticationRepository.findById("testemail")).thenReturn(optional);
        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        User flag = authenticationService.saveUser(user);
        Assert.assertEquals("Cannot Register User", user, flag);

    }

    @Test
    public void testFindByUserNameAndPassword() throws UserNotFoundException {
        Mockito.when(autheticationRepository.findByUserNameAndPassword("testUser", "123456")).thenReturn(user);
        User fetchedUser = authenticationService.findByUserNameAndPassword("testUser", "123456");
        Assert.assertEquals("testUser", fetchedUser.getUserName());
    }
}

