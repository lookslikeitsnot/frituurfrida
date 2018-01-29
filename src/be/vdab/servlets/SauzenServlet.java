package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.repositories.SausRepository;
import be.vdab.util.StringUtils;

@WebServlet("/sauzen.htm")
public class SauzenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sauzen.jsp";
	private static final String REDIRECT_URL = "/sauzen.htm";
	private final SausRepository sausRepository = new SausRepository();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("sauzen", sausRepository.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String[] sausNummersStrings = request.getParameterValues("sausnummer");
		if (sausNummersStrings != null) {
			for (String sausNummerString : sausNummersStrings) {
				if (sausNummerString != null && !sausNummerString.isEmpty() && StringUtils.isLong(sausNummerString))
					sausRepository.remove(Long.parseLong(sausNummerString));
			}
		}
		response.sendRedirect(request.getContextPath() + REDIRECT_URL);

	}
}
