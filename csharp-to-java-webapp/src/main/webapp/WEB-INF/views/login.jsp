<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="/login.do" method="post">
			<b>Test input</b>
			<input type="text" name="name">
			<input type="submit" value="Translate">
		</form>
	</div>
	<div>
		<p></p>
		${name}
	</div>
</body>
</html>