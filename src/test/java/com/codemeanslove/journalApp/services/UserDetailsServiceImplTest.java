package com.codemeanslove.journalApp.services;

import com.codemeanslove.journalApp.entity.User;
import com.codemeanslove.journalApp.repository.UserRepository;
import com.codemeanslove.journalApp.service.UserDetailsServiceImpl;
//import org.mockito.Mock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Mockito.*;/////////////////////////////////*******************

public class UserDetailsServiceImplTest {//this one will be very fast bcoz even application context is not loaded

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUsernameTest(){
        //jab ye method call hoga (db se koi connection nhi banana h)||(Actual repo call nhi krna h) direct ye user return krna h
        //Argument matcher ki place pr spcific bhi rakh sakte h like "shyam" call ho jab hi ye chalao
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("encrypted").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }

}

//   ^- if we dont even want application context to be loaded then

//@SpringBootTest
//public class UserDetailsServiceImplTest {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//     //          ^- eske aandar jo dependency h usko Mock krege
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Test
//    public void loadUserByUsernameTest(){
//        //jab ye method call hoga (db se koi connection nhi banana h)||(Actual repo call nhi krna h) direct ye user return krna h
//        //Argument matcher ki place pr spcific bhi rakh sakte h like "shyam" call ho jab hi ye chalao
//        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("encrypted").roles(new ArrayList<>()).build());
//        UserDetails user = userDetailsService.loadUserByUsername("ram");
//        Assertions.assertNotNull(user);
//    }
//
//}
