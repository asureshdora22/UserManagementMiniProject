<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign-in</title>
<style type="text/css">
 table {   
        border: 5px solid #f1f1f1;
        height: 155px;
        background-color: lightblue; 
       
         
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
        background-color: lightgreen;  
   }
     body {
			background-color: aqua;
		}
		 /* Add a blue text color to links */
a {
  color: dodgerblue;
}
</style>   
</head>
<body>
<div class="d1">
	<font color="#a83298"><h1 align="center">User Management Application</h1></font> 
</div>
<div><h1></h1></div>
<div id="d1left"></div>



	<div id="d1right">
	<b><font color="red">${failMsg}</font></b>
	<form action="handleLoginBtn" method="post" ><h1>
	<font color="#3c32a8">Sign-In</font>
		<table >
		
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" placeholder="Email Id"/></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" placeholder="Password"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Sign-In"/></td>
			</tr>
			<tr>
				<td><a href="/forgotPwdForm">Forgot Password?</a></td>
				<td> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="/loadRegForm">Sign-Up</a></td>
			</tr>
		</table></h1>
	</form>
	
	</div>
</body>
</html>