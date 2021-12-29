package com.geekbrains.backend.test.imgur;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ImgurApiFunctionTest extends ImgurApiAbstractTest {

    private static Response CURRENT_IMAGE_ID;

    @Test
    void getAccountBase() {
        String userName = "max90003";
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .body("data.id", is(157973001))
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .get("account/" + userName);
    }

    @Test
    void postImageTest() {
        CURRENT_IMAGE_ID = given()
                .spec(requestSpecification)
                .multiPart("image", getFileResource("Снимок.PNG"))
                .log()
                .all()
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post("upload");
    }

    @Test
    void TestGetImage() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .body("status", is(200))
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .get("image" + CURRENT_IMAGE_ID);
    }

    @Test
    void testCreateComment() {
        given()
                .spec(requestSpecification)
                .formParam("image_id", "1JAmS3X")
                .formParam("comment", "Hello Test")
                .log()
                .all()
                .expect()
                .body("success", is(true))
                .body("status", is(200))
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post("comment");
    }

    @Test
    void deleteImageById() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .body("status", is(200))
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .delete("image/" + CURRENT_IMAGE_ID);
    }

}
