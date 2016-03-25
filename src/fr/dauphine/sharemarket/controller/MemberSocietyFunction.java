package fr.dauphine.sharemarket.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sharemarket.dao.ContratDAOInterface;
import fr.dauphine.sharemarket.dao.SecteurActiviteDAO;
import fr.dauphine.sharemarket.dao.SecteurActiviteDAOInterface;
import fr.dauphine.sharemarket.dao.SocieteDAO;
import fr.dauphine.sharemarket.dao.SocieteDAOInterface;
import fr.dauphine.sharemarket.dao.TypeContratDAOInterface;
import fr.dauphine.sharemarket.error.MessagesDErreurs;
import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Contrat;
import fr.dauphine.sharemarket.model.Secteur_activite;
import fr.dauphine.sharemarket.model.Societe;
import fr.dauphine.sharemarket.model.Type_Contrat;
import fr.dauphine.sharemarket.model.Utilisateur;

@WebServlet("/MemberSocietyFunction")
public class MemberSocietyFunction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger MEMBER_SOCIETY_FUNCTION_LOGGER = Logger.getLogger(AdminFunction.class.getCanonicalName());   
	@EJB
	SocieteDAOInterface societeDAO;
	@EJB
	SecteurActiviteDAOInterface secteurActiviteDAO;
	@EJB
	TypeContratDAOInterface typeContratDAO;
	@EJB
	ContratDAOInterface contratDAO;
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
			
		case "EditContract" : 
			editerNouveauContract(request,response);
			break;
		case "Nouveau Contrat Enchere" : 
			nouveauContratEnchere(request,response);
			break;
		case "Nouveau Contrat Prix Fixe" : 
			nouveauContratPrixFixe(request,response);
			break;
		case "ListContrats" : 
			afficherContratSociete(request,response);
			break;
		default : 
			getServletConfig().getServletContext().getRequestDispatcher("/notfound.html").forward(request,response);
			return;
		}	
	}

	private void afficherContratSociete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.setAttribute("contrats",societe.getContrats());
		getServletConfig().getServletContext().getRequestDispatcher("/MyContracts").forward(request,response);
		return;
	}

	private void nouveauContratPrixFixe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("societe_id").equals("") || request.getParameter("contrat_id").equals("") || request.getParameter("prix_fixe").equals("") ||  request.getParameter("type_contrat").equals("")){
			request.setAttribute("message_error", MessagesDErreurs.getMessageDerreur("3"));
			editerNouveauContract(request, response);
			return;
		}
		Contrat contrat = new Contrat();
		try {
			contrat.setSociete(societeDAO.findById(Integer.parseInt(request.getParameter("societe_id"))));
		} catch (NumberFormatException | ShareMarketException e) {
			request.setAttribute("message_error", e.getMessage());
			editerNouveauContract(request, response);
			return;
		}
		Type_Contrat type_Contrat;
		try {
			type_Contrat = typeContratDAO.findById(Integer.parseInt(request.getParameter("type_contrat")));
		} catch (NumberFormatException | ShareMarketException e1) {
			request.setAttribute("message_error", e1.getMessage());
			editerNouveauContract(request, response);
			return;
		}
		contrat.setTypeContrat(type_Contrat);
		Byte achat=0;
		if(request.getParameter("achat_vente")!=null)achat=1;
		contrat.setAchatVente(achat);
		contrat.setDateEmission(new Timestamp(System.currentTimeMillis()));
		contrat.setEtat(0);
		contrat.setPrixFixe(Double.parseDouble(request.getParameter("prix_fixe")));
		contrat.setPrixDepart(0);
		contratDAO.insert(contrat);
		request.setAttribute("message_info", MessagesDErreurs.getMessageDerreur("14"));
		getServletConfig().getServletContext().getRequestDispatcher("/MembreSociete").forward(request,response);
		return;
	}

	private void nouveauContratEnchere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("societe_id").equals("") || request.getParameter("contrat_id").equals("") || request.getParameter("prix_depart").equals("") || request.getParameter("date_fin_echere").equals("")|| request.getParameter("type_contrat").equals("")){
			request.setAttribute("message_error", MessagesDErreurs.getMessageDerreur("3"));
			editerNouveauContract(request, response);
			return;
		}
		Contrat contrat = new Contrat();
		try {
			contrat.setSociete(societeDAO.findById(Integer.parseInt(request.getParameter("societe_id"))));
		} catch (NumberFormatException | ShareMarketException e) {
			request.setAttribute("message_error", e.getMessage());
			editerNouveauContract(request, response);
			return;
		}
		Type_Contrat type_Contrat;
		try {
			type_Contrat = typeContratDAO.findById(Integer.parseInt(request.getParameter("type_contrat")));
		} catch (NumberFormatException | ShareMarketException e1) {
			request.setAttribute("message_error", e1.getMessage());
			editerNouveauContract(request, response);
			return;
		}
		contrat.setTypeContrat(type_Contrat);
		Byte achat=0;
		if(request.getParameter("achat_vente")!=null)achat=1;
		contrat.setAchatVente(achat);
		contrat.setDateEmission(new Timestamp(System.currentTimeMillis()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date parsedDate;
	    try {
			parsedDate = dateFormat.parse(request.getParameter("date_fin_echere"));
		} catch (ParseException e) {
			request.setAttribute("message_error", e.getMessage());
			editerNouveauContract(request, response);
			return;
		} 
		contrat.setDateFinEnchere(new Timestamp(parsedDate.getTime()));
		contrat.setEtat(0);
		contrat.setPrixDepart(Double.parseDouble(request.getParameter("prix_depart")));
		contrat.setPrixFixe(0);
		contratDAO.insert(contrat);
		request.setAttribute("message_info", MessagesDErreurs.getMessageDerreur("14"));
		getServletConfig().getServletContext().getRequestDispatcher("/MembreSociete").forward(request,response);
		return;
	}

	private void editerNouveauContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.setAttribute("societe_id", societe.getIdSociete());
		List<Type_Contrat> types;
		try {
			types=typeContratDAO.getAll();
		} catch (ShareMarketException e) {
			request.setAttribute("message_error", e.getMessage());
			getServletConfig().getServletContext().getRequestDispatcher("/MembreSociete").forward(request,response);
			return;
		}
		request.setAttribute("types", types);
		getServletConfig().getServletContext().getRequestDispatcher("/EditContract").forward(request,response);
		return;
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
