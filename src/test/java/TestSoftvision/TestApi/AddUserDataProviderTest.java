package TestSoftvision.TestApi;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestSoftvision.TestApi.ConstantPackage.ConstantProperty;
import TestSoftvision.TestApi.pages.DataProviderUtil;
import TestSoftvision.TestApi.pages.utilApi;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class AddUserDataProviderTest {

	@Test(dataProvider = "New Users", dataProviderClass = DataProviderUtil.class)
	public void createUserTest(String name, String job, int statusCode) {
		RestAssured.baseURI = ConstantProperty.BASE_URL_API;
		Header contentType = new Header("Content-type", "application/json; charset=UTF-8");
		Response response = null;
		try {
			response = RestAssured.given().header(contentType).body(utilApi.createUserJson(name, job))
					.post("/api/users");
			response.body().prettyPeek();
			response.then().body("name", Matchers.<String>is(name)).and().body("job", Matchers.<String>is(job));
		} catch (java.lang.AssertionError e) {
			System.err.println("Asserttation with null (name =  " + name + ") , (job = " + job);

			Assert.fail();

		}

		try {
			response.then().statusCode(statusCode);
		} catch (java.lang.AssertionError e) {
			System.err.println(
					"Expected status code: " + statusCode + " actual status code: " + response.getStatusCode());
			Assert.fail(); 
		}

	}
}
