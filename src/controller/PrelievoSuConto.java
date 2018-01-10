package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartaDiCredito;
import model.Conto;
import model.Giocatore;
import model.MovimentoCarta;
import model.TipoMovimento;
import persistence.DAOFactory;
import persistence.PostgresDAOFactory;

public class PrelievoSuConto extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		String importoStr=req.getParameter("importo");
		Giocatore user=(Giocatore) session.getAttribute("loggato");
		Conto contoUtente=user.getConto();
		CartaDiCredito carta=contoUtente.getCarta();
		Float importo=Float.valueOf(importoStr);
		if(contoUtente.preleva(importo)) {
			carta.versa(importo);
			System.out.println("prelevato");
			PostgresDAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getCartaDiCreditoDAO().update(carta);
			PostgresDAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getContoDAO().update(contoUtente);
			MovimentoCarta movimento=new MovimentoCarta(new java.util.Date(), TipoMovimento.PRELIEVO, Float.valueOf(importo), contoUtente);
			PostgresDAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getMovimentoCartaDAO().save(movimento);
		}
		else {
			resp.getWriter().print("importo non disponibile");
		}
	}
}
