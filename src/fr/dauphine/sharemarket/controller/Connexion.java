package fr.dauphine.sharemarket.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sharemarket.dao.UtilisateurDAOInterface;
import fr.dauphine.sharemarket.error.MessagesDErreurs;
import fr.dauphine.sharemarket.model.Utilisateur;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UtilisateurDAOInterface utilisateurService;
	
    public Connexion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = utilisateurService.connexion(request.getParameter("email"), request.getParameter("password"));
		HttpSession session = request.getSession(true);
		if(utilisateur==null){
			session.setAttribute("connection_error", MessagesDErreurs.getMessageDerreur("4"));
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}
		if(utilisateur.getValide()==0){
			session.setAttribute("connection_error", MessagesDErreurs.getMessageDerreur("5"));
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}
		session.setAttribute("connected_user", utilisateur);
		if(utilisateur.getAdministrateur()==1){
			getServletContext().getRequestDispatcher("/Admin").forward(request, response);
		}else if (utilisateur.getMembreSociete()==1){
			getServletContext().getRequestDispatcher("/MembreSociete").forward(request, response);
		}else{
			getServletContext().getRequestDispatcher("/Investissor").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
