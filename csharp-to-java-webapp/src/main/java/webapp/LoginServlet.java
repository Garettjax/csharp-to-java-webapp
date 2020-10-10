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
		String code = request.getParameter("name");
		if(code != "") {
			CustomParser customParser = new CustomParser(code);
			tokens = customParser.getAllTokens(customParser);
			
			Iterator<Token> itr = tokens.iterator();
			while (itr.hasNext()) {
				Token next = itr.next();
				if (next.getRowNumber() == -1) {
					itr.remove();
				}
			}

			tokens = Translation.TranslateTokensToJava(tokens);
			//PrintWriter out = response.getWriter();
			
//			String temp = "";
//			if(showTokens) {
//				for (Token element : tokens) {
//					out.println("<div>");
//					out.println(element.getLexeme());
//					out.println("</div>");
////					out.println("<div>");
////					out.println("<div>");
////					out.println("<div>");
////					out.println("<div>");
////					out.println("<div>");
////					temp += element.rowNum + "              ";
////					temp += element.getLexeme() + "                ";
////					temp += element.getTokenType() + "                ";
//				}
//			}
			
			request.setAttribute("name", tokens);
		}
		
		
		
		
//		request.getRequestDispatcher("/WEB-INF/views/viewCode.jsp").forward(request, response);
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
}