<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<div class="col-8">
				<div class="jumbotron">
				  <h1 class="display-4">안녕하세요 관리자님!</h1>
				  <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
				  <hr class="my-4">
				  <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
				  <p class="lead">
				    <a class="btn btn-primary btn-lg" href="#" role="button">더 보기</a>
				  </p>
				</div>
			</div>
			<div class="col-2">
			</div>
		</div>
	</div>
</body>
</html>