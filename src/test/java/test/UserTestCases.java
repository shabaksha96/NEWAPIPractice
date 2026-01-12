package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.UserEndpoints;
import io.restassured.response.Response;
import payload.User;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;


public class UserTestCases {
	
	Faker random;
	User payload;
	int id;
	
	public Logger logs;
	
	@BeforeClass
	public void setup () 
	{
		random = new Faker();
		payload = new User();
		payload.setId(9);
		payload.setUserName(random.name().username());
		payload.setPassword(random.internet().password(7, 11));
		
		RestAssured.config = RestAssured.config()
			    .encoderConfig(EncoderConfig.encoderConfig()
			    .appendDefaultContentCharsetToContentTypeIfUndefined(false));
		
		logs =LogManager.getLogger(this.getClass());
	}
	
	@Test (priority =1)
	public void testCreateUser () 
	{
		logs.info("********* Started Execution *********");
		Response r =UserEndpoints.createUser(payload);
		r.then().log().all();
		AssertJUnit.assertEquals(r.statusCode(), 200);
		this.id =r.jsonPath().getInt("id");
		System.out.println("Id is: "+id);
		logs.info("********* Create user completed *********");
	}
	
	@Test (priority =2)
	public void testListUser () 
	{
		logs.info("********* Started Execution *********");
		Response r =UserEndpoints.listUser();
		r.then().log().all();
		AssertJUnit.assertEquals(r.statusCode(), 200);
		logs.info("********* list user completed *********");
	}
	
	@Test (priority =3)
	public void testGetUser () 
	{
		logs.info("********* Started Execution *********");
		Response res =UserEndpoints.getUser(id);
		res.then().log().body();
		AssertJUnit.assertEquals(res.statusCode(), 200);
		logs.info("********* view new user completed *********");
	}

	@Test (priority =4)
	public void testPutUser () 
	{
		logs.info("********* Started Execution *********");
		Response res =UserEndpoints.updateUser(id=6);
		res.then().log().body();
		AssertJUnit.assertEquals(res.statusCode(), 200);
		logs.info("********* update user completed *********");
	}
	
	@Test (priority =5)
	public void testDltUser () 
	{
		logs.info("********* Started Execution *********");
		Response res =UserEndpoints.dltUser(id=6);
		res.then().log().body();
		AssertJUnit.assertEquals(res.statusCode(), 200);
		logs.info("********* delete user completed *********");
	}
}
