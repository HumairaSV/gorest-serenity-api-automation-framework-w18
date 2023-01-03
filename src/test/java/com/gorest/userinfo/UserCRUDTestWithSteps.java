package com.gorest.userinfo;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Objects;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTestWithSteps extends TestBase {

    static String name = "Happy"+ TestUtils.getRandomValue();
    static String email= "Feet"+ TestUtils.getRandomValue()+"@gmail.com";
    static String gender= "male";
    static String status = "active";
    static int userId;

    @Steps
    UserSteps userSteps;

    @Title("T1 - This will create a new user")
    @Test
    public void test001(){
        ValidatableResponse response = userSteps.createUser(name,email,gender,status).statusCode(201);
        userId = response.log().all().extract().path("id");
    }

    @Title("T2 - Verifying if the user was added to the list")
    @Test
    public void test002(){
        HashMap<String, Object> userMap = userSteps.extractUserByID(userId);
        Assert.assertThat(userMap, hasValue(name));
    }

    @Title("T3 - Update the user details and verify the updated information")
    @Test
    public void test003(){
        name = name + "_updated";
        userSteps.updateUser(name,email,gender,status,userId);
        //verifying if the information has been updated
        HashMap<String, Object> userMap = userSteps.extractUserByID(userId);
        Assert.assertThat(userMap, hasValue(name));

    }

    @Title("T5 - Delete the booking and verify if the booking has been deleted")
    @Test
    public void test004(){
        userSteps.deleteUserById(userId).statusCode(204);
        userSteps.getUserById(userId).statusCode(404);

    }
}
