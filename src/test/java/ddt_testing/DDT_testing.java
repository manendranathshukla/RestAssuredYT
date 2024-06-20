package ddt_testing;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SerializationAndDeserialization.User;
import SerializationAndDeserialization.UserResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DDT_testing {
	
	
	@DataProvider(name="userDataSet")
	public Object[][] userData(){
		return new Object[][] {
			{"John","Teacher"},
			{"Rucy","Painter"},
			{"Gohjn","Engineer"},
			{"Gopal","manager"}
		};
		
	}
	
	@DataProvider(name="userCSVdataset")
	public Object[][] userCSVDataset() throws IOException{
		List<Object[]> userData= new ArrayList<>();
		
		try(FileReader reader = new FileReader("src/test/resources/userCSVData.csv")){
			 Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader("Name","Job").parse(reader);
			 for(CSVRecord r : records) {
				 userData.add(new Object[] {r.get("Name"),r.get("Job")});
			 }
		}
		
		return userData.toArray(new Object[0][]);
		
	}
	

	
	@Test(dataProvider = "userCSVdataset")
	public void createUserUsingDDT(String name,String job){
		User u = new User(name,job);
		
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
