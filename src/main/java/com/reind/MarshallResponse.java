package com.reind;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Sample TestCases where the response  json is marshalled into a pojo(MovieResponse) and the feilds are verfied
 */

public class MarshallResponse {

    Configuration configuration;
    Response response;
    ObjectMapper objectMapper;

    public MarshallResponse() {
        configuration = new Configuration();
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
    }

    @Test
    public void testWithTitleAlone() throws IOException {
        response = configuration.getResponse("?t=frozen");
        MovieResponse movieResponse = objectMapper.readValue(response.getBody().asString(), MovieResponse.class);
        assertTrue(movieResponse.getTitle().equals("Frozen"));
        assertTrue(movieResponse.getLanguage().equals("English, Icelandic"));

    }

    @Test
    public void testWithIMDBTitleAlone() throws IOException {
        Response response = configuration.getResponse("?i=tt2294629");
        MovieResponse movieResponse = objectMapper.readValue(response.getBody().asString(), MovieResponse.class);
        assertTrue(movieResponse.getTitle().equals("Frozen"));
        assertTrue(movieResponse.getLanguage().equals("English, Icelandic"));
    }
}
