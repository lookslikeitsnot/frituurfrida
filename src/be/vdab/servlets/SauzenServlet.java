package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Saus;


@WebServlet("/sauzen.htm")
public class SauzenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sauzen.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Saus> sauzen = new LinkedList<>();
		sauzen.add(new Saus(12, "cocktail", "zout", "peper", "water"));
		sauzen.add(new Saus(16, "mayonaise", "eiwit", "dooiers", "olie"));
		sauzen.add(new Saus(18, "mosterd"));
		sauzen.add(new Saus(19, "tartare", "yaourt"));
		sauzen.add(new Saus(20, "vinaigrette", "azijn"));
		request.setAttribute("sauzen", sauzen);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
