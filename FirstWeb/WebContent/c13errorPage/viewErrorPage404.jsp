<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%> <!--에러처리 페이지에서만 사용  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<style></style>
</head>
<body>

	<h1>
		404 에러: 요청하신 페이지를 찾을 수 없습니다. <br> 주소를 올바르게 입력해주세요. 
	</h1>

	<a href="<%=request.getContextPath()%>/index.html">홈으로 가기</a>

</body>
</html>
<!--
만약 에러 페이지의 길이가 513 바이트보다 작다면,인터넷 익스플로러는 이 페이지가 출력하는 에러 페이지
를 출력하지 않고 자체적으로 제공하는 'HTTP 오류 메시지' 화면을 출력할 것이다. 만약 에러 페이지의 길이
가 513 바이트보다 작은데 에러 페이지의 내용이 인터넷 익스플로러에서도 올바르게 출력되길 원한다면, 응
답 결과에 이 주석과 같은 내용을 포함시켜서 에러 페이지의 길이가 513 바이트 이상이 되도록 해 주어야 한
다. 참고로 이 주석은 456바이트이다.
-->