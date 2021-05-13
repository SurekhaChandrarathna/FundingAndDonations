//hide status messages
$(document).ready(function()
{
 $("#alertSuccess").hide();
 $("#alertError").hide();
});


function validateItemForm()
{
	
	if ($("#name").val().trim() == "")
	 {
		return "Please insert your name.";
	 }
	
	if ($("#email").val().trim() == "")
	 {
		return "Please insert your email.";
	 }
	
	if ($("#amount").val().trim() == "")
	 {
		return "Please insert donating amount";
	 }
	
	if ($("#cardNumber").val().trim() == "")
	 {
		return "Please insert your card number.";
	 }
	
	if ($("#CVC").val().trim() == "")
	 {
		return "Please insert CVC";
	 }
	
	return true;
}


$(document).on("click", "#btnSave", function(event)
{
	// Clear status messages-------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();
	 
	// Form validation----------------
	var status = validateItemForm();
	// If not valid-------------------
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	 } 

});



