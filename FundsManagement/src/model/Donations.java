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
		
		output = "<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"\r\n" + 
				"	<link rel=\"stylesheet\" href=\"../Home.css\">\r\n" + 
				"	<!-- bootstrap -->\r\n" + 
				"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"		\r\n" + 
				"	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" \r\n" + 
				"			integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\r\n" + 
				"	\r\n" + 
				"	<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">	\r\n" + 
				"		\r\n" + 
				"		\r\n" + 
				"	<!-- bootstrap -->\r\n" + 
				"<title>GadgetBadget</title>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"<!-- navigation bar -->\r\n" + 
				"\r\n" + 
				"  <nav  class=\"navbar fixed-top navbar-white bg-white\">\r\n" + 
				"		<div class= \"container\">\r\n" + 
				"			<a class=\"navbar-brand\" href=\"#\">\r\n" + 
				"     			 <img src=\"../../../GadgetBadget/images/Capture.PNG\" alt=\"logo\" width=\"220\" height=\"78\" float=\"left\">\r\n" + 
				"   			</a>\r\n" + 
				"   			<br>\r\n" + 
				"   			<div class=\"topnav\" id=\"myTopnav\">\r\n" + 
				"			  <a href=\"../../../GadgetBadget/HomesService/Homes/AdminHome\" >Home</a>\r\n" + 
				"			  <a href=\"\">Products</a>\r\n" + 
				"			  <a href=\"../../../GadgetBadget/DonationsService/Donations\" class=\"active\">Donations</a>\r\n" + 
				"			  <a href=\"../../../GadgetBadget/FundsAdminService/FundsAdmin\">Funding HelpDesk</a>\r\n" + 
				"			  <a href=\"javascript:void(0);\" class=\"icon\" onclick=\"myFunction()\">\r\n" + 
				"			    <i class=\"fa fa-bars\"></i>\r\n" + 
				"			  </a>\r\n" + 
				"			\r\n" + 
				"			</div>\r\n" + 
				"			\r\n" + 
				"			<div class=\"dropdown\">\r\n" + 
				"			   <img src=\"../images/avatar.png\" class=\"img-fluid\" alt=\"avatar1\" width=\"50\" height=\"80\" >\r\n" + 
				"			  <div class=\"dropdown-content\">\r\n" + 
				"			    <a href=\"#\">Profile</a>\r\n" + 
				"			    <a href=\"\">Log Out</a>\r\n" + 
				"			  </div>\r\n" + 
				"			</div>			\r\n" + 
				"		</div>	\r\n" + 
				"	</nav>\r\n" + 
				"	\r\n" + 
				"<!-- navigation bar -->\r\n" + 
				"	<br>\r\n" + 
				"  <img class=\"card-img-top\" src=\"../images/adminpage.jpg\" alt=\"Card image cap\"  height=\"500px\">\r\n" + 
				"  <br><br><br>"
				+ "<center><div class=\"card\" style=\"width: 18rem;\">"
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
			output += "</table><center><br><br>"
					+ "<footer class=\"page-footer font-small color-dark\" style=\"background-color:#1f3a93\">\r\n" + 
					"\r\n" + 
					"  <div style=\"background-color: #59abe3;\">\r\n" + 
					"    <div class=\"container\">\r\n" + 
					"\r\n" + 
					"      <!-- Grid row-->\r\n" + 
					"      <div class=\"row py-4 d-flex align-items-center\">\r\n" + 
					"\r\n" + 
					"        <!-- Grid column -->\r\n" + 
					"        <div class=\"col-md-6 col-lg-5 text-center text-md-left mb-4 mb-md-0\">\r\n" + 
					"          <h6 class=\"mb-0\" style=\"color:white\">Get connected with us on social networks!</h6>\r\n" + 
					"        </div>\r\n" + 
					"        <!-- Grid column -->\r\n" + 
					"\r\n" + 
					"        <!-- Grid column -->\r\n" + 
					"        <div class=\"col-md-6 col-lg-7 text-center text-md-right\">\r\n" + 
					"\r\n" + 
					"          <!-- Facebook -->\r\n" + 
					"          <a class=\"fa fa-facebook\"></a>\r\n" + 
					"          <!-- Twitter -->\r\n" + 
					"          <a class=\"fa fa-twitter \"></a>\r\n" + 
					"          <!-- Google +-->\r\n" + 
					"          <a class=\"fa fa-google-plus-g\"></a>\r\n" + 
					"          <!--Linkedin -->\r\n" + 
					"          <a class=\"fa fa-linkedin\"></a>\r\n" + 
					"          <!--Instagram-->\r\n" + 
					"          <a class=\"fa fa-instagram \"> </a>\r\n" + 
					"\r\n" + 
					"        </div>\r\n" + 
					"        <!-- Grid column -->\r\n" + 
					"\r\n" + 
					"      </div>\r\n" + 
					"      <!-- Grid row-->\r\n" + 
					"\r\n" + 
					"    </div>\r\n" + 
					"  </div>\r\n" + 
					"\r\n" + 
					"  <!-- Footer Links -->\r\n" + 
					"  <div class=\"container text-center text-md-left mt-5\">\r\n" + 
					"\r\n" + 
					"    <!-- Grid row -->\r\n" + 
					"    <div class=\"row mt-3\">\r\n" + 
					"\r\n" + 
					"      <!-- Grid column -->\r\n" + 
					"      <div class=\"col-md-3 col-lg-4 col-xl-3 mx-auto mb-4\">\r\n" + 
					"\r\n" + 
					"        <!-- Content -->\r\n" + 
					"        <h6 class=\"text-uppercase font-weight-bold\" >GadgetBadget Company</h6>\r\n" + 
					"        <hr class=\"deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto\" style=\"width: 60px;\">\r\n" + 
					"        <p style=\"color:white\">Best funding company for young researchers....</p>\r\n" + 
					"\r\n" + 
					"      </div>\r\n" + 
					"      <!-- Grid column -->\r\n" + 
					"\r\n" + 
					"      <!-- Grid column -->\r\n" + 
					"      <div class=\"col-md-2 col-lg-2 col-xl-2 mx-auto mb-4\">\r\n" + 
					"\r\n" + 
					"        <!-- Links -->\r\n" + 
					"        <h6 class=\"text-uppercase font-weight-bold\">Products</h6>\r\n" + 
					"        <hr class=\"deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto\" style=\"width: 60px;\">\r\n" + 
					"        <p>\r\n" + 
					"          <a href=\"#!\" style=\"color:white\">pen Drives</a>\r\n" + 
					"        </p>\r\n" + 
					"        <p>\r\n" + 
					"          <a href=\"#!\" style=\"color:white\">Hard Disks</a>\r\n" + 
					"        </p>\r\n" + 
					"        <p>\r\n" + 
					"          <a href=\"#!\" style=\"color:white\">T shirts</a>\r\n" + 
					"        </p>\r\n" + 
					"        <p>\r\n" + 
					"          <a href=\"#!\" style=\"color:white\">Cables</a>\r\n" + 
					"        </p>\r\n" + 
					"\r\n" + 
					"      </div>\r\n" + 
					"      <!-- Grid column -->\r\n" + 
					"\r\n" + 
					"      <!-- Grid column -->\r\n" + 
					"      <div class=\"col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4\">\r\n" + 
					"\r\n" + 
					"        <!-- Links -->\r\n" + 
					"        <h6 class=\"text-uppercase font-weight-bold\">Contact</h6>\r\n" + 
					"        <hr class=\"deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto\" style=\"width: 60px;\">\r\n" + 
					"        <p style=\"color:white\">\r\n" + 
					"          <i class=\"fa fa-home mr-3\"></i> Colombo, SriLanka</p>\r\n" + 
					"        <p style=\"color:white\">\r\n" + 
					"          <i class=\"fa fa-envelope mr-3\" style=\"color:white\"></i> info@example.com</p>\r\n" + 
					"        <p style=\"color:white\">\r\n" + 
					"          <i class=\"fa fa-phone mr-3\" style=\"color:white\"></i> + 01 234 567 88</p>\r\n" + 
					"        <p style=\"color:white\">\r\n" + 
					"          <i class=\"fa fa-print mr-3\" style=\"color:white\"></i> + 01 234 567 89</p>\r\n" + 
					"\r\n" + 
					"      </div>\r\n" + 
					"      <!-- Grid column -->\r\n" + 
					"\r\n" + 
					"    </div>\r\n" + 
					"    <!-- Grid row -->\r\n" + 
					"\r\n" + 
					"  </div>\r\n" + 
					"  <!-- Footer Links -->\r\n" + 
					"\r\n" + 
					"  <!-- Copyright -->\r\n" + 
					"  <div class=\"footer-copyright text-center py-3\" style=\"color:white\">© 2020 Copyright:\r\n" + 
					"    <a href=\"https://mdbootstrap.com/\" style=\"color:white\"> GadgetBadget Company</a>\r\n" + 
					"  </div>\r\n" + 
					"  <!-- Copyright -->\r\n" + 
					"\r\n" + 
					"</footer>\r\n" + 
					"<!-- Footer -->\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"<script>\r\n" + 
					"function myFunction() {\r\n" + 
					"  var x = document.getElementById(\"myTopnav\");\r\n" + 
					"  if (x.className === \"topnav\") {\r\n" + 
					"    x.className += \" responsive\";\r\n" + 
					"  } else {\r\n" + 
					"    x.className = \"topnav\";\r\n" + 
					"  }\r\n" + 
					"}\r\n" + 
					"</script>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"\r\n" + 
					" <!-- bootstrap -->\r\n" + 
					"	<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\r\n" + 
					"	<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\r\n" + 
					"	<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>\r\n" + 
					"<!-- bootstrap -->\r\n" + 
					"\r\n" + 
					"</body>\r\n" + 
					"</html>";
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
