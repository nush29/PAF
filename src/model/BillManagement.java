package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BillManagement {
	
	 //A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/power", "root", "nushara29");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	public String insertBill(String billNo, String accNo, String amount, String description)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 // create a prepared statement
	 String query = " insert into bill(`id`,`billNo`,`accNo`,`amount`,`description`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, billNo);
	 preparedStmt.setString(3, accNo);
	 preparedStmt.setString(4, amount);
	 preparedStmt.setString(5, description);
	 // execute the statement
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Bill successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the bill.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String readBillDetails()
	
	{
		 String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading.";
		 }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>billNo</th>"
		 +"<th>accNo</th><th>amount</th>"
		 + "<th>description</th></tr>";
		 String query = "select * from bill";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String id = Integer.toString(rs.getInt("id"));
			 String billNo = rs.getString("billNo");
			 String accNo = rs.getString("accNo");
			 String amount = rs.getString("amount");
			 String description = rs.getString("description");
			
			 
			 // Add a row into the html table
			 output += "<tr><td>" + billNo + "</td>";
			 output += "<td>" + accNo + "</td>";
			 output += "<td>" + amount + "</td>";
			 output += "<td>" + description + "</td>"; 

			 
			 // buttons
			 output += "<input id='bill_id' type='hidden' "
			 + " value='" + id + "'>"
			 + "</form></td></tr>";
		 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
		 }
		catch (Exception e)
		 {
			 output = "Error while reading the bill details"
			 		+ ".";
			 System.err.println(e.getMessage());
		 }
		return output;
	}
	
	public String EditBillDetails(String id,String billNo,String accNo,String amount, String description)
	   {
		   String output = "";
		   try
			   {
			   Connection con = connect();
			   if (con == null)
			   {
				   return "Error while connecting to the database for updating"; 
			   }
			   
			   // create a prepared statement
			   String query = "UPDATE bill SET billNo=?,accNo=?,amount=?,description=?WHERE id=?";
			   PreparedStatement preparedStmt = con.prepareStatement(query);
			   // binding values
			   preparedStmt.setString(1, billNo);
			   preparedStmt.setString(2, accNo);
			   preparedStmt.setString(3, amount);
			   preparedStmt.setString(4, description);
			   preparedStmt.setInt(5, Integer.parseInt(id));
			   // execute the statement
			   preparedStmt.execute();
			   con.close();
			   output = "Updated successfully";
			   }
		    catch (Exception e)
			{
			   output = "Error while updating the bill";
			   System.err.println(e.getMessage());
			}
		    return output;
		    }
	
	public String deleteBill(String id)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for deleting.";
	}

	// create a prepared statement
	String query = "delete from bill where id=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);

	// binding values
	preparedStmt.setInt(1, Integer.parseInt(id));

	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully";
	}
	catch (Exception e)
	{
	output = e.toString();
	//System.err.println(e.getMessage());
	}
	return output;
	}
public String fetchBill(String id)
	
	{
		 String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading";
		 }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>billNo</th>"
		 +"<th>accNo</th><th>amount</th>"
		 + "<th>description</th></tr>";
		 String query = "select * from bill where id='"+id+"'";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 { 
			 String Id = Integer.toString(rs.getInt("id"));
			 String billNo = rs.getString("billNo");
			 String accNo = rs.getString("accNo");
			 String amount = rs.getString("amount");
			 String description = rs.getString("description");
			 // Add a row into the html table
			 
			 output += "<tr><td>" + billNo + "</td>";
			 output += "<td>" + accNo + "</td>";
			 output += "<td>" + amount + "</td>"; 
			 output += "<td>" + description + "</td></tr>";
			 // buttons
			 output += "<input name='itemID' type='hidden' "
			 + " value='" + id + "'>"
			 + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 
		 }
		catch (Exception e)
		 {
//			 output = "Error while reading the Payment details";
			output=e.toString();
			 System.err.println(e.getMessage());
		 }
		return output;
	}

}
