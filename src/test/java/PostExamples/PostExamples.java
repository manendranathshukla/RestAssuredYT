package PostExamples;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.regex.Matcher;

import  io.restassured.response.Response;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PostExamples {
	public static String authToken;
	public static int bookingId;
	
	@Test
	public void GenerateAndVerifyAuthToken() {
		
		String jsonString="{\"username\" : \"admin\",\"password\" : \"password123\"}";
		
	RequestSpecification req= RestAssured.given();
	req.contentType(ContentType.JSON);
	req.body(jsonString);
	req.baseUri("https://restful-booker.herokuapp.com/auth");
	Response res= req.post();
	
	System.out.println(res.asPrettyString());
	authToken=res.jsonPath().get("token");
	
	// Now Validate the token
	// length - 15
	// Mix Of Char and Number 
	//Not Null
	
	ValidatableResponse vRes=res.then();
	
	vRes.statusCode(200);
	vRes.body("token", Matchers.notNullValue());
	
	vRes.body("token.length()", Matchers.is(15));
	vRes.body("token", Matchers.matchesRegex("^[a-z0-9]+$"));
	
	
	

		
	}

	@Test(priority = 1)
	public void CreateBookingVerifyStatus() {
		String jsonString = "{\r\n"
				+ "  \"firstname\": \"Hello\",\r\n"
				+ "  \"lastname\": \"Brown\",\r\n"
				+ "  \"totalprice\": 111,\r\n"
				+ "  \"depositpaid\": true,\r\n"
				+ "  \"bookingdates\": {\r\n"
				+ "    \"checkin\": \"2018-01-01\",\r\n"
				+ "    \"checkout\": \"2019-01-01\"\r\n"
				+ "  },\r\n"
				+ "  \"additionalneeds\": \"UUUUUU\"\r\n"
				+ "}";

		RequestSpecification req= RestAssured.given();
		req.contentType(ContentType.JSON);
		req.body(jsonString);
		req.baseUri("https://restful-booker.herokuapp.com/booking");
		Response res= req.post();
		
		System.out.println(res.asPrettyString());
		bookingId=res.jsonPath().getInt("bookingid");
		
		ValidatableResponse vRes=res.then();
		
		vRes.statusCode(200);
		vRes.body("booking.firstname", Matchers.equalTo("Hello"));

	}
	
	
	public void testAuthentication() {
//		RestAssured.given()
//				.auth()
//				.preemptive()
//				.basic("", authToken)
	}
		
		
	
}
