package com.gorest.userinfo;

import com.gorest.constants.Endpoints;
import com.gorest.model.UserPojo;
import gherkin.lexer.En;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;


public class UserSteps {

    @Step("Creating user with name: {0}, email: {1}, gender: {2}, status: {3}")
    public ValidatableResponse createUser(String name,String email,String gender,String status){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("access-token","7a256004c86ace9977c5143d9350b010da7c60b233b898d6a8b8476f594b398e");

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .queryParams(qParams)
                .when()
                .body(userPojo)
                .post()
                .then();
    }

    @Step("Extracting user details by userId: {0}")
    public HashMap<String, Object> extractUserByID(int userId) {
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("access-token","7a256004c86ace9977c5143d9350b010da7c60b233b898d6a8b8476f594b398e");

        return SerenityRest.given().log().all()
                .header("Authorization","Bearer 7a256004c86ace9977c5143d9350b010da7c60b233b898d6a8b8476f594b398e")
                .pathParam("userId", userId)
                .queryParams(qParams)
                .when()
                .get(Endpoints.GET_USER_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");

    }

    @Step("Updating user information with name: {0}, email: {1}, gender: {2}, status: {3}, userId: {4}")
    public ValidatableResponse updateUser(String name,String email,String gender,String status, int userId){

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("access-token","7a256004c86ace9977c5143d9350b010da7c60b233b898d6a8b8476f594b398e");


        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 7a256004c86ace9977c5143d9350b010da7c60b233b898d6a8b8476f594b398e")
                .pathParam("userId", userId)
                .queryParams(qParams)
                .when()
                .body(userPojo)
                .put(Endpoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step("Deleting the user by userId: {0}")
    public ValidatableResponse deleteUserById(int userId){
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("access-token","7a256004c86ace9977c5143d9350b010da7c60b233b898d6a8b8476f594b398e");

        return SerenityRest.given().log().all()
                .queryParams(qParams)
                .pathParam("userId", userId)
                .when()
                .delete(Endpoints.DELETE_USER_BY_ID)
                .then();

    }

    @Step("Getting the user by userId: {0}")
    public ValidatableResponse getUserById(int userId){
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("access-token","7a256004c86ace9977c5143d9350b010da7c60b233b898d6a8b8476f594b398e");

        return SerenityRest.given().log().all()
                .pathParam("userId", userId)
                .queryParams(qParams)
                .when()
                .get(Endpoints.GET_USER_BY_ID)
                .then();

    }

}
