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
                        <h1 class="h3 mb-0 text-gray-800">${category} Detail</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row justify-content-center">
                    
                    	<div class="col-lg-6 mt-5">
                    
	                    	<!-- 생성한 contents 작성 -->
	                    	<div class="card shadow mb-4">
	                            <div class="card-header py-3">
	                                <h4 class="m-0 font-weight-bold text-primary">${dto.boardTitle}</h4>
		                            <div class="d-flex justify-content-end">${dto.boardWriter} | ${dto.boardDate} | ${dto.boardHit}</div>
	                            </div>
	                            <div class="card-body" style="min-height: 300px;">
	                            	${dto.boardContents}
	                            </div>
	                            <div class="card-footer py-3 d-flex justify-content-between">
	                            	<c:if test="${category ne 'Notice'}">
		                            	<a href="./reply?boardNum=${dto.boardNum}" class="btn btn-primary">답글</a>
	                            	</c:if>
     	                            <a href="./list" class="btn btn-secondary btn-icon-split" role="button">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-list"></i>
                                        </span>
                                        <span class="text">List</span>
                                    </a>
                                    
                                    <div>
								        <a href="./update?boardNum=${dto.boardNum}" class="btn btn-primary btn-icon-split mx-1">
								            <span class="icon text-white-50">
								                <i class="fas fa-edit"></i>
								            </span>
								            <span class="text">Edit</span>
								        </a>
								        
								        <form action="./delete" method="post">
								        	<input type="hidden" name="boardNum" value="${dto.boardNum}">
								        	<button class="btn btn-danger" id="del">Delete</button>
								        </form>
								
								    </div>
	                            </div>
	                        </div>
	                        
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