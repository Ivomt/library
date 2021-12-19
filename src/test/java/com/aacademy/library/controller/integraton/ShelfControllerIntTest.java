package com.aacademy.library.controller.integraton;

import com.aacademy.library.dto.ShelfDto;
import com.aacademy.library.model.Shelf;
import com.aacademy.library.repository.ShelfRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ShelfControllerIntTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ShelfRepository shelfRepository;

    @Test
    public void saveShelf() throws JsonProcessingException {

        ShelfDto shelfDto = ShelfDto.builder().number(5).build();
        String jSonRequest = objectMapper.writeValueAsString(shelfDto);    //това се вика, за да ни направи този рекуест toString

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .body(jSonRequest)
                .when()
                .post("http://localhost:8080/shelves")
                .then()
                .statusCode(200)
                .body("id",equalTo(1))
                .body("number",equalTo(5));
    }
    @Test
    public void updateShelf () throws Exception{
        shelfRepository.save(Shelf.builder().number(1).build());
        ShelfDto shelfDto=ShelfDto.builder().id(1L).number(5).build();
        String jsonRequest = objectMapper.writeValueAsString(shelfDto);

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .body(jsonRequest)
                .when()
                .put("http://localhost:8080/shelves/1")
                .then()
                .statusCode(200)
                .body("id",equalTo(1))
                .body("number",equalTo(5));
    }
    @Test
    public void findByNumber() throws Exception{
        shelfRepository.save(Shelf.builder().number(1).build());

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .when()
                .get("http://localhost:8080/shelves/number/1")
                .then()
                .statusCode(200)
                .body("id",equalTo(1))
                .body("number",equalTo(1));
    }
}
