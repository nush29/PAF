package com;

import model.BillManagement;

import javax.websocket.server.PathParam;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


//For JSON
import com.google.gson.*;


//For XML
import org.jsoup.*;	
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/bill")

public class Bill_management {
	
	BillManagement userObj = new BillManagement();
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	// insert payment details.
	
	public String insertUser(@FormParam("billNo") String billNo,
	@FormParam("accNo") String accNo,
	@FormParam("amount") String amount,
	@FormParam("description") String description
	) {
	String output = userObj.insertBill(billNo, accNo, amount,description);
	return output;
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBillDetails() //view all payment details
	{
		return userObj.readBillDetails();
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String userData) //update payment
	{
		//Convert the input string to a JSON object
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		//Read the values from the JSON object
		 String id = userObject.get("id").getAsString();
		 String billNo = userObject.get("billNo").getAsString();
		 String accNo = userObject.get("accNo").getAsString();
		 String amount = userObject.get("amount").getAsString();
		 String description = userObject.get("description").getAsString();
		 String output = userObj.EditBillDetails(id, billNo, accNo, amount,description);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)//delete users
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <userId>
	String userID = doc.select("id").text();
	String output = userObj.deleteBill(userID);
	return output;
	}
	@GET
	@Path("/getUserbyID/{userId}")//view a specific payment
	@Produces(MediaType.TEXT_HTML)
	public String UserProfileDetails(@PathParam("userId") String userId) {

		return userObj.fetchBill(userId);
	}

}
