package PutAndPatchExample;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.regex.Matcher;

import  io.restassured.response.Response;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import PostExamples.PostExamples;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
public class PutAndPatchExample {
	
	@Test(priority = 2)
	public void UpdateBookingAndVerifyData() {
		
		String jsonString = "{\r\n"
				+ "  \"firstname\": \"Subscribe\",\r\n"
				+ "  \"lastname\": \"MyCodeWorks\",\r\n"
				+ "  \"totalprice\": 111,\r\n"
				+ "  \"depositpaid\": true,\r\n"
				+ "  \"bookingdates\": {\r\n"
				+ "    \"checkin\": \"2018-01-01\",\r\n"
				+ "    \"checkout\": \"2019-01-09\"\r\n"
				+ "  },\r\n"
				+ "  \"additionalneeds\": \"OO\"\r\n"
				+ "}";

		RequestSpecification req= RestAssured.given();
		req.contentType(ContentType.JSON);
		req.header("Cookie","token="+PostExamples.authToken);
		req.body(jsonString);
		req.baseUri("https://restful-booker.herokuapp.com/booking/"+PostExamples.bookingId);
		
		// Update whole data 
		Response res= req.put();
		
		System.out.println(res.asPrettyString());
		
		ValidatableResponse vRes=res.then();
		
		vRes.statusCode(200);
		vRes.body("firstname", Matchers.equalTo("Subscribe"));
		vRes.body("lastname", Matchers.equalTo("MyCodeWorks"));
	}
	
	
	@Test(priority = 3)
	public void PartialUpdateBookingAndVerifyData() {
		
		String jsonString = "{\r\n"
				+ "    \"firstname\" : \"Hello\",\r\n"
				+ "    \"lastname\" : \"SDET\"\r\n"
				+ "}";

		RequestSpecification req= RestAssured.given();
		req.contentType(ContentType.JSON);
		req.header("Cookie","token="+PostExamples.authToken);
		req.body(jsonString);
		req.baseUri("https://restful-booker.herokuapp.com/booking/"+PostExamples.bookingId);
		
		// Update Partial data 
		Response res= req.patch();
		
		System.out.println(res.asPrettyString());
		
		ValidatableResponse vRes=res.then();
		
		vRes.statusCode(200);
		vRes.body("firstname", Matchers.equalTo("Hello"));
		vRes.body("lastname", Matchers.equalTo("SDET"));
	}
	
	@Test(priority = 4)
	public void DeleteBookingAndVerifyStatus() {
		
		RequestSpecification req= RestAssured.given();
		req.auth().preemptive().basic("admin","password123");
		req.contentType(ContentType.JSON);
		//req.header("Cookie","token="+PostExamples.authToken);
		req.baseUri("https://restful-booker.herokuapp.com/booking/"+"2664");
		
		// Delete Booking data 
		Response res= req.delete();
		
		System.out.println(res.asPrettyString());
		
		ValidatableResponse vRes=res.then();
		
		vRes.statusCode(201);
	}

	
	//@Test(priority = 5)
	public void GetBookingAndVerifyStatus() {
		
		RequestSpecification req= RestAssured.given();
		req.contentType(ContentType.JSON);
		req.header("Cookie","token="+PostExamples.authToken);
		req.baseUri("https://restful-booker.herokuapp.com/booking/"+PostExamples.bookingId);
		
		Response res= req.get();
		
		System.out.println(res.asPrettyString());
		
		ValidatableResponse vRes=res.then();
		
		vRes.statusCode(404);
	}
}
