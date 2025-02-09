package com.codemeanslove.journalApp.services;

import com.codemeanslove.journalApp.entity.User;
import com.codemeanslove.journalApp.repository.UserRepository;
import com.codemeanslove.journalApp.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;//****************//

@SpringBootTest// uske bina null ptr exception aayeegi bcoz application context hi nhi chala to bhir beam bhi create nhi hui hogi  that's why userrRepo null tha
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;
//----------------------------------------------------
//    there are some annotations jo hame sabke pahle chalanaa h to ham ye use kr lege
//    @BeforeAll @BeforeEach @AfterAll @AfterEach
//    like kuch chije initialize krni h before running each test we can use @BeforeEach
//    100 test cases h but unke run hone k pahle ye test case chale so we can use @BeforeAll
//    @AfterEach har ek test case ke khatm hote h ye test case chalega
//    @AfterAll  jab sare khatm ho jayege to e chalega

//   Example hamne ek resouce use kiye @BeforeAll and close kr diye @AfterAll
//   @BeforeAll we created a csv and then we done some tests then we delete it in @AfterAll method

//-------------------custom Source---------------------------------
    @Autowired
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class) //***************//
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }

//----------------------------------------------------

    @Disabled
    @ParameterizedTest
    //ans many more @CsvFileSource and @CsvFileSources etc
    @CsvSource({
            "2,3,5",
            "3,6,9"
    })
    public void textMultiple(int a,int b,int expected){
        assertEquals(expected,a+b);
    }

//---------------------------------------------------------------
//@Disabled
@ParameterizedTest
@ValueSource( strings = {                 // or ||  @CsvSource({
        "prem",
        "jaya",
        "ram"
})
public void findByUserNameTest(String name){
    User user = userRepository.findByUserName(name);
}
//----------------------------------------------------
//    @Disabled
    @Test
    public void findByUserNameTest(){
        User user = userRepository.findByUserName("prem");
        assertTrue(!user.getJournalEntries().isEmpty());//aree emply h to fail hi hoga re baba

        //we can check null values
//        assertNotNull(userRepository.findByUserName("prem"));

        //we can check true or false
//        assertTrue(5>3);
    }
//-------------------------------------------------------
//    @Test
//    public void add(){
//        assertEquals(4,4+1);
//    }

}//end of class


// 1 -->  .\mvnw test

//BUILD FAILURE
//[INFO] ------------------------------------------------------------------------
//        [INFO] Total time:  11.403 s
//[INFO] Finished at: 2025-02-06T14:37:52+05:30
//        [INFO] ------------------------------------------------------------------------
//        [ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.5.2:test (default-test) on project journalApp:


//inside parent  spring-boot-starter-parent   eske aanda aata h to likhle ki jarurat nhi h
//    spring-boot-maven-plugin

//______________

//for the test coverage
// we must have plugin -> code_coverage for java
// right click ->  more Run/Debug -> Run with coverage
//there will be coverage result in up right corner (mere esme nhi hua)





