<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<title>Insert title here</title>
</head>
<body>
	<div>
		<form class="form-group" action="/login.do" method="post">
			<label for="codeInput">Input C# Code</label>
			<div>
				<textarea id="codeInput" rows='10' name="name"></textarea>
			</div>
			<div>
				<input class="btn btn-primary" type="submit" value="Translate">
			</div>
			
		</form>
	</div>
	<div>
		${name}
	</div>
</body>
</html>