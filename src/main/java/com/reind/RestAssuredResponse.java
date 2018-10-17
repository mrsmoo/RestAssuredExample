package com.reind;

import io.restassured.response.Response;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Sample TestCases where the response  json is acessed using the restassured reponse and then verified.
 */


public class RestAssuredResponse {

    Configuration configuration;
    Response response;

    public RestAssuredResponse() {
        configuration = new Configuration();
    }

    @Test
    public void testWithTitleAlone() throws IOException {
        response = configuration.getResponse("?t=frozen");
        assertTrue(response.jsonPath().getString("Title").equals("Frozen"));
        assertTrue(response.jsonPath().getString("Language").equals("English, Icelandic"));
    }

    @Test
    public void testWithIMDBTitleAlone() throws IOException {
        response = configuration.getResponse("?i=tt2294629");
        assertTrue(response.jsonPath().getString("Title").equals("Frozen"));
        assertTrue(response.jsonPath().getString("Language").equals("English, Icelandic"));
    }
}