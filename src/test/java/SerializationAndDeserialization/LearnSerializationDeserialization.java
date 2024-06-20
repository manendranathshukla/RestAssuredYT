package SerializationAndDeserialization;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class LearnSerializationDeserialization {
	
	@Test
	public void createUserUsingUserPOJO(){
		User u = new User("Ram","Leader");
		
		UserResponse uResp=RestAssured.given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.baseUri("https://reqres.in/")
			.body(u)
		.when()
			.post("/api/users")
		.then()
//			.log().all()
			.statusCode(201)
			.extract()
			.as(UserResponse.class);
		
		// Lets Print POJO Class Object
		
		System.out.println(uResp.toString());
	}

}
