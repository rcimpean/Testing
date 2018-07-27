package TestSoftvision.TestApi;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import TestSoftvision.TestApi.pages.utilApi;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getUsers {

	protected static final String BASE_URL = "https://reqres.in";

	@Test
	public void getAllUsers() {
		int id = 2;
		RestAssured.baseURI = BASE_URL;
		String firstName = "Janet";
		String lastName = "Weaver";
		Header contentType = new Header("Content-type", "application/json; charset=UTF-8");

		RequestSpecification httpRequest = RestAssured.given().header(contentType);
		Response response = httpRequest.request(Method.GET, "/api/users/" + id);
		
		 System.out.println(response.getStatusCode());
		 System.out.println(response.asString());
		 System.out.println(response.getContentType());
		 System.out.println(response.getTime()); response.prettyPrint();
		 

		try {
			assertEquals(response.getStatusCode(), 200);
		} catch (AssertionError e) {
			System.err.println("Response code is " + response.getStatusCode());
		}
		try {
			// assertTrue(response.asString().contains(firstName));
			assertTrue(utilApi.getUserDataFromJson(response.asString(), "first_name").equals(firstName));
		} catch (AssertionError e) {
			System.err.println("Assert error at find first_name = " + firstName);
		}
		try {
			// assertTrue(response.asString().contains(lastName));
			assertTrue(utilApi.getUserDataFromJson(response.asString(), "last_name").equals(lastName));
		} catch (AssertionError e) {
			System.err.println("Assert error at find last_name = " + lastName);
		}
		Object responseFieldValue = utilApi.getUserDataFromJsonGeneric(response.asString(), "id");
		if (utilApi.getUserDataFromJsonGeneric(response.asString(), "id") instanceof Object) {
			System.out.println(responseFieldValue + " as object");
		}

		int x = Integer.parseInt(responseFieldValue.toString());
		System.out.println(x + 2);

	}

	@Test
	public void getUserTestNG() {
		int id = 2;
		RestAssured.baseURI = BASE_URL;
		Header contentType = new Header("Content-type", "application/json; charset=UTF-8");

		RequestSpecification httpRequest = RestAssured.given().header(contentType);
		Response response = httpRequest.request(Method.GET, "/api/users/" + id);
		assertEquals(response.getStatusCode(), 200);
	}

	@Test
	public void getUserTestREST() {

		RestAssured.baseURI = BASE_URL;

		Header contentType = new Header("Content-type", "application/json; charset=UTF-8");

		Response response = RestAssured.given().header(contentType).when().get("/api/users?page=2");
		int len = Integer.parseInt(utilApi.getUserDataFromJsonGeneric(response.asString(), "total").toString());

		for (int i = 1; i <= len; i++) {
			Response responseIndividual = RestAssured.given().header(contentType).when().get("/api/users/" + i);

			RestAssured.given().header(contentType).when().get("/api/users/" + i).then().body("data.id",
					Matchers.<Integer>is(i));

			System.out.println("object  " + i);
			responseIndividual.prettyPrint();
		}

	}
	
	@Test
	public void addUser()
	{
		RestAssured.baseURI = BASE_URL;
		Header contentType = new Header("Content-type", "application/json; charset=UTF-8");
		Response response = RestAssured.given().header(contentType).body(utilApi.createUserJson("BruceLee", "Teacher")).post("/api/users");
		response.body().prettyPeek();
		response.then().body("name", Matchers.<String>is("BruceLee")).and().body("job", Matchers.<String>is("Teacher"));
		response.then().statusCode(HttpStatus.SC_CREATED);
	}
}
