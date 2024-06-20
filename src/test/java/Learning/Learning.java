package Learning;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.io.FileWriter;
import java.util.regex.Matcher;

import  io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PostExamples.PostExamples;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
public class Learning {

	@Test
	public void testQueryParam() {
		given()
		.queryParam("firstname", "MyCodWorks")
		.and().queryParam("lastname", "Subscibe")
		.log().all()
		.when()
			.get("https://restful-booker.herokuapp.com/booking")
		.then()
			.statusCode(200)
			.log().all()
			.body("body.size()", equalTo(1));
	}
	
	@DataProvider(name="userTestData")
	public Object[][] userTestData(){
		return new Object[][] {
			{"abc","engineer"},
			{"Manendra","Engineer"},
			{"Shubham","Youtuber"}
		};
	}
	
	
	@Test
	public void testPathParam() {
		given()
		.pathParam("id",3814)
		.log().all()
		.cookie("Token","170a8c33d9fd3b9")
		.when()
			.get("https://restful-booker.herokuapp.com/booking/{id}")
		.then()
			.statusCode(200)
			.log().all()
			.body("body.size()", equalTo(1))
			.body("body.firstname",equalTo("MyCodWorks"));
	}
	
	@Test(dataProvider = "userTestData")
	public void testSerialization(String name,String job) {
		User u = new User(name,job);
		Response r=RestAssured.given()	
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(u)
			.baseUri("https://reqres.in")
			
		.when()
			.post("/api/users")
		.then()
			.log().all()
			.statusCode(201)
			.extract()
			.response();
		String resString=r.asPrettyString();
	System.out.print(resString);
//		try(FileWriter f = new FileWriter("response.json")) {
//			f.write(resString);
//			System.out.println("Successfully copied JSON object to file");
//            System.out.println("\nJSON Object: " + resString);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
	}
}
