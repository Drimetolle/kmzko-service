package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.domains.conveyor.Node;
import com.kmzko.configurator.entity.user.PersonalConveyor;
import com.kmzko.configurator.entity.user.PersonalQuestionnaire;
import com.kmzko.configurator.entity.user.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
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
//        User user = new User();
//        user.setUsername("user");
//        userService.save(user);
//
//        conveyorDetailService.save(new PersonalConveyor("conv1", ConveyorType.TAPE, new ArrayList<>(), user, new ArrayList<>()));
//        questionnaireDetailService.save(new PersonalQuestionnaire("ques1", ConveyorType.TAPE, new ArrayList<>(), user));
//
//        User secondUser = new User();
//        secondUser.setUsername("secondUser");
//        userService.save(secondUser);
//
////        conveyorDetailService.save(new PersonalConveyor("conv2", ConveyorType.TAPE, new ArrayList<>(), secondUser, new ArrayList<>()));
//        questionnaireDetailService.save(new PersonalQuestionnaire("ques2", ConveyorType.TAPE, new ArrayList<>(), secondUser));
    }

    @Before
    public void setUser() {
        globalUser = userService.findByUsername("user");
    }

    @Test
    public void getAllUserConveyors() {
        Set<PersonalConveyor> conveyors = userService.getAllUserConveyors(globalUser.getUsername());

        assertEquals(conveyors.size(), 1);
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