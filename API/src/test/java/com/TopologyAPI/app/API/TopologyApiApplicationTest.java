package com.TopologyAPI.app.API;

import com.Components;
import com.TopolgyApi;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.HashSet;




public class TopologyApiApplicationTest {
	//Constructing Topology API object to handle requests
	TopolgyApi topologyApi= new TopolgyApi();
	//testing that readtopology function in the API responds
	@Test
	void test_readtopology_01() {
		given().get("/readtopology?File=topology.json").then().statusCode(200);

	}
	//comparing the returned topology from readtopology function with the actual JSON file
	@Test
	void test_readtopology_02() {
		given().get("/readtopology?File=topology.json").then().and().contentType(ContentType.JSON).body(matchesJsonSchemaInClasspath("topology.json"));

	}
	//testing that queryDevicesWithNetlistNode function in the API responds
	@Test(dependsOnMethods = {"test_readtopology_02"})
	void test_queryDevicesWithNetlistNode_01() {
		given().get("/queryDevicesWithNetlistNode?TopID=top1&netID=n1").then().statusCode(200);



	}
	//testing that queryDevicesWithNetlistNode function in the API responds with correct count of connected devices to node n1
	@Test(dependsOnMethods = {"test_readtopology_02"})
	void test_queryDevicesWithNetlistNode_02() {
		int actualResponseSize= new HashSet<Components> (given().get("/queryDevicesWithNetlistNode?TopID=top1&netID=n1").jsonPath().get()).size();
		int expectedResponseSize=2;
		Assert.assertEquals(actualResponseSize,expectedResponseSize);


	}
	//testing that queryTopologies function in the API responds with the actual topology read from the file
	@Test(dependsOnMethods = {"test_readtopology_02"})
	void test_queryTopologies_01() {
		given().get("/queryTopologies").then().and().contentType(ContentType.JSON).body(matchesJsonSchemaInClasspath("topology.json"));

	}
	@Test(dependsOnMethods = {"test_readtopology_02"})
	void test_queryTopologies_02() {
		given().get("/queryTopologies").then().statusCode(200);

	}
	//testing that queryDevices function in the API responds
	@Test(dependsOnMethods = {"test_readtopology_02"})
	void test_queryDevices_01() {
		given().get("queryDevices?TopologyID=top1").then().statusCode(200);

	}
	//testing that queryDevices function in the API responds with the correct devices that are in the file
	@Test(dependsOnMethods = {"test_readtopology_02"})
	void test_queryDevices_02() {
		String expectedResponse= given().get("/readtopology?File=topology.json").then().extract().path("components").toString().replace(" ","");
		String actualResponse=given().get("queryDevices?TopologyID=top1").body().print().replace("\"","");
		actualResponse=actualResponse.replace(":","=");

		Assert.assertEquals(actualResponse,expectedResponse);

	}
	//comparing the returned topology from readtopology function with the actual JSON file
	@Test
	void test_readtopology_03() {
		given().get("/readtopology?File=topology2.json").then().and().contentType(ContentType.JSON).body(matchesJsonSchemaInClasspath("topology2.json"));
	}
	//testing that writetopology function in the API responds
	@Test(dependsOnMethods = {"test_readtopology_02"})
	void test_writetopology_01() {
		given().get("/writetopology?TopID=top1").then().statusCode(200);

	}
	//testing that writetopology function writes successfully
	@Test(dependsOnMethods = {"test_readtopology_02"})
	void test_writetopology_02() {
		String response=given().get("/writetopology?TopID=top1").getBody().asString();
		Assert.assertEquals(response,"true");

	}
	//testing that deletetopology function in the API responds
	@Test(dependsOnMethods = {"test_readtopology_02"})
	void test_deletetopology_01() {
		given().get("/deleteTopologies?TopID=top1").then().statusCode(200);

	}
	//testing that deletetopology function deletes successfully
	@Test(dependsOnMethods = {"test_readtopology_02"})
	void test_deletetopology_02() {
		String response=given().get("/deleteTopologies?TopID=top1").getBody().asString();
		Assert.assertEquals(response,"true");

	}



}
