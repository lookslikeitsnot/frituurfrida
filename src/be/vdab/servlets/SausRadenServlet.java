package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.spellen.SausRaden;

@WebServlet("/sausraden.htm")
public class SausRadenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sausraden.jsp";
	private static final String SPEL = "sausRaden";
	private static final int MAXPOGINGEN = 10;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(SPEL) == null) {

			session.setAttribute(SPEL, new SausRaden());
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (request.getParameter("nieuwspel") != null) {
			session.removeAttribute(SPEL);
		} else {
			SausRaden sausRaden = (SausRaden) session.getAttribute(SPEL);
			String userChar = request.getParameter("userChar");
			if (userChar != null && !userChar.isEmpty()) {
				if (sausRaden.getTeVinden().indexOf(userChar) == -1) {
					sausRaden.setPogingen(sausRaden.getPogingen() + 1);
				}
				if(sausRaden.getPogingen()>MAXPOGINGEN) {
					sausRaden.setPogingen(MAXPOGINGEN);
				}
				sausRaden.getGeprobeerdeLetters().add(userChar.charAt(0));
				session.setAttribute(SPEL, sausRaden);
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
}