package model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Donations {
	
	private Connection connect()
	 {
		Connection con = null;
		 try{
			 Class.forName("com.mysql.cj.jdbc.Driver");
	
		 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbudget?useTimezone=true&serverTimezone=UTC", "root", "");
		 }catch (Exception e){
			 e.printStackTrace();
			 }
		
		 return con;
	} 
	
	
	//insert donations
	public String insertDonations(String name, String email, String amount, String cardNumber,String CVC){
		
		 String output = "";
		 
		 output= "";
		 
		 try{
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for inserting.";
			 }
			  
				 
			 // create a prepared statement
			 String query = " insert into donations(`donationID`,`name`,`email`,`amount`,`cardNumber`,`CVC`)"+ " values (?, ?, ?, ?, ?,?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, email);
			 preparedStmt.setString(4, amount);
			 preparedStmt.setString(5, cardNumber);
			 preparedStmt.setString(6, CVC);
			// execute the statement
			
			 preparedStmt.execute();
			 con.close();
			 output = "Donation is successful";
		
		 }
		 catch (Exception e)
		 {
			 output = "Error while donating.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
	 }
	
	
	//--------------------------------------------------------------
	
	public String readDonations()
	{
		String output = "";
		try
		{
	
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			
			String sql2 = "select SUM(amount) from donations";
			Statement stamt = con.createStatement();
			ResultSet resultSet = stamt.executeQuery(sql2);
			String CountRn = null;
			
			while(resultSet.next())
			{
				CountRn = resultSet.getString(1);
			}
		
		output =  "<center><div class=\"card\" style=\"width: 18rem;\">"
					+ "<h4 style=\"text-align:center\">Total Donations</h4>"
					+ "<h1 style=\"text-align:center\">Rs :"+CountRn+"</h1>"
				+ "</div><center>"
				+ "<br><br>"
				+ "<center><table class=\"table\" style=\"width:850px\"><thead><tr><th>Donation ID</th>" +
		"<th scope=\"col\">Doner's name</th>" +
		"<th scope=\"col\">Doner's email</th>" +
		"<th scope=\"col\">Donated amount</th>" +
		"<th scope=\"col\">Transfer for funding</th></tr><thead>";
		
		String query = "select * from donations";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		// iterate through the rows in the result set
		while (rs.next())
		{
			String donationID = Integer.toString(rs.getInt("donationID"));
			String name = rs.getString("name");
			String email = rs.getString("email");
			String amount = rs.getString("amount");
			String cardNumber = rs.getString("cardNumber");
			String CVC = rs.getString("CVC");
			
			// Add into the html table
			output += "<tbody><tr><td>" + donationID + "</td>";
			output += "<td>" + name + "</td>";
			output += "<td>" + email + "</td>";
			output += "<td>" + amount + "</td>";
			
			// buttons
			output += "<td>"
						+ "<form action='../../../GadgetBadget/DonationsService/Donations/Delete' method='post'>"
						+ "<input name='btnRemove' type='submit' value='Transfer for funds' class='btn btn-success'>"
						+ "<input name='donationID' type='hidden' value='" + donationID+ "'>"
						+ "</form>"
					+ "</td></tr></tbody>";
		}
		
			con.close();
			// Complete the html table
			output += "</table><center><br><br>";
		}
		catch (Exception e)
		{
			output = "Error while loading the donation list.";
			System.err.println(e.getMessage());
		}
		
		
		return output;
		
	} 	
	
	
	
	
	//transfer for funding(delete)
	public String deleteDonation(String donationID)
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
				 String query = "delete from donations where donationID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(donationID));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Transfer for funding is successfull";
			 }
			 catch (Exception e)
			 {
				 output = "Error while transferring the donation.";
				 System.err.println(e.getMessage());
			 }
		 return output;
	 } 
		


}
