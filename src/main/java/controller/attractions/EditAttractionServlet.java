package controller.attractions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attraction;
import model.TypeAttraction;
import services.AttractionService;
import services.TypeAttractionService;

@WebServlet("/attractions/edit.do")
public class EditAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private AttractionService attractionService;
	private TypeAttractionService typeAttractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.typeAttractionService = new TypeAttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TypeAttraction> typeAttractions = typeAttractionService.list();
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		Attraction attraction = attractionService.find(id);
		req.setAttribute("attraction", attraction);
		req.setAttribute("typeAttractions", typeAttractions);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String type = req.getParameter("typeAttraction");
		Double cost = Double.parseDouble(req.getParameter("cost"));
		Double duration = Double.parseDouble(req.getParameter("duration"));
		Integer capacity = Integer.parseInt(req.getParameter("capacity"));

		Attraction attraction = attractionService.update(id, name, type, cost, duration, capacity);

		if (attraction.isValid()) {
			resp.sendRedirect("/TierraMedia/attractions/index.do");
		} else {
			req.setAttribute("attraction", attraction);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
