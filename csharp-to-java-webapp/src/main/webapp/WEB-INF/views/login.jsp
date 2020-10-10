<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="webapp.Token" %>

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
			<label class="offset-1" for="codeInput">Input C# Code</label>
			<div>
				<textarea class="col-10 form-control offset-1" name="name" rows="10">test</textarea>
			</div>
			<div>
				<input class="col-10 btn btn-primary btn-lg btn-block offset-1" type="submit" value="Translate">
			</div>
			
		</form>
	</div>
	<div class="offset-1 col-10"  style='background-color:#F1F1F1;'>
	<%ArrayList data = (ArrayList)request.getAttribute("name");
	if(data != null) {
		int count = 1;
		out.println("<label for='output'>Java Translation</label>");
		out.println("<div>");
		for (int i = 0; i < data.size(); i++) { Token token = (Token)data.get(i);
		if (token.getRowNumber() == count) {
			out.println(token.getLexeme());
		}
		else {
			out.println("</div>");
			out.println("<div>");
			out.println(token.getLexeme());
			count++;
		}
	}%><%}%>
	</div>
</body>
</html>