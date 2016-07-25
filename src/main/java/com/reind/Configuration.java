package com.reind;

import com.jayway.restassured.response.Response;

import java.net.MalformedURLException;

import static com.jayway.restassured.RestAssured.given;

/**
 * Simple Configuration Files which can be extended in future
 */

public class Configuration {
    String testURL = "http://www.omdbapi.com/";

    public String buildURLQueryString(String queryParamToBeAdded) throws MalformedURLException {
        return testURL + queryParamToBeAdded;
    }

    public Response getResponse(String URL) throws MalformedURLException {
        Response response =
                given()
                        //.log().all()
                        .contentType("application/json").get(String.valueOf(buildURLQueryString(URL)));
        return response;
    }
}