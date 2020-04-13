<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script type="text/javascript">
	function movePage(pageNo){
		location.href ='<%=request.getContextPath() %>/admin/list.do?pageNo='+pageNo;
	}
</script>
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
				<h4>게시판 목록</h4>
				<form action="delete.do" method="post">
					<table class="table table-striped">
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col" style="width:5%;">Check</th>
					      <th scope="col" style="width:5%;">Index</th>
					      <th scope="col" style="width:45%;">Title</th>
					      <th scope="col" style="width:10%;">Author</th>
					      <th scope="col" style="width:20%;">Date</th>
					      <th scope="col" style="width:5%;">View</th>
					      <th scope="col" style="width:10%;">Action</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="list" items="${board}" varStatus="status">
					  	<tr>
					  	  <th><input type="checkbox" name="del-id" value="${list.ID}"></th>
					      <th scope="row">${status.index + 1}</th>
					      <td>
					      	<a href="detail.do?id=${list.ID}">${list["TITLE"]} </a>
					      </td>
					      <td>${list["AUTHOR"]}</td>
					      <td>
					      	<fmt:formatDate pattern="yy년 MM월 dd일" value="${list.REGDATE}"/>
					      </td>
					      <td>
					      	<fmt:formatNumber value="${list.HIT}"/>
					      </td>
					      <td>
					      	<a href="edit.do?id=${list.ID}">Edit</a>
					      </td>
						  </tr>
						  </c:forEach>
						  <tr>
						  	<td colspan="7" class="text-center">
						  		<ui:pagination paginationInfo = "${paginationInfo}" type="text" jsFunction="movePage" />
						  	</td>
						  </tr>
					  </tbody>
					</table>
					<input type="submit" class="btn btn-danger" name="delete" value="일괄삭제">
				</form>
			</div>
		</div>
	</div>
</body>
</html>