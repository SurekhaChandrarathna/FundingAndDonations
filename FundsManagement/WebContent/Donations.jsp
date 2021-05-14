<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.Donations"%>
<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" href="../../../FundsManagement/Views/Home.css">
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">		
	
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/main.js"></script>
	
<title>GadgetBadget</title>
</head>
<body>

<!-- navigation bar -->

  <nav  class="navbar fixed-top navbar-white bg-white">
		<div class= "container">
			<a class="navbar-brand" href="#">
     			 <img src="images/Capture.PNG" alt="logo" width="220" height="78" float="left">
   			</a>
   			<br>
   			<div class="topnav" id="myTopnav">
			  <a href="#" >Home</a>
			  <a href="#">Products</a>
			  <a href="../../../FundsManagement/MyProjects.jsp" >My Projects</a>
			  <a href="#" class="active">Funding HelpDesk</a>
			  <a href="javascript:void(0);" class="icon" onclick="myFunction()">
			    <i class="fa fa-bars"></i>
			  </a>
			
			</div>
			
      
			<div class="dropdown">
			   <img src="images/avatar.png" class="img-fluid" alt="avatar1" width="50" height="80" >
			  <div class="dropdown-content">
			    <a href="#">Profile</a>
			    <a href="#">Log Out</a>
			  </div>
			</div>			
		</div>	
	</nav>
	
<!-- navigation bar -->
	<br>
  <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="images/cable.jpg" alt="First slide" height="500px">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="images/hard.jpg" alt="Second slide" height="500px">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="images/pen.jpg" alt="Third slide" height="500px">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
  <br><br><br>
  
  
  <center><h3> Welcome to GadgetBadget </h3>
  		<h2>Hope to donate and help young researchers ??</h2>
  	
     <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#exampleModalCenter">
  		Donate->>
	</button></center>
	
	<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Fill Your details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="formDonation" name="formDonation">
		  <div class="form-group row">
		    <label  class="col-sm-2 col-form-label">Name</label>
		    <div class="col-sm-10">
		     
		      <input type="text" class="form-control"  placeholder="Enter your Name" name="name" id="name">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control"  placeholder="Enter your Email" name="email" id="email">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label class="col-sm-2 col-form-label">Amount</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control"  placeholder="Enter donating amount" name="amount" id="amount" >
		    </div>
		  </div>
		  <div class="form-group row">
		    <label  class="col-sm-2 col-form-label">Card Number</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control"  placeholder="Enter your card number" name="cardNumber" id="cardNumber">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label  class="col-sm-2 col-form-label">CVC</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control"  placeholder="Enter your CVC" name="CVC" id="CVC">
		    </div>
		  </div>
		 
		  
		  <div id="alertSuccess" class="alert alert-success"></div>
 		  <div id="alertError" class="alert alert-danger"></div>
		  
		  <input type="button" id="btnSave" value="Donate" class="btn btn-primary">
		   <input type="hidden" id="hidDonationIDSave" name="hidDonationIDSave" value="">
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
      </div>
    </div>
  </div>  
</div>
 <br><br>



	


<!-- Footer -->
<footer class="page-footer font-small color-dark" style="background-color:#1f3a93">

  <div style="background-color: #59abe3;">
    <div class="container">

      <!-- Grid row-->
      <div class="row py-4 d-flex align-items-center">

        <!-- Grid column -->
        <div class="col-md-6 col-lg-5 text-center text-md-left mb-4 mb-md-0">
          <h6 class="mb-0" style="color:white">Get connected with us on social networks!</h6>
        </div>
        <!-- Grid column -->

        <!-- Grid column -->
        <div class="col-md-6 col-lg-7 text-center text-md-right">

          <!-- Facebook -->
          <a class="fa fa-facebook"></a>
          <!-- Twitter -->
          <a class="fa fa-twitter "></a>
          <!-- Google +-->
          <a class="fa fa-google-plus-g"></a>
          <!--Linkedin -->
          <a class="fa fa-linkedin"></a>
          <!--Instagram-->
          <a class="fa fa-instagram "> </a>

        </div>
        <!-- Grid column -->

      </div>
      <!-- Grid row-->

    </div>
  </div>

  <!-- Footer Links -->
  <div class="container text-center text-md-left mt-5">

    <!-- Grid row -->
    <div class="row mt-3">

      <!-- Grid column -->
      <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">

        <!-- Content -->
        <h6 class="text-uppercase font-weight-bold" >GadgetBadget Company</h6>
        <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
        <p style="color:white">Best funding company for young researchers....</p>

      </div>
      <!-- Grid column -->

      <!-- Grid column -->
      <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">

        <!-- Links -->
        <h6 class="text-uppercase font-weight-bold">Products</h6>
        <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
        <p>
          <a href="#!" style="color:white">pen Drives</a>
        </p>
        <p>
          <a href="#!" style="color:white">Hard Disks</a>
        </p>
        <p>
          <a href="#!" style="color:white">T shirts</a>
        </p>
        <p>
          <a href="#!" style="color:white">Cables</a>
        </p>

      </div>
      <!-- Grid column -->

      <!-- Grid column -->
      <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">

        <!-- Links -->
        <h6 class="text-uppercase font-weight-bold">Contact</h6>
        <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
        <p style="color:white">
          <i class="fa fa-home mr-3"></i> Colombo, SriLanka</p>
        <p style="color:white">
          <i class="fa fa-envelope mr-3" style="color:white"></i> info@example.com</p>
        <p style="color:white">
          <i class="fa fa-phone mr-3" style="color:white"></i> + 01 234 567 88</p>
        <p style="color:white">
          <i class="fa fa-print mr-3" style="color:white"></i> + 01 234 567 89</p>

      </div>
      <!-- Grid column -->

    </div>
    <!-- Grid row -->

  </div>
  <!-- Footer Links -->

  <!-- Copyright -->
  <div class="footer-copyright text-center py-3" style="color:white">© 2020 Copyright:
    <a href="https://mdbootstrap.com/" style="color:white"> GadgetBadget Company</a>
  </div>
  <!-- Copyright -->

</footer>
<!-- Footer -->



<script>
function myFunction() {
  var x = document.getElementById("myTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}
</script>



 <!-- bootstrap -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>



</body>
</html>