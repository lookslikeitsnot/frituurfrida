package be.vdab.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.GastenboekEntry;
import be.vdab.repositories.GastenboekRepository;
import be.vdab.util.StringUtils;

@WebServlet("/gastenboek.htm")
public class GastenboekServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/gastenboek.jsp";
	private static final String REDIRECT_URL = "/gastenboek.htm";
	private final transient GastenboekRepository gastenboek = new GastenboekRepository();

	@Resource(name = GastenboekRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		gastenboek.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<GastenboekEntry> gastenboekEntries = gastenboek.findAll();
		request.setAttribute("gastenboek", gastenboekEntries);
		String toevoegen = request.getParameter("toevoegen");
		if (toevoegen != null && toevoegen.equals("true")) {
			request.setAttribute("toevoegen", toevoegen);
		}
		if (checkUser(request)) {
			request.setAttribute("beheerder", "true");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String uitloggen = request.getParameter("uitloggen");
		String[] teVerwijderen = request.getParameterValues("entryId");
		if ("Uitloggen".equals(uitloggen)) {
			Cookie cookie = new Cookie("pwd", URLEncoder.encode(uitloggen, "UTF-8"));
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
//			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
		}else if(teVerwijderen != null && checkUser(request)) {
			for(String str: teVerwijderen) {
				if(StringUtils.isInt(str)) {
					gastenboek.delete(Integer.parseInt(str));
				}
			}
//			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
		}
		else {
			String gastnaam = request.getParameter("gastnaam");
			if (gastnaam == null || gastnaam.trim().isEmpty()) {
				fouten.put("gastnaam", "verplicht");
			}
			String gastentekst = request.getParameter("gastentekst");
			if (gastentekst == null || gastentekst.trim().isEmpty()) {
				fouten.put("gastentekst", "verplicht");
			}
			if (fouten.isEmpty()) {
				GastenboekEntry gEntry = new GastenboekEntry(0, gastentekst, gastnaam, null);
				gastenboek.create(gEntry);
//				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
			} else {
				request.setAttribute("fouten", fouten);
//				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));

	}
	
	private boolean checkUser(HttpServletRequest request) throws UnsupportedEncodingException {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if ("pwd".equals(cookie.getName())) {
					String pwd = URLDecoder.decode(cookie.getValue(), "UTF-8");
					if (pwd != null && this.getServletContext().getInitParameter("beheerderpwd").equals(pwd)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
