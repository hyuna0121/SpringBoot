<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	
	<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
</head>
<body id="page-top">

	<div id="wrapper">
	
		<!-- side bar -->
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<!-- side bar -->
		
		<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
        
        	<!-- Main Content -->
            <div id="content">
            
            	<!-- Topbar -->
            	<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
            	<!-- End of Topbar -->
            	
            	<!-- Begin Page Content -->
                <div class="container-fluid">
                
                	<!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Product</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row justify-content-center">
                    
                    	<!-- 생성한 contents 작성 -->
                    	<form class="col-sm-8">
	                    	<div class="input-group mb-3">
							    <select class="form-control" name="kind">
							      <option value="k1">Title</option>
							      <option value="k2">Category</option>
							      <option value="k3">Sale</option>
							    </select>
	
							  <input type="text" class="form-control" name="search" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2">
							  
							  <div class="input-group-append">
							    <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Button</button>
							  </div>
							  
							</div>
                    	</form>
                    	
                    	<table class="table col-sm-8 table-hover mt-5">
						    <thead class="thead-dark">
						        <tr>
						            <th scope="col">Num</th>
						            <th scope="col">Name</th>
						            <th scope="col">Category</th>
						            <th scope="col">Rate</th>
						            <th scope="col">Sale</th>
						        </tr>
						    </thead>
						  	<tbody>
							    <c:forEach items="${productList}" var="product">
							        <tr>
							            <th scope="row">${product.productNum}</th>
							            <td><a href="./detail?productNum=${product.productNum}">${product.productName}</a></td>
							            <td>${product.productCategory}</td>
							            <td>${product.productRate}</td>
							            <td>${product.productSale}</td>
							         </tr>
							    </c:forEach>
						    </tbody>
						  </table>
						  
                    </div>
                    <div class="row col-sm-8 offset-sm-2 justify-content-between">
					  <nav aria-label="Page navigation example">
						  <ul class="pagination">
						    <li class="page-item">
						      <a class="page-link" href="./list?page=${pager.begin - 1}&kind=${pager.kind}&search=${param.search}" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <c:forEach begin="${pager.begin}" end="${pager.end}" var="i">
							    <li class="page-item"><a class="page-link" href="./list?page=${i}&kind=${pager.kind}&search=${param.search}">${i}</a></li>
					  		</c:forEach>
					  		<li>
						      <a class="page-link" href="./list?page=${pager.end + 1}&kind=${pager.kind}&search=${param.search}" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						  </ul>
						</nav> 
						
						<div>
							<a href="./add" class="btn btn-info" role="button">글쓰기</a>
						</div>
                    </div>
                    
                </div>
                <!-- /.container-fluid -->
                
            </div>
            <!-- End of Main Content -->
            
            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->
            
        </div>
        
	</div>
	
	<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>
</body>
</html>