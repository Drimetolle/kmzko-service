package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.entity.user.conveyor.PersonalConveyor;
import com.kmzko.configurator.entity.user.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private static UserService userService;

    @Autowired
    private static PersonalConveyorDetailService conveyorDetailService;

    @Autowired
    private static PersonalQuestionnaireDetailService questionnaireDetailService;

    private User globalUser;

    @AfterClass
    public static void setUpContext() {

    }

    @Before
    public void setUser() {
//        globalUser = userService.findByUsername("user");
    }

    @Test
    public void getAllUserConveyors() {

    }

    @Test
    public void getAllUserQuestionnaires() {
    }

    @Test
    public void testGetAllUserConveyors() {
    }

    @Test
    public void testGetAllUserQuestionnaires() {
    }

    @Test
    public void getUserConveyorsById() {
    }

    @Test
    public void getUserQuestionnairesById() {
    }
}