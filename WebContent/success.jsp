<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Success Page</title>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<meta http-equiv="x-ua-compatible" content="ie=edge">
    	<link rel="stylesheet" href="css/login_styles.css">
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light" rel="stylesheet">
	</head>
	<body>
		<!-- JSP Code -->
			<%
				String userName = null;
				Cookie[] cookies = request.getCookies();
				
				if(cookies != null)
				{
					for(Cookie cookie : cookies)
					{
						if(cookie.getName().equals("user")) userName = cookie.getValue();
					}
				}
				
				if(userName == null) 
					response.sendRedirect("login.jsp");
			%>
			
			<div class="container">
				<div class="jumbotron">
					<h1>Hi!!! <%=userName %>, You Have Been Registered Successfully.</h1>
					<a class="btn btn-primary" href="login.jsp">Go to Login Page</a>
				</div>
			</div>
	</body>
</html>