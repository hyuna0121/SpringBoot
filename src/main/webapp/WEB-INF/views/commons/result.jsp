<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		alert('${msg}'); // jsp 파일안에서는 인식하지만, 별도의 js 파일로 분리할 경우 인식하지 못함
		window.location.href = "${path}"; // window는 최상위 객체이기 때문에 생략 가능
	</script>
</body>
</html>