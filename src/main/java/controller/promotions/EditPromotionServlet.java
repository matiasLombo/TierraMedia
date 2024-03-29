package controller.promotions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attraction;
import model.Promotion;
import model.PromotionAbsoluta;
import model.PromotionAxB;
import model.PromotionPorcentual;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/promotions/edit.do")
public class EditPromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private AttractionService attractionService;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Promotion promotion = promotionService.find(id);
		List<Attraction> attractionsList = attractionService.listByTypeAttraction(promotion.getType());
		req.setAttribute("promotion", promotion);
		req.setAttribute("attractionsList", attractionsList);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String typeP = req.getParameter("typeP");
		String name = req.getParameter("name");
		String[] attractionsid = req.getParameterValues("myselect");
		
		if(typeP.equals("PROMOCIONPORCENTUAL")) {
			PromotionPorcentual promotionPorcentual = promotionService.updatePorcentual(id, name, attractionsid);
			if (promotionPorcentual.isValid()) {
				resp.sendRedirect("/TierraMedia/promotions/index.do");
			} else {
				req.setAttribute("promotion", promotionPorcentual);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/create.jsp");
				dispatcher.forward(req, resp);
			}
		}
		
		if(typeP.equals("PROMOCIONABSOLUTA")) {
			PromotionAbsoluta promotionAbsoluta = promotionService.updateAbsoluta(id, name, attractionsid);
			if (promotionAbsoluta.isValid()) {
				resp.sendRedirect("/TierraMedia/promotions/index.do");
			} else {
				req.setAttribute("promotion", promotionAbsoluta);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/create.jsp");
				dispatcher.forward(req, resp);
			}
		}
		
		if(typeP.equals("PROMOCIONAXB")) {
			PromotionAxB promotionAxB = promotionService.updateAxB(id, name, attractionsid);
			if (promotionAxB.isValid()) {
				resp.sendRedirect("/TierraMedia/promotions/index.do");
			} else {
				req.setAttribute("promotion", promotionAxB);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/create.jsp");
				dispatcher.forward(req, resp);
			}
		}
	}
}
