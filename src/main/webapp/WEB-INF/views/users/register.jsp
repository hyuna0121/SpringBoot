<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            
                            <form class="user" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="username"
                                        placeholder="ID" required>
                                </div>
                                
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-user" name="password"
                                        placeholder="Password" required>
                                </div>
                                
                                <!-- Repeat Password -->
<!--                                 <div class="form-group">
                                    <input type="password" class="form-control form-control-user" id="repeatPassword"
                                        placeholder="Repeat Password">
                                </div> -->
                                
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="name"
                                        placeholder="Name" required>
                                </div>

                                <div class="form-group">
                                    <input type="email" class="form-control form-control-user" name="email"
                                        placeholder="Email Address" required>
                                </div>
                                
                                <div class="form-group">
                                    <input type="tel" class="form-control form-control-user" name="phone"
                                        placeholder="Phone Number">
                                </div>

                                <div class="form-group">
                                    <input type="date" class="form-control form-control-user" name="birth"
                                            placeholder="Birth Date">
                                </div>
                                
                                <!-- Profile Image -->
                                <div class="form-group">
                                    <label for="profileImage" class="small text-gray-600 pl-3 mb-1">Profile Image</label>
                                    <input type="file" class="form-control form-control-user" name="profile" id="profileImage" 
                                           style="padding-top: 13px; height: 50px;">
                                </div>

                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    Register Account
                                </button>
                            </form>
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