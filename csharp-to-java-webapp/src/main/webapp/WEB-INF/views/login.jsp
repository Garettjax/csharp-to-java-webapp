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
<style type="text/css" media="screen">
    #codeEditor { 
        top: 0;
        height: 500px;
        width: 500px;
    }
</style>
<script type="text/javascript">
	//var initial = 'namespace Loops\n\t{\n\t\tinternal class Program\n{\npublic static void Main(string[] args)\n{\nint test1 == 1;\nwhile (test1 <= 4)\n{\nConsole.WriteLine("Test 1 Passed (prints 4 times)");\ntest1++;\n}\n\nfor (int test2 = 1; test2 <= 4; test2++)\nConsole.WriteLine("Test 2 Passed (should print 4 times)");\nint test3 = 21;\ndo\n{\nConsole.WriteLine("If this line prints once, Test 3 Passed");\ntest3++;\n} while (test3 >= 20);\nConsole.WriteLine("Test 4 Passed if prints once");\n}\n}\n}';
	
</script>
</head>
<body>
	<div>
		<form class="form-group" action="/login.do" method="post">
			<label class="offset-1" for="codeInput">Input C# Code</label>
			<div>
				<div id="codeEditor" class="offset-1" ></div>
				<textarea  id="codeSubmit" class="col-10 form-control offset-1" name="name" rows="10"></textarea>
			</div>
			<div>
				<input class="col-10 btn btn-primary btn-lg btn-block offset-1" type="submit" value="Translate">
			</div>
			
		</form>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.12/ace.js" type="text/javascript" charset="utf-8"></script>
	<script>
		//var editor = ace.edit("codeEditor");
	    //editor.setTheme("ace/theme/monokai");
	    //editor.session.setMode("ace/mode/javascript");
	    //editor.session.setValue(initial);
	    //var textarea = document.getElementById("codeSubmit");
	    //textarea.value = initial;
	</script>
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