<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="//cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
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
				<h4>게시판 작성</h4>
				<hr />
				<form action="create.do" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="title">제목</label><br>
						<input type="text" name="title" class="form-control" placeholder="제목을 입력하세요" required>
					</div>
					<div class="form-group">
						<label>작성자: ${ADMIN}</label>
					</div>
					<div class="form-group">
						<label for="content">내용</label><br>
						<textarea name="content" id="content" class="form-control" placeholder="내용을 입력하세요"></textarea>
					</div>
					<div class="form-group">
						<label for="file" class="control-lable">File - 첨부파일</label>
						<input type="file" name="file" class="form-control" />
					</div>
					<div class="form-group">
						<input type="submit" value="게시물 생성" class="btn btn-info" />
						<a ></a>
					</div>
				</form>
			</div>
			<div class="col-2">
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
 	CKEDITOR.replace( 'content' );
</script>
</html>