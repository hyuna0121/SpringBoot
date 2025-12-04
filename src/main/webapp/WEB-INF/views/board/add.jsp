<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	
	<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
	
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.css" rel="stylesheet">
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
                        <h1 class="h3 mb-0 text-gray-800">${category} ${sub}</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row justify-content-center">
                    
                    	<!-- 생성한 contents 작성 -->
						<div class="card shadow mb-5">
							<div class="card-header  py-3">
                    	        <h4 class="m-0 font-weight-bold text-primary">${category} ${sub}</h4>
	                        </div>
	                        <div class="card-body">
<!-- Form ---------------------------------------------------------->
								<form:form modelAttribute="dto" method="post" enctype="multipart/form-data">
								  <form:hidden path="boardNum"/>
								  
								  <div class="form-group">
								    <label for="title">Title</label>
								    <form:input path="boardTitle" cssClass="form-control" id="title"/>
								    <form:errors path="boardTitle"></form:errors>
								  </div>
								  
								  <div class="form-group">
								    <label for="writer">Writer</label>
								    <form:input path="boardWriter" cssClass="form-control" id="writer"/>
								  </div>
								  
								  <div class="form-group">
								    <label for="contents">Contents</label>
								    <form:textarea path="boardContents" cssClass="form-control" id="contents"/>
								  </div>
								  
								  <div class="form-group">
								  	<button type="button" class="btn btn-info" id="fileAdd">File Add</button>	
								  </div>
								  
								  <div id="files">
								  	
								  </div>
								  
								  <div class="mt-5 d-flex justify-content-end">
								  	<a href="./list" class="btn btn-secondary mr-2" role="button">Cancel</a>
					                <button type="submit" class="btn btn-info">${sub}</button>
					              </div>
								</form:form>
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
	
	<script src="/js/board/board.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.js"></script>
	<script>
      $('#contents').summernote({ /* jQuery : 호출하는 요소.메서드명 */
        tabsize: 2,
        height: 300
      });
  </script>
</body>
</html>