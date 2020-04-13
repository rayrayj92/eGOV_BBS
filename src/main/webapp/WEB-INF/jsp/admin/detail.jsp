<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/partial/nav.jsp" />
	<div class="container" style="margin-top: 2rem;">
		<div class="row">
			<div class="col-2">
				<ul class="list-group list-group-flush">
			      <li class="list-group-item"><a href="list.do">게시판 목록</a></li>
				  <li class="list-group-item"><a href="create.do">게시판 작성</a></li>
				  <li class="list-group-item"><a href="../logout.do">로그아웃</a></li>
				</ul>
			</div>
			<div class="col-10">
				<h3>${detail.title}</h3>
				<hr />
				<div>
					<ul>
						<li>Author: ${detail.author}</li>
						<li>Date: ${detail.regdate}</li>
						<li>View: ${detail.hit}</li>
					</ul>
				</div>
				<hr />
				<div style="height:400px; overflow:auto;">
					${detail.content}
				</div>
				<hr />
				<span style="font-size:1.2rem; font-weight: 700;">Files</span><br />
				<c:if test="${detail.filename != ''}">
					<a href="downloadFile.do?filename=${detail.filename}" download>${detail.filename}</a>
				</c:if>
				<c:if test="${detail.filename == null}">
					<span style="color:red;">No files</span>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>