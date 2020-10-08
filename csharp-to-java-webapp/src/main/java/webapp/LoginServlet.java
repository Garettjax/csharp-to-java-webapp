package webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
//		String t = request.getParameter("name");
//		System.out.println(t);
		request.setAttribute("name", request.getParameter("name"));
//		request.getRequestDispatcher("/WEB-INF/views/viewCode.jsp").forward(request, response);
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
}