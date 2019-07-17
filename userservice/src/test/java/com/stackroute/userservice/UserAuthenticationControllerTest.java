package com.stackroute.userservice;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.controller.UserAuthenticationController;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService authenticationService;

    private User user;
    private User duplicateUser;

    @InjectMocks
    private UserAuthenticationController authenticationController;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();

        user = new User();
        user.setUserName("testUser");
        user.setFirstName("TestFirstName");
        user.setLastName("TestLastName");
        user.setContactNo("100");
        user.setPassword("123456");
        user.setUserAddedDate(new Date());

        duplicateUser = new User();
        duplicateUser.setUserName("wrongUser");
        duplicateUser.setFirstName("test");
        duplicateUser.setLastName("test");
        duplicateUser.setPassword("123456");
        duplicateUser.setContactNo("102");
        duplicateUser.setUserAddedDate(new Date());


    }

    @Test
    public void testRegisterUser() throws Exception {

        Mockito.when(authenticationService.saveUser(user)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());

    }
    
    @Test
    public void testRegisterUserFailure() throws Exception {
        Mockito.when(authenticationService.saveUser((User)Mockito.any())).thenThrow(new UserAlreadyExistsException("User already exisits"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(duplicateUser)))
                .andExpect(MockMvcResultMatchers.status().isConflict()).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void testRegisterUserFailureError() throws Exception {
        Mockito.when(authenticationService.saveUser(Mockito.any())).thenThrow(new NumberFormatException());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError()).andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testLoginUserSuccess() throws Exception {

        String email = "testUser";
        String password = "123456";


        Mockito.when(authenticationService.saveUser(user)).thenReturn(user);
        Mockito.when(authenticationService.findByUserNameAndPassword(email, password)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    
    
    @Test
    public void testLoginUserFailure() throws Exception {
        Mockito.when(authenticationService.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenThrow(new UserNotFoundException("User Not Found"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized()).andDo(MockMvcResultHandlers.print());
    }
    
   
    @Test
    public void testLoginUserFailureError() throws Exception {
        Mockito.when(authenticationService.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenThrow(new IndexOutOfBoundsException("Unexpected Error"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError()).andDo(MockMvcResultHandlers.print());
    }

    // Parsing String format data into JSON format
    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;
    }
}
