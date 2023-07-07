package resources;

import data.Contact;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class ContactResourceTest {
    @Test
    public void getDataTest() {
        final String url = "/contacts";

        Response response = given()
                .when().get(url);

        List<Contact> contacts = response.getBody().as(List.class);
        assertThat(contacts.size(), is(4));
    }

    @Test
    public void getContactTest() {
        final String url = "/contacts/retrieve/3";

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when().get(url)
                .then()
                    .statusCode(200)
                    .body("name", is("Martín"))
                    .body("surname", is("Belmonte"))
                    .body("nif", is("3"));
    }

    @Test
    public void createContactTest() {
        final String url = "/contacts/create";

        Contact contact = new Contact("Name", "Suranme", "5");

        given()
                .when()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(contact)
                    .post(url)
                .then()
                    .statusCode(201);
    }

    @Test
    public void updateContactTest() {
        final String url = "/contacts/update";

        Contact contact = new Contact("Oscar", "Belmonte", "1");

        given()
                .when()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(contact)
                    .put(url)
                .then()
                    .statusCode(204);

    }

    @Test
    public void deleteContactTest() {
        final String url = "/contacts/delete/2";

        given()
                .when()
                    .delete(url)
                .then()
                .statusCode(204);
    }
}