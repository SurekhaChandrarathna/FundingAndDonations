//hide status messages
$(document).ready(function()
{
 $("#alertSuccess").hide();
 $("#alertError").hide();
});


function validateDonationForm()
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
	var status = validateDonationForm();
	// If not valid-------------------
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	 } 
	
	 var type = ($("#hidDonationIDSave").val() == "") ? "POST" : "PUT";
	 $.ajax(
	 {
		 url : "DonationsAPI",
		 type : type,
		 data : $("#formDonation").serialize(),
		 dataType : "text",
		 complete : function(response, status)
			 {
				 onDonationSaveComplete(response.responseText, status);
			 }
	 }); 

});

function onDonationSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divDonationGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	
	}
	
	$("#hidDonationIDSave").val("");
	$("#formDonation")[0].reset();
}


//Delete==========================================
$(document).on("click", ".btnRemove", function(event)
		{
		$.ajax(
		{
			url : "DonationsAPI",
			type : "DELETE",
			data : "donationID=" + $(this).data("donationID"),
			dataType : "text",
			complete : function(response, status)
		{
				onDonationDeleteComplete(response.responseText, status);
		}
		});
	});



function onDonationDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divDonationGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
		} else if (status == "error")
		{
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}



