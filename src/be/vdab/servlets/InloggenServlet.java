package be.vdab.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inloggen.htm")
public class InloggenServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/inloggen.jsp";
	private static final String REDIRECT_URL_JUIST = "/gastenboek.htm";
	private static final String REDIRECT_URL_FOUT = "/inloggen.htm";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirect_url = REDIRECT_URL_FOUT;
		String pwd = request.getParameter("pwd");
		if(pwd!=null && this.getServletContext().getInitParameter("beheerderpwd").equals(pwd)) {
			Cookie cookie = new Cookie("pwd", URLEncoder.encode(pwd, "UTF-8"));
			response.addCookie(cookie);
			redirect_url = REDIRECT_URL_JUIST;
		}
		response.sendRedirect(request.getContextPath() + redirect_url);
	}
}
