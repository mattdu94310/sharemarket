package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.services.UtilisateurService;
import jpa.entities.Utilisateur;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UtilisateurService utilisateurService;
	
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = utilisateurService.connexion(request.getParameter("email"), request.getParameter("password"));
		StringBuilder stringBuilder = new StringBuilder();
		if(utilisateur==null){
			stringBuilder.append("Utilisateur Inconnu \n");
		}
		
		
		
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
