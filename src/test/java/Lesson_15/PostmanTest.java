package Lesson_15;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostmanTest {

    @Test
    void testGetMethod() {
        RestAssured.baseURI = "https://postman-echo.com";
        Response response = RestAssured.given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .get("/get");

        assertEquals(200, response.getStatusCode(), "Неверный статус");

        String foo1 = response.jsonPath().getString("args.foo1");
        String foo2 = response.jsonPath().getString("args.foo2");

        assertEquals("bar1", foo1, "Неверное значение foo1");
        assertEquals("bar2", foo2, "Неверное значение foo2");
    }

    @Test
    void testPostMethod() {
        RestAssured.baseURI = "https://postman-echo.com";
        Response response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8") // Устанавливаем кодировку UTF-8
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .post("/post");

        assertEquals(200, response.getStatusCode(), "Неверный статус");

        String foo1Value = response.jsonPath().getString("form.foo1");
        String foo2Value = response.jsonPath().getString("form.foo2");

        assertEquals("bar1", foo1Value, "Неверное значение foo1");
        assertEquals("bar2", foo2Value, "Неверное значение foo2");
    }

    @Test
    void testPutMethod() {
        RestAssured.baseURI = "https://postman-echo.com";
        String body = "This is expected to be sent back as part of response body.";
        Response response = RestAssured.given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .body(body)
                .put("/put");

        assertEquals(200, response.getStatusCode(), "Неверный статус");

        String foo1 = response.jsonPath().getString("args.foo1");
        String foo2 = response.jsonPath().getString("args.foo2");
        String bodyResponse = response.jsonPath().getString("data");

        assertEquals(body, bodyResponse, "Неверное значение body");
        assertEquals("bar1", foo1, "Неверное значение foo1");
        assertEquals("bar2", foo2, "Неверное значение foo2");
    }

    @Test
    void testPatchMethod() {
        RestAssured.baseURI = "https://postman-echo.com";
        String body = "This is expected to be sent back as part of response body.";
        Response response = RestAssured.given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .body(body)
                .patch("/patch");

        assertEquals(200, response.getStatusCode(), "Неверный статус");

        String foo1 = response.jsonPath().getString("args.foo1");
        String foo2 = response.jsonPath().getString("args.foo2");
        String bodyResponse = response.jsonPath().getString("data");

        assertEquals(body, bodyResponse, "Неверное значение body");
        assertEquals("bar1", foo1, "Неверное значение foo1");
        assertEquals("bar2", foo2, "Неверное значение foo2");
    }

    @Test
    void testDeleteMethod() {
        RestAssured.baseURI = "https://postman-echo.com";
        Response response = RestAssured.given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .delete("/delete");

        assertEquals(200, response.getStatusCode(), "Неверный статус");

        String foo1 = response.jsonPath().getString("args.foo1");
        String foo2 = response.jsonPath().getString("args.foo2");

        assertEquals("bar1", foo1, "Неверное значение foo1");
        assertEquals("bar2", foo2, "Неверное значение foo2");
    }
}
