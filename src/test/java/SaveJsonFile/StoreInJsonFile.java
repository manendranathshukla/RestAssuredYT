package SaveJsonFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SerializationAndDeserialization.User;
import SerializationAndDeserialization.UserResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreInJsonFile {
	
	@DataProvider(name="userDataFromCSV")
	public Object[][] readCSVData() throws Exception{
		List<Object[]> dd= new ArrayList<>();
		try(FileReader read= new FileReader("src/test/resources/userCSVData.csv")){
			Iterable<CSVRecord> records=CSVFormat.DEFAULT.withHeader("Name","Job").parse(read);
			for(CSVRecord re:records) {
				dd.add(new Object[] {re.get("Name"),re.get("Job")});
			}
		}
		return dd.toArray(new Object[0][]);
	}
	

	@Test(dataProvider = "userDataFromCSV")
	public void createUserUsingUserPOJO(String name,String job){
		User u = new User(name,job);
		
		Response response=RestAssured.given()
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
			.response();
		
		
		String jsonString = response.asPrettyString();
		System.out.println(jsonString);
		
		// Write to a jsonfile
//		try(FileWriter fr= new FileWriter("userResponse.json")){
//			fr.write(jsonString);
//			fr.close();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
		
		
			
	}
	
	
}
