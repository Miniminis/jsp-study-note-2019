<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" language="javascript">

 /*  $(document).ready(function(){
    	
        $.ajax({
            type : "POST", //전송방식 지정
            url : "writeActivity.jsp",//호출 URL을 설정한다. 
            dataType : "html",//호출한 페이지의 형식
            error : function(){
                alert("글작성에 실패하였습니다. \n 제목과 내용을 다시 확인하여 주세요");
            },
            success : function(successInsert){
                $("#successInsert").html(successInsert); //div에 받아온 값을 넣는다.
                alert("통신 데이터 값 : " + successInsert);
            }
             
        });
    }); */
  </script>
<%				


	session = request.getSession(false);
	LoginInfo dongjun = (LoginInfo) session.getAttribute("userInfo");
				



%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DATE SHARE</title>
<link href="../css/index.css" rel="stylesheet" type="text/css">
<style></style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">
		<div id="main_wrap">
			<div id="header">
				<%@include file="../frame/my.jsp"%>
				<%@include file="../frame/header.jsp"%>
			</div>
			<div id="nav">
				<%@include file="../frame/nav.jsp"%>
			</div>
			<div id="content">
				<h3>글쓰기</h3>
					<form action="writeActivity.jsp" method="post" enctype="multipart/form-data">

					<table>
						
						<tr>
							<td>제목</td>
							<td>
								<input type="text" name="a_title">
							</td>
						</tr>
						<tr>
							<td>사진</td>
							<td>
								<input type="file" name="a_path">
							</td>
						</tr>
						<tr>
							<td>내용</td>
							<td>
								<textarea rows="5" cols="30" name="a_content"></textarea>
							</td>
						</tr>
						<tr>
							<td>별점</td>
							<td>
								<input type="range" name="a_star" max="10" step="1">
							</td>
						</tr>
						<!-- 할수있다면 지도 -->
						<tr>
							<td colspan="2">
								<input type="hidden" name="u_num" value="<%= dongjun.getU_num() %>"><!-- 유저번호 가져오기 -->
								<input type="submit" value="등록">
							</td>
						</tr>
					</table>
				</form>

				<hr>
			</div>
			<div id="footer">
				<%@include file="../frame/footer.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>