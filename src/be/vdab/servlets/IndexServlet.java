package be.vdab.servlets;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Adres;
import be.vdab.entities.Gemeente;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(urlPatterns = "/index.htm", name = "indexservlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DayOfWeek dag = LocalDateTime.now().getDayOfWeek();
//		request.setAttribute("telefoonnummer", this.getInitParameter("telefoonnummer"));
		request.setAttribute("openGesloten", dag.equals(DayOfWeek.MONDAY) || dag.equals(DayOfWeek.THURSDAY)
				? "gesloten" : "open");
		request.setAttribute("adres", 
				new Adres("Stationstraat", "25B", 
						new Gemeente("Hoplaboem", 9000)));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
	