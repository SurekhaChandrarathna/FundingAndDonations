package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Blob;


public class FundsAdmin {
	
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
	
	//-----------------------READ FUND REQUESTS--------------------------------------------------------------//
	
	public String readFundRequests()
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
		output = 
				 "<form>"
				+ "<input name='btnView' type='submit' class='btn btn-info' value='View Accepted Funds'><br><br>"
				+ "</form>"
				+ "<center><table class=\"table\" style='width:1300px'><thead class=\"thead-dark\" style='width:600'>"
				+ "<tr>"
					+ "<th scope=\"col\">Request ID</th>"
					+ "	<th scope=\"col\">Project ID</th>"
					+ "<th scope=\"col\">Project Name</th>" 
					+"<th scope=\"col\">Description</th>" 
					+"<th scope=\"col\">Budget</th>" 
					+"<th scope=\"col\">User Email</th>" 
					+"<th scope=\"col\">Bank Card</th>" 
					+"<th scope=\"col\"></th>"
					+ "<th scope=\"col\"></th></tr>"
					+ "</thead>";
		
		String query = "select P.ProjectName, P.ProjectID, P.Description,P.Budget, P.UserEmail,F.RequestID,F.BankCardNumber"
				+ " from fundrequests F, projects P "
				+ "where F.ProjectID=P.ProjectID ";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		// iterate through the rows in the result set
		while (rs.next())
		{
			String RequestID = Integer.toString(rs.getInt("RequestID"));
			String ProjectID = Integer.toString(rs.getInt("ProjectID"));
			String ProjectName = rs.getString("ProjectName");
			String Description = rs.getString("Description");
			String Budget = rs.getString("Budget");
			String UserEmail = rs.getString("UserEmail");
			String BankCardNumber= rs.getString("BankCardNumber");
			// Add into the html table
			output += "<tbody>"
					+ "<tr>"
					+ "<td>" + RequestID + "</td>"
					+ "<td>" + ProjectID + "</td>";
			output += "<td>" + ProjectName + "</td>";
			output += "<td>" + Description + "</td>";
			output += "<td>" + Budget + "</td>";
			output += "<td>" + UserEmail + "</td>";
			output += "<td>" + BankCardNumber + "</td>";
			
			// buttons
			output += "<td><form >"
					+ "<input name='btnAccept' type='submit' value='Accept Request' class='btn btn-success'>"
					+ "<input name='RequestID' type='hidden' value='" + RequestID+ "'>"
							+ "<input name='ProjectID' type='text' value='"+ProjectID+"' hidden>"
							+ "<input name='UserEmail' type='text' value='"+UserEmail+"' hidden><br><br>"
							+ "<input type='hidden' class='form-control'  name='Budget' value='"+Budget+"'  readonly>"
							
							+ "<label>Funding amount :</label><br>"
							+ "<input type='text' name='amount' class='form-control' placeholder='Enter funding amount' required>"
					+ "</form></td>"
					+ ""
			+ "<td><form>"
					+ "<input name='btnRemove' type='submit' value='Reject' class='btn btn-danger'>"
					+ "<input name='RequestID' type='hidden' value='" + RequestID+ "'>"
					+ "<input name='ProjectID' type='hidden' value='"+ProjectID+"'>" 
				+ "</form>"
			+ "</td></tr>"
			+ "</tbody>";
		}
		
