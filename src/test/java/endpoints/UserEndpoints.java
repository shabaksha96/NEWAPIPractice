package endpoints;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import payload.User;

public class UserEndpoints {
	
	public static Response createUser(User input) 
	{
		Response res = given()
		.contentType("application/json; v=1.0").accept("*/*").body(input)
		
		.when()
		.post(Routes.postUserURL);
		
		return res;
	}

	public static Response listUser() 
	{
		Response res = given()
		.accept("text/plain; v=1.0")
		
		.when()
		.get(Routes.getUserURL);
		
		return res;
	}
	
	public static Response getUser(int id) 
	{
		Response res = given()
		.accept("text/plain; v=1.0").pathParam("ID", id)
		
		.when()
		.get(Routes.getUserID);
		
		return res;
	}
	
	public static Response updateUser(int id) 
	{
		Response res = given()
		.contentType("application/json; v=1.0").accept("*/*").body("{" +
		          "  \"id\": 22," +
		          "  \"userName\": \"rakkadc\"," +
		          "  \"password\": \"oksxbjqkb\"" +
		          "}").pathParam("ID", id)
		
		.when()
		.put(Routes.putUserURL);
		
		return res;
	}
	
	public static Response dltUser(int id) 
	{
		Response res = given()
		.accept("*/*").pathParam("ID", id)
		
		.when()
		.get(Routes.dltUserURL);
		
		return res;
	}
	
}
