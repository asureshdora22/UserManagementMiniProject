<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
 table {   
         border: 5px solid #1b1c42;
        height: 155px;
        background-color: lightblue;  
        font-family: Arial, Helvetica, sans-serif;
 
    }   
   
    
          #d1left
            {
                
                width: 36%;
                height: 200px;
                float: left;
            }
            #d1right
            {
                /* background-color: skyblue; */
                
                width: 64%;
                height: 300px;
                float: left;
                
            }
   .d1{
        padding: 5px;   
        background-color: #53e349;  
   }
     body {
			background-color: #2edbd8;
		}
		span{
		font-size: 0.875em;
		color: red;
		}
</style>   

	<script
src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery
.min.js"></script>
<script>
$(document).ready(function() {
	$("#userEmail").blur(function() {
		$("#errMsg").text("");
			$.ajax({
				type : "GET",
				url : "uniqueMail?email="+$("#userEmail").val(),
				success : function(data) {
					console.log(data);
					if(data=="DUPLICATE"){
						$("#errMsg").text("Duplicate Email");
						$("#submitBtn").prop("disabled", true);
						}else{
						$("#submitBtn").prop("disabled", false);
						}
						}
						});
			
						});
	$( "#countryId" ).change(function() {
		$('#stateId').find('option:not(:first)').remove();
		$('#cityId').find('option:not(:first)').remove();
		$.ajax({
			type : "GET",
			url : "countryChange?countryId="+$("#countryId").val(),
			success : function(data) {
				 $.each(data, function(stateId, stateName) {
			            $('#stateId').append($('<option>').text(stateName).attr('value', stateId));
			        });
			}
		});
	});
	$( "#stateId" ).change(function() {
		$('#cityId').find('option:not(:first)').remove();
		$.ajax({
			type : "GET",
			url : "changeState?stateId="+$("#stateId").val(),
			success : function(data) {
				 $.each(data, function(cityId, cityName) {
			            $('#cityId').append($('<option>').text(cityName).attr('value', cityId));
			        });
			}
		});
	});
});
						
			</script>
</head>
<body>
<div class="d1">
	<font color="#a83298"><h1 align="center">User Management Application</h1></font> 
</div>
<div><h1></h1></div>
<div id="d1left"></div>





	<div id="d1right">
	<b><font color="blue">${successMsg}</font></b>
    <b><font color="red">${failMsg}</font></b>
	<form:form action="userAccount" method="post" modelAttribute="userAcc">
	<h2><font color="#3c32a8">User Registration</font><table>
	<tr>
		<td>First Name:</td>
		<td><form:input path="firstName" placeholder="First Name"/> </td>
	</tr>
	<tr>
		<td>Last Name:</td>
		<td><form:input path="lastName" placeholder="Last Name"/></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><form:input path="userEmail" placeholder="Email Id"   id="userEmail"/>
		<span id="errMsg" ></span></td>
	</tr>
	<tr>
		<td>Phone Number:</td>
		<td><form:input path="userMobile" placeholder="Mobile Number"/></td>
	</tr>
	<tr>
		<td>DOB:</td>
		<td><input type="date" value=""/></td>
	</tr>
	<tr>
		<td>Gender:</td>
		<td>
		<form:radiobutton path="gender" value="M"/>Male
		<form:radiobutton path="gender" value="F"/>Fe-Male
		
		</td>
	</tr>
	
	<tr>
		<td>Country:</td>
		<td><form:select path="countryId" id="countryId">
		<form:option value="">-Select-</form:option>
		<form:options items="${listOfCountry}" />
		
		</form:select> </td>
	</tr>
	<tr>
		<td>State:</td>
		<td><form:select path="stateId" id="stateId">
		<form:option value="">-Select-</form:option>
		
		</form:select> </td>
	</tr>
	<tr>
		<td>Cities:</td>
		<td><form:select path="cityId" id="cityId">
		<form:option value="">-Select-</form:option>
		
		</form:select> </td>
	</tr>
	<tr>
		<td><input type="reset" value="Reset"/> </td>
		<td><input type="submit" value="Submit" id="submitBtn"/></td>
	</tr>
	
	
	</table></h2>
	
	
	</form:form></div>
</body>
</html>