			con.close();
			// Complete the html table
			output += "</table></center>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		
		return output;
		
	} 
	
	
	
	//-------------------------DELETE FUND REQUESTS---------------------------------------------------------//
	public String deleteFundRequests(String RequestID, int ProjectID)
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
				 String query = "delete from fundrequests where RequestID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(RequestID));
				 // execute the statement
				 preparedStmt.execute();
				 
				 
				 String query1 = "UPDATE projects SET status=3 WHERE ProjectId = '"+ProjectID+"'";
				 PreparedStatement preparedStmt1 = con.prepareStatement(query1);
				 
				 preparedStmt1.execute();
				 
				 
				 con.close();
				 output = "Rejected";
				 
			 }
			 catch (Exception e)
			 {
				 output = "Error while transferring the donation.";
				 System.err.println(e.getMessage());
			 }
		 return output;
	 } 
	
	
	
 //--------------------------------ACCEPT FUND REQUESTS -INSERT TO ACCEPTED------------------------------//
	public String insertAcceptedFunds(String UserEmail, int ProjectID, String amount,String RequestID){
		
		 String output = "";
		 
		 
		 
		 try{
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for inserting.";
			 }
			  
			
			 // create a prepared statement
			 String query = " insert into accepted(`FundID`,`UserEmail`,`ProjectID`,`amount`)"+ " values (?, ?, ?, ?)";
			
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, UserEmail);
			 preparedStmt.setInt(3, ProjectID);
			 preparedStmt.setString(4, amount);
			
			// execute the statement
			
			 preparedStmt.execute();
			 
			 deleteFundRequests(RequestID,ProjectID);
			 
			 String query1 = "UPDATE projects SET status=2 WHERE ProjectId = '"+ProjectID+"'";
			 PreparedStatement preparedStmt1 = con.prepareStatement(query1);
			 
			 preparedStmt1.execute();
			 
			 con.close();
			 output = "Funding is successful";
		 }
		 catch (Exception e)
		 {
			 output = "Already accepted the fund.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
	 }
	
	
	//----------------------------------------------VIEW ACCEPTED FUND REQUESTS---------------------------------------//
	public String readAcceptedFunds()
	{
		String output = "";
		try
		{
	
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
		
		output = "<center><table class=\"table\" style='width:850px'><thead><tr><th>Fund ID</th>" +
		"<th scope=\"col\">Project Name</th>" +
		"<th scope=\"col\">Reseacher Email</th>" +
		"<th scope=\"col\">Funded amount</th>" +
		"<th scope=\"col\">Description</th></tr><thead>";
		
		String query = "select A.FundID,A.amount,A.UserEmail,P.ProjectName,P.Description from accepted A, projects P where A.ProjectID=P.ProjectId ";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		// iterate through the rows in the result set
		while (rs.next())
		{
			String FundID = Integer.toString(rs.getInt("FundID"));
			String ProjectName = rs.getString("ProjectName");
			String UserEmail = rs.getString("UserEmail");
			String amount = rs.getString("amount");
			String Description = rs.getString("Description");
			
			
			// Add into the html table
			output += "<tbody><tr><td>" + FundID + "</td>";
			output += "<td>" + ProjectName + "</td>";
			output += "<td>" + UserEmail + "</td>";
			output += "<td>" + amount + "</td>";
			output += "<td>" + Description + "</td>";
			
			// buttons
			output += "<td>"
						+ "<form action='../../../GadgetBadget///' method='post'>"
						+ "<input name='btnUpdate' type='submit' value='Update' class='btn btn-info'>"
						+ "<input name='FundID' type='hidden' value='" + FundID+ "'>"
						+ "</form>"
					+ "</td></tr></tbody>";
		}
		
			con.close();
			// Complete the html table
			output += "</table><center>";
		}
		catch (Exception e)
		{
			output = "Error while loading the accepted request list.";
			System.err.println(e.getMessage());
		}
		
		
		return output;
		
	} 	
	
	
	
	//----------------------------------------------UPDATE FUND AMOUNT---------------------------------------//
	public String updateFund(String FundID,String UserEmail,String ProjectID, String amount)
	{
		String output = "";
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE accepted SET UserEmail=?,ProjectID=?,amount=? WHERE FundID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 
			 preparedStmt.setString(1, UserEmail);
			 preparedStmt.setInt(2,Integer.parseInt(ProjectID));
			 preparedStmt.setString(3, amount);
			 preparedStmt.setInt(4,Integer.parseInt(FundID));
			
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	
	
	
	//---------------------------------------------------USER --------------------------------------------------------------------//
	
		
		
	//------------------------------------READ PROJECTS OF SPECIFIC USER--------------------------------------------------------------------------------//
			public String readMyProjects()
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
				output = "  <center><h5> Welcome to GadgetBadget </h5></center>\r\n" + 
						" <br>\r\n" + 
						"<center><h6 style='color:blue'>projects you have submitted will be appeared here</h6>"
						+ 
						"<br>" + 
						"<table class=\"table\" style='width:1300px' >" + 
						"  <thead class=\"thead-dark\">\r\n" + 
						"    <tr>\r\n" + 
						"      <th scope=\"col\">id</th>\r\n" + 
						"      <th scope=\"col\">Project Code</th>\r\n" + 
						"      <th scope=\"col\">Project Name</th>\r\n" + 
						"      <th style='width:300px'>Description</th>\r\n" + 
						"      <th scope=\"col\">Budget</th>\r\n" + 
						"      <th scope=\"col\">Category</th>\r\n" + 
						"      <th scope=\"col\">User Email</th>\r\n" + 
						"      <th scope=\"col\">Status</th>\r\n" + 
						"      <th scope=\"col\"></th>\r\n" + 
						"      <th scope=\"col\"></th>\r\n" + 
						"    </tr></thead>";
				
				String query = "select * from projects where UserEmail like 'surekha@GB.com'";//------ hard coded---
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
			
				// iterate through the rows in the result set
				while (rs.next())
				{
					String ProjectID = Integer.toString(rs.getInt("ProjectID"));
					String ProjectCode = rs.getString("ProjectCode");
					String ProjectName = rs.getString("ProjectName");
					Blob Image = rs.getBlob("Image");
					String Description = rs.getString("Description");
					String Budget = rs.getString("Budget");
					String Category = rs.getString("Category");
					String UserEmail = rs.getString("UserEmail");
					int status = rs.getInt("status");
					
					// Add into the html table
					output += "<tbody>\r\n" + 
							"    <tr>"
							+ "<td>" + ProjectID + "</td>";
					output += "<td>" + ProjectCode + "</td>";
					output += "<td>" + ProjectName + "</td>";
					output += "<td>" + Description + "</td>";
					output += "<td>" + Budget + "</td>";
					output += "<td>" + Category + "</td>";
					output += "<td>" + UserEmail + "</td>";
					
					if(status==0) {
						
						output +=  "<td>"+
								"        <form name='FormRequestForFund' id='FormRequestForFund'>" + 
								"		    <label>Bank Card Number</label>" + 
								"			  <input type='hidden' name='UserEmail' value='"+UserEmail+"' >\r\n" + 
								"				<input type='hidden' name='ProjectID' value='"+ProjectID+"' >\r\n" + 
								"		      <input type='number' class='form-control' placeholder='Enter card number' name='BankCardNumber' id='BankCardNumber' required>"
								+ "<div id='alertSuccess2' class='alert alert-success'></div>\r\n" + 
								" <div id='alertError2' class='alert alert-danger'></div>"
								+ "<input type=\"hidden\" id=\"hidItemIDSave\"\r\n" + 
								" name=\"hidItemIDSave\" value=\"\">" +  
								"		  <input type='submit' name='submit' value='Send request' id='submit' class='btn btn-primary'>" + 
								"		</form>\r\n" + "</td>";
								
					}else if(status == 1) {
						
						output +=  "<td><div class='badge badge-warning'> Requested for funds </div></td>";
								
					}else if(status == 2) {
						output +=  "<td><div class='badge badge-success'> Request Accepted </div></td>";
					}else if(status == 3) {
						output +=  "<td><div class='badge badge-danger'> Request Rejected </div></td>";
					}
					
					output +=  "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-primary'></td>"
							+ "<td><input name='btnDelete' type='button' value='Delete' class='btn btn-danger'></td>";
					
					// buttons
					
				}
				
					con.close();
					// Complete the html table
					output += "</tr></table>";
				}
				catch (Exception e)
				{
					output = "Error while reading the items.";
					System.err.println(e.getMessage());
				}
				
				
				return output;
				
			} 	
			

	//-------------------------SEND REQUEST FOR FUND-------------------------------------------------------------------------------//	
			
			public String insertFundRequests(String UserEmail, String ProjectID, String BankCardNumber){
				
				 String output = "";
				 
				 
				 
				 try{
					 Connection con = connect();
					 if (con == null)
					 {
						 return "Error while connecting to the database for inserting.";
					 }
					  
						 
					 // create a prepared statement
					 String query = " insert into fundrequests(`RequestID`,`UserEmail`,`ProjectID`,`BankCardNumber`)"+ " values (?, ?, ?,?)";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, UserEmail);
					 preparedStmt.setInt(3, Integer.parseInt(ProjectID));
					 preparedStmt.setString(4, BankCardNumber);
					 
					// execute the statement
					
					 preparedStmt.execute();
					 
					// create a prepared statement
					 String query1 = "UPDATE projects SET status=1 WHERE ProjectId = '"+ProjectID+"'";
					 PreparedStatement preparedStmt1 = con.prepareStatement(query1);
					
					 preparedStmt1.execute();
					 
					 con.close();
					 String newItems = readMyProjects();
					 output = "{\"status\":\"success\", \"data\": \"" +
					 newItems + "\"}"; 
				 }
				 catch (Exception e)
				 {
					 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
							 System.err.println(e.getMessage());
				 }
				 
				 return output;
			 }
			
	
	
//==========================================================================================================================================//
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
					 String query = " insert into donations(`donationID`,`name`,`email`,`amount`,`cardNumber`,`CVC`)"
					 + " values (?, ?, ?, ?, ?,?)";
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
					 //output = "Donation is successful";
					 
					 String newDonations = readDonations();
					 output = "{\"status\":\"success\", \"data\": \"" +
							 newDonations + "\"}";
				
				 }
				 catch (Exception e)
				 {
					 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
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
								+ "<input name='btnRemove' type='button' value='Transfer' class='btnRemove btn btn-success' data-donationID='" + donationID + "'>"
								+ "<input name='donationID' type='hidden' value='" + donationID+ "'>"
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
						 String newDonations = readDonations();
						 output = "{\"status\":\"success\", \"data\": \"" +
								 newDonations + "\"}";
						 }
						 catch (Exception e)
						 {
							 output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
							 System.err.println(e.getMessage());
						 } 
				 return output;
			 } 
				


}
