package TestSoftvision.TestApi;

import java.util.Base64;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import TestSoftvision.TestApi.ConstantPackage.ConstantProperty;
import TestSoftvision.TestApi.pages.DataProviderUtil;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicAutentichationTest {
	
	@Test
	public void noAutentichation()
	{
		RestAssured.baseURI = ConstantProperty.BASE_URL_API_AUTENTICHATION;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get();
		response.prettyPeek();
		response.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
		
	}
	
	@Test(dataProvider = "Autentichation", dataProviderClass = DataProviderUtil.class)
	public void baseAutentichation(String username, String password)
	{
		String ussernamePassword = username+":"+password;
		byte[] encodedBytesUsername = Base64.getEncoder().encode(ussernamePassword.getBytes());
		String encodedUsername = new String(encodedBytesUsername);

		RestAssured.baseURI = ConstantProperty.BASE_URL_API_AUTENTICHATION;
		Header authenticate = new Header("Authorization", "Basic "+encodedUsername);
		RequestSpecification httpRequest = RestAssured.given().header(authenticate);
		Response response = httpRequest.get();
		response.prettyPeek();
		if (!username.equals("") && !password.equals("")) {
			response.then().statusCode(HttpStatus.SC_OK);
		}else
			response.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
		
		
	}
}
