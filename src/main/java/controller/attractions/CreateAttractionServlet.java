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

@WebServlet("/attractions/create.do")
public class CreateAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
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
		req.setAttribute("typeAttractions", typeAttractions);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String type = req.getParameter("typeAttraction");
		Double cost = Double.parseDouble(req.getParameter("cost"));
		Double duration = Double.parseDouble(req.getParameter("duration"));
		Integer capacity = Integer.parseInt(req.getParameter("capacity"));

		Attraction attraction = attractionService.create(name, type, cost, duration, capacity);
		if (attraction.isValid()) {
			resp.sendRedirect("/TierraMedia/attractions/index.do");
		} else {
			req.setAttribute("attraction", attraction);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/attractions/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
