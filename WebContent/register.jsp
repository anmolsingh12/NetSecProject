<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registration Form</title>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<meta http-equiv="x-ua-compatible" content="ie=edge">
    	<link rel="stylesheet" href="css/login_styles.css">
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light" rel="stylesheet">
	</head>

	<body>
	<!-- Register Navbar -->
	<nav class="navbar navbar-dark navbar-expand-sm navbar-toggleable-xs fixed-top bg-light">
		<h2 style="font-family: 'Shadows Into Light', cursive; font-size:56px;" id="msg">Already Registered?</h2><hr>
		<a id="login-button" class="btn btn-info login-button" href="login.jsp">Login Here</a>
  	</nav>
  	<!-- Register Navbar -->
  	<!-- Register Container -->
		<div class="container" style="margin-top:75px;">
			<div class="row">
		    	<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
		        	<div class="card card-signin my-5">
		          		<div class="card-body">
		            		<h5 class="card-title text-center">Register</h5>
		            		<hr class="my-5">
		            		<form class="form-signin" action="Register" method="post" >
			                    <div class="form-label-group">
			                        <input type="text" id="userId" name = "userId" class="form-control" placeholder="--User Id" autofocus required>
		                			<label for="userId">User Id</label>
			                    </div>
			                
			                    <div class="form-label-group">
			                        <input type="text" id="username" name = "username" class="form-control" placeholder="--User Name" required autofocus>
		                			<label for="username">UserName</label>
			                    </div>
			                    
			                    <div class="form-label-group">
			                        <input type="password" id="password" name = "password" class="form-control" placeholder="--Password" required autofocus>
		                			<label for="password">Password</label>		                        
			                    </div>
								  
								<div class="form-label-group">
									<p>Permitted Role</p>
                        			<div class="radio">
	                        			<label><input type = "radio" id="role" name = "role" value="admin">&nbsp;Admin</label>
										<label><input type = "radio" id="role" name = "role" value="user">&nbsp;User</label>
									</div>	
								</div>
																
								<button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Add User</button>
							</form>
						</div>
					</div>
				</div>
        	</div>
		</div>
	<!-- Register Container -->	
		<!-- JavaScript Code and Libraries -->
    	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.4/js/tether.min.js" crossorigin="anonymous"></script>
    <!-- JavaScript Code and Libraries-->	   
	</body>
</html>