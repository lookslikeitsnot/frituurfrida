package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.repositories.DeurRepository;
import be.vdab.util.StringUtils;

@WebServlet("/deuren.htm")
public class DeurenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/deuren.jsp";
	private static final String SPEL = "deurSpel";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(SPEL) == null) {
			session.setAttribute(SPEL, new DeurRepository());
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
			DeurRepository deurRepository = (DeurRepository) session.getAttribute(SPEL);
			String deurNummerString = request.getParameter("deurnummer");
			if (deurNummerString != null && !deurNummerString.isEmpty() && StringUtils.isLong(deurNummerString)) {
				deurRepository.openDoor(Long.parseLong(deurNummerString));
				session.setAttribute(SPEL, deurRepository);
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
}