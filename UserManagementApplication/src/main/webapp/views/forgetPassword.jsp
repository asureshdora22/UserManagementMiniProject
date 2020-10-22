<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forget Password</title>
<style type="text/css">
 table {   
        border: 5px solid #f1f1f1;
        height: 155px;
        width:300px;
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
	<b><font color="blue">${successMsg}</font></b>
	<b><font color="red">${failMsg}</font></b>
	<form action="forgotPwdForm" method="post" ><h2>
	<font color="#3c32a8">Forget Password</font>
		<table >
		
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" placeholder="Email Id"/></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"/></td>
			</tr>
			
		</table></h2>
	</form>
	
	</div>
</body>
</html>