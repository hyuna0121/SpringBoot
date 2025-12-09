<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    
    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row justify-content-center">
                    <div class="col-lg-6"> 
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Update an Account!</h1>
                            </div>
                            
                            <form:form modelAttribute="userDTO" method="post" enctype="multipart/form-data" cssClass="user">
                                <div class="form-group">
	                            	<form:input path="name" cssClass="form-control form-control-user" placeholder="Name"/>
	                            	<form:errors path="name"></form:errors>
                                </div>

                                <div class="form-group">
	                            	<form:input path="email" cssClass="form-control form-control-user" placeholder="Email Address"/>
	                            	<form:errors path="email"></form:errors>
                                </div>
                                
                                <div class="form-group">
	                            	<form:input path="phone" cssClass="form-control form-control-user" placeholder="Phone Number"/>
	                            	<form:errors path="phone"></form:errors>
                                </div>

                                <div class="form-group">
	                            	<form:input type="date" path="birth" cssClass="form-control form-control-user" placeholder="Birth Date"/>
	                            	<form:errors path="birth"></form:errors>
                                </div>
                                
                                <!-- Profile Image -->
                                <div class="form-group">
                                    <label for="profileImage" class="small text-gray-600 pl-3 mb-1">Profile Image</label>
                                    <input type="file" class="form-control form-control-user" name="profile" id="profileImage" 
                                           style="padding-top: 13px; height: 50px;">
                                </div>

                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    Update Account
                                </button>
                            </form:form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="forgot-password.html">Forgot Password?</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="login.html">Already have an account? Login!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
</body>
</html>