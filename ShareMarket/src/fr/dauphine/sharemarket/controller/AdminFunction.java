package fr.dauphine.sharemarket.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sharemarket.dao.UtilisateurDAOInterface;
import fr.dauphine.sharemarket.error.MessagesDErreurs;
import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Utilisateur;

/**
 * Servlet implementation class AdminFunction
 */
@WebServlet("/AdminFunction")
public class AdminFunction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger ADMIN_FUNCTION_LOGGER = Logger.getLogger(AdminFunction.class.getCanonicalName());   
	@EJB
	private UtilisateurDAOInterface utilisateurDAO;
	
    public AdminFunction() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Byte isAdmin=0;
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("connected_user");
		if(utilisateur!=null){
			isAdmin=utilisateur.getAdministrateur();
		}
		if(isAdmin==0){
			getServletConfig().getServletContext().getRequestDispatcher("/unauthorized.html").forward(request,response);
		}
		if(request.getParameter("Demande") != null){
			switch(request.getParameter("Demande")){
			case "Rechercher Utilisateur" :
				rechercherUtilisateur(request, response);
				break;
			
			case "SupprimerUtilisateur" :
				supprimerUtilisateur(request, response);
				break;
				
			case "FormulaireUtilisateur" :
				formulaireUtilisateur(request,response);
				break;
				
			case "Modifier Utilisateur" : 
				modifierUtilisateur(request,response);
				break;
				
			case "Ajouter Membre" :
				creerMembreSociete(request,response);
			}
		}
	}


	private void creerMembreSociete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("login").trim().equals("") || request.getParameter("password").trim().equals("") || request.getParameter("nom").trim().equals("") || request.getParameter("prenom").trim().equals("") || request.getParameter("nomsociete").trim().equals("")){
			request.setAttribute("message_error", MessagesDErreurs.getMessageDerreur("3"));
			getServletConfig().getServletContext().getRequestDispatcher("/MInscription").forward(request,response);
			return;
		}
		try {
			utilisateurDAO.creerMembreSociete(request.getParameter("login"),request.getParameter("password"), request.getParameter("nom"),request.getParameter("prenom"), request.getParameter("nomsociete") );
		} catch (ShareMarketException e) {
			request.setAttribute("message_error", e.getMessage());
			getServletConfig().getServletContext().getRequestDispatcher("/MInscription").forward(request,response);
			return;
		}
		request.setAttribute("message_info", "Utilisateur correctement ajouté à la base");
		getServletConfig().getServletContext().getRequestDispatcher("/Admin").forward(request,response);
	}

	private void modifierUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur = utilisateurDAO.findById(request.getParameter("login"));
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setPassword(request.getParameter("password"));
		Byte valide=0;
		if(request.getParameter("valide")!=null){
			valide=1;
		}
		utilisateur.setValide(valide);
		utilisateurDAO.maj(utilisateur);
		precedenteRecherche(request, response);
	}

	private void rechercherUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		byte admin=0, investor=0, membersociety=0,valide=0;
		if(request.getParameter("admin")!=null){
			admin=1;
		}
		if(request.getParameter("investor")!=null){
			investor=1;
		}
		if(request.getParameter("membersociety")!=null){
			membersociety=1;
		}
		if(request.getParameter("valide")!=null){
			valide=1;
		}
		session.setAttribute("search_user_login", request.getParameter("login"));
		session.setAttribute("search_user_nom", request.getParameter("nom"));
		session.setAttribute("search_user_prenom", request.getParameter("prenom"));
		session.setAttribute("search_user_admin", admin);
		session.setAttribute("search_user_membersociety", membersociety);
		session.setAttribute("search_user_valide", valide);
		session.setAttribute("search_user_investisseur", investor);
		List<Utilisateur> utilisateurs = utilisateurDAO.find(request.getParameter("login"), request.getParameter("nom"), request.getParameter("prenom"),admin, membersociety, investor, valide);
		request.setAttribute("utilisateurs", utilisateurs); 
		getServletConfig().getServletContext().getRequestDispatcher("/userlist.jsp").forward(request,response);
	}
	
	private void precedenteRecherche(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("search_user_login");
		String nom = (String) session.getAttribute("search_user_nom");
		String prenom = (String) session.getAttribute("search_user_prenom");
		Byte admin = (Byte) session.getAttribute("search_user_admin");
		Byte membersociety = (Byte) session.getAttribute("search_user_membersociety");
		Byte investor = (Byte) session.getAttribute("search_user_investisseur");
		Byte valide = (Byte) session.getAttribute("search_user_valide");
		List<Utilisateur> utilisateurs = utilisateurDAO.find(login, nom, prenom,admin, membersociety, investor, valide);
		request.setAttribute("utilisateurs", utilisateurs); 
		getServletConfig().getServletContext().getRequestDispatcher("/UserList").forward(request,response);
	}
	
	private void supprimerUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		utilisateurDAO.supprimer(request.getParameter("id"));
		precedenteRecherche(request, response);
	}
	
	private void formulaireUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("utilisateur", utilisateurDAO.findById(request.getParameter("id")));
		request.setAttribute("Demande", "");
		getServletConfig().getServletContext().getRequestDispatcher("/EditUser").forward(request,response);
	}
}
