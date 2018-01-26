package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.repositories.SausRepository;

@WebServlet("/ingredient.htm")
public class IngredientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/ingredient.jsp";
	private final SausRepository sausRepository = new SausRepository();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fout = new String();
		String ingredient = request.getParameter("ingredient");
		if(!isIngredientValid(ingredient)) {
			fout = "Ingrediënt is niet geldig!";
			request.setAttribute("fout", fout);
		}
		else {
			request.setAttribute("sauzen", sausRepository.findByIngredient(ingredient));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	private boolean isIngredientValid(String ingredient) {
		return ingredient != null && !ingredient.trim().isEmpty();
	}
	
}
