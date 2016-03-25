package fr.dauphine.sharemarket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sharemarket.dao.SecteurActiviteDAO;
import fr.dauphine.sharemarket.dao.SecteurActiviteDAOInterface;
import fr.dauphine.sharemarket.dao.SocieteDAO;
import fr.dauphine.sharemarket.dao.SocieteDAOInterface;
import fr.dauphine.sharemarket.error.MessagesDErreurs;
import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Secteur_activite;
import fr.dauphine.sharemarket.model.Societe;
import fr.dauphine.sharemarket.model.Utilisateur;

@WebServlet("/MemberSocietyFunction")
public class MemberSocietyFunction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger MEMBER_SOCIETY_FUNCTION_LOGGER = Logger.getLogger(AdminFunction.class.getCanonicalName());   
	@EJB
	SocieteDAOInterface societeDAO;
	@EJB
	SecteurActiviteDAOInterface secteurActiviteDAO;

	public MemberSocietyFunction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Byte isMemberSociete=0;
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("connected_user");
		if(utilisateur!=null){
			isMemberSociete=utilisateur.getMembreSociete();
		}else {
			getServletConfig().getServletContext().getRequestDispatcher("/unauthorized.html").forward(request,response);
			return;
		}
		if(isMemberSociete==0){
			getServletConfig().getServletContext().getRequestDispatcher("/unauthorized.html").forward(request,response);
			return;
		}
		if(request.getParameter("Demande")==null){
			getServletConfig().getServletContext().getRequestDispatcher("/notfound.html").forward(request,response);
			return;
		}
		MEMBER_SOCIETY_FUNCTION_LOGGER.info("Demande="+request.getParameter("Demande"));
		switch(request.getParameter("Demande")){
		case "EditSociety" : 
			editerSociete(request,response);
			break;
		case "Modifier Societe" : 
			modifierSociete(request,response);
			break;

		case "AjouterSecteur" :
			ajouterSecteur(request,response);
			break;
			
		case "SupprimerSecteur" :
			supprimerSecteur(request,response);
			break;
		default : 
			getServletConfig().getServletContext().getRequestDispatcher("/notfound.html").forward(request,response);
			return;
		}	
	}

	private void supprimerSecteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("societe")==null || request.getParameter("secteur")==null){
			request.setAttribute("message_error", MessagesDErreurs.getMessageDerreur("3"));
			editerSociete(request, response);
			return;
		}
		int societe = Integer.parseInt(request.getParameter("societe"));
		int secteur = Integer.parseInt(request.getParameter("secteur"));
		try {
			societeDAO.removeSecteur(societe, secteur);
		} catch (ShareMarketException e) {
			request.setAttribute("message_error", e.getMessage());
			editerSociete(request, response);
			return;
		}
		editerSociete(request, response);
		return;
	}

	private void ajouterSecteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("societe")==null || request.getParameter("secteur")==null){
			request.setAttribute("message_error", MessagesDErreurs.getMessageDerreur("3"));
			editerSociete(request, response);
			return;
		}
		int societe = Integer.parseInt(request.getParameter("societe"));
		int secteur = Integer.parseInt(request.getParameter("secteur"));
		Secteur_activite secteur_activite=null;
		try {
			secteur_activite = secteurActiviteDAO.getSecteurById(secteur);
		} catch (ShareMarketException e) {
			
		}
		Societe societeEntity;
		try {
			societeEntity=societeDAO.findById(societe);
		} catch (ShareMarketException e) {
			request.setAttribute("message_error", e.getMessage());
			editerSociete(request, response);
			return;
		}
		List<Societe> societes = new ArrayList<Societe>();
		societes.add(societeEntity);
		secteur_activite.setSocietes(societes);
		List<Secteur_activite> secteur_activites = new ArrayList<Secteur_activite>();
		MEMBER_SOCIETY_FUNCTION_LOGGER.info(secteur_activite.getSecteur());
		secteur_activites.add(secteur_activite);
		societeEntity.setSecteurActivites(secteur_activites);
		societeDAO.save(societeEntity);
		secteurActiviteDAO.save(secteur_activite);
		editerSociete(request, response);
		return;
	}

	private void modifierSociete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("nom").equals("") || request.getParameter("resume").equals("")){
			request.setAttribute("message_error", MessagesDErreurs.getMessageDerreur("3"));
			editerSociete(request, response);
			return;
		}
		boolean error=false;
		try {
			societeDAO.updateSociete(request.getParameter("id") ,request.getParameter("nom"), request.getParameter("resume"));
		} catch (ShareMarketException e) {
			error=true;
			request.setAttribute("message_error",e.getMessage());
		}
		if(!error){
			request.setAttribute("message_info",MessagesDErreurs.getMessageDerreur("8"));
		}
		getServletConfig().getServletContext().getRequestDispatcher("/MembreSociete").forward(request,response);
		return;
	}

	private void editerSociete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("connected_user");
		Societe societe = null;
		try {
			societe = societeDAO.findByMemberSociete(utilisateur.getLogin());
		} catch (ShareMarketException e) {
			request.setAttribute("message_error", e.getMessage());
			getServletConfig().getServletContext().getRequestDispatcher("/MembreSociete").forward(request,response);
			return;
		}
		List<Secteur_activite> secteurs;
		try {
			secteurs = secteurActiviteDAO.getAllSecteur();
		} catch (ShareMarketException e) {
			request.setAttribute("message_error", e.getMessage());
			getServletConfig().getServletContext().getRequestDispatcher("/MembreSociete").forward(request,response);
			return;
		}
		request.setAttribute("societe", societe);
		request.setAttribute("secteurs", secteurs);
		getServletConfig().getServletContext().getRequestDispatcher("/EditSociety").forward(request,response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
