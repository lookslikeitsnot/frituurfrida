package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.GastenboekEntry;
import be.vdab.repositories.GastenboekRepository;

@WebServlet("/gastenboek.htm")
public class GastenboekServlet extends HttpServlet{
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
		if(toevoegen!=null && toevoegen.equals("true")) {
			request.setAttribute("toevoegen", toevoegen);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String gastnaam = request.getParameter("gastnaam");
		if (gastnaam == null || gastnaam.trim().isEmpty()) {
			fouten.put("gastnaam", "verplicht");
		}
		String gastentekst = request.getParameter("gastentekst");
		if (gastentekst == null || gastentekst.trim().isEmpty()) {
			fouten.put("gastentekst", "verplicht");
		}
		if(fouten.isEmpty()) {
			GastenboekEntry gEntry = new GastenboekEntry(gastentekst, gastnaam, null);
			gastenboek.create(gEntry);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
		}else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		
	}
}
