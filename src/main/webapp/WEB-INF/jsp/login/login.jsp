<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/partial/nav.jsp" />
	<div class="container" style="margin-top: 2rem;">
		<div class="row">
			<div class="col-2">
			</div>
			<div class="col-8">
				<h4>전자정부프레임워크 - 로그인</h4>
				<hr />
				<form action="tryLogin.do" method="post">
					<div class="form-group">
						<label for="email">이메일</label><br>
						<input type="email" name="email" class="form-control" placeholder="이메일을 입력하세요" required>
					</div>
					<div class="form-group">
						<label for="password">비밀번호</label><br>
						<input type="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요" min="6">
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary btn-block" value="로그인">
					</div>
				</form>
			</div>
			<div class="col-2">
			</div>
		</div>
	</div>
</body>
</html>