package GetExamplex;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import PostExamples.PostExamples;

public class GetMethod {
	
//	@Test
//	public void GetUsers() {
//		given().baseUri("https://reqres.in/api/users?page=2")
//		.get()
//		.then().log().all().statusCode(200)
//		.statusLine("HTTP/1.1 200 OK")
//		.body("per_page", Matchers.equalTo(6))
//		.body("data[0].email",Matchers.containsString("george.bluth@reqres.in"));
//		
//	}
	
//	@Test
	public void GetSingleUser_VerifyStatusAndEmail() {
		RequestSpecification req= RestAssured.given();	
		
		req.baseUri("https://reqres.in/api/users/320");
		
		Response res=req.get();
		
		String resString= res.asPrettyString();
		System.out.println(resString);
		
		
		// Time To validate the data
		ValidatableResponse resPonse=res.then();
		
		// Status
		resPonse.statusCode(200);
		
		// Email
		resPonse.body("data.email", Matchers.containsString("janet.weaver@reqres.in"));
		
		
	}
	
	
	// Get Booking By Query Param 
	
	@Test(priority = 4)
	public void GetBookingByQueryParameter() {
		int bookingId=RestAssured
				.given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.queryParam("firstname","Hello")
					.and()
					.queryParam("lastname", "SDET")
					.baseUri("https://restful-booker.herokuapp.com/booking")
					.log().all()
				.when()
					.get()
				.then()
					.log().all()
					.statusCode(200)
				.extract()
					.jsonPath()
					.getInt("[0].bookingid");
		
		Assert.assertEquals(PostExamples.bookingId, bookingId);
		
	}
	
	@Test(priority = 5)
	public void GetBookingByPathParam() {
		String firstname=RestAssured
				.given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("id",2)
					.baseUri("https://reqres.in/")
					.log().all()
				.when()
					.get("/api/users/{id}")
				.then()
					.log().all()
					.statusCode(200)
				.extract()
					.jsonPath()
					.getString("data.first_name");
	Assert.assertEquals(firstname, "Janet");
	
	}
	

}
