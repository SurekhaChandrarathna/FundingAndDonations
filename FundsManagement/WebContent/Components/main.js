//hide status messages
$(document).ready(function()
{
 $("#alertSuccess").hide();
 $("#alertError").hide();
 $("#alertSuccess2").hide();
 $("#alertError2").hide();
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

//--------------------------------------------------------------------------
function validateRequestForm()
{
	
	if ($("#BankCardNumber").val().trim() == "")
	 {
		return "Please insert BC number.";
	 }
	
	
	return true;
}


$(document).on("click", "#submit", function(event)
{
	// Clear status messages-------------
	 $("#alertSuccess2").text("");
	 $("#alertSuccess2").hide();
	 $("#alertError2").text("");
	 $("#alertError2").hide();
	 
	// Form validation----------------
	var status = validateRequestForm();
	// If not valid-------------------
	if (status != true)
	 {
		 $("#alertError2").text(status);
		 $("#alertError2").show();
		 return;
	 } 
	
	 var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	 $.ajax(
	 {
		 url : "FundsAPI",
		 type : type,
		 data : $("#FormRequestForFund").serialize(),
		 dataType : "text",
		 complete : function(response, status)
			 {
			 	onRequestSaveComplete(response.responseText, status);
			 }
	 }); 

});

function onRequestSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess2").text("Successfully saved.");
			$("#alertSuccess2").show();
			
			$("#divItems1Grid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError2").text(resultSet.data);
			$("#alertError2").show();
		}
		} else if (status == "error")
		{
			$("#alertError2").text("Error while saving.");
			$("#alertError2").show();
	} else
	{
		$("#alertError2").text("Unknown error while saving..");
		$("#alertError2").show();
	
	}
	
	$("#hidItemIDSave").val("");
	$("#FormRequestForFund")[0].reset();
}



