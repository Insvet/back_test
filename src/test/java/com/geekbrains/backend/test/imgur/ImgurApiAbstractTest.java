package com.geekbrains.backend.test.imgur;

import java.util.Properties;

import com.geekbrains.backend.test.FunctionalTest;
import io.restassured.RestAssured;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.AuthenticationSpecificationImpl;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ImgurApiAbstractTest extends FunctionalTest {

    protected static RequestSpecification requestSpecification;
    protected static ResponseSpecification responseSpecification;

    static {
        try {
            Properties properties = readProperties();
            RestAssured.baseURI = properties.getProperty("imgur-api-url");
            String TOKEN = properties.getProperty("imgur-api-token");
            requestSpecification = new RequestSpecBuilder()
                    .setAuth(new OAuth2Scheme())
                    .build();
            AuthenticationSpecification auth = new AuthenticationSpecificationImpl(requestSpecification);
            requestSpecification = auth.oauth2(TOKEN);

            responseSpecification = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectStatusLine("HTTP/1.1 200 OK")
                    .expectContentType(ContentType.JSON)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
