package webapp;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
	public static ArrayList<Token> tokens = new ArrayList<Token>();
	boolean showTokens =true;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
//		String t = request.getParameter("name");
//		System.out.println(t);
		CustomParser customParser = new CustomParser(request.getParameter("name"));
		tokens = customParser.getAllTokens(customParser);
		
		Iterator<Token> itr = tokens.iterator();
		while (itr.hasNext()) {
			Token next = itr.next();
			if (next.getRowNumber() == -1) {
				itr.remove();
			}
		}

		tokens = Translation.TranslateTokensToJava(tokens);
		
		//PrintWriter pr = new PrintWriter("");
		String temp = "";
		if(showTokens) {
			for (Token element : tokens) {
				temp += element.rowNum + "                ";
				temp += element.getLexeme() + "                ";
				temp += element.getTokenType() + "                ";
//				pr.println();
//				pr.println("*************************************");
//				pr.println(element.rowNum);
//				pr.println(element.getLexeme());
//				pr.println(element.getTokenType());
//				pr.println("*************************************");
//				pr.println();
			}
		}
		
		request.setAttribute("name", temp);
		
		
		
//		request.getRequestDispatcher("/WEB-INF/views/viewCode.jsp").forward(request, response);
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
